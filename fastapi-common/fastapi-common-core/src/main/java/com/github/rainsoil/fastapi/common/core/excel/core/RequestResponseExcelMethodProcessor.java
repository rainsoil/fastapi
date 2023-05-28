package com.github.rainsoil.fastapi.common.core.excel.core;

import cn.hutool.core.lang.Opt;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.excel.annotation.RequestExcel;
import com.github.rainsoil.fastapi.common.core.excel.annotation.ResponseExcel;
import com.github.rainsoil.fastapi.common.core.excel.enums.Scene;
import com.github.rainsoil.fastapi.common.core.excel.exception.EasyExcelException;
import com.github.rainsoil.fastapi.common.core.excel.listener.CollectorReadListener;
import com.github.rainsoil.fastapi.common.core.excel.module.RequestExcelInfo;
import com.github.rainsoil.fastapi.common.core.excel.module.ResponseExcelInfo;
import com.github.rainsoil.fastapi.common.core.validation.BeanValidators;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alibaba.excel.support.ExcelTypeEnum.CSV;

/**
 * 注解处理
 *
 * @author luyanan
 * @since 2023/05/25
 **/
public class RequestResponseExcelMethodProcessor implements HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {


	private static final String RESPONSE_EXCEL_CONTENT_TYPE =
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static final String RESPONSE_EXCEL_CONTENT_DISPOSITION = "Content-disposition";

	private static final String RESPONSE_EXCEL_ATTACHMENT = "attachment;filename=";

	private static final String EXCEL_TYPE_HEADER = "excel-type";

	private final ResourceLoader resourceLoader;


	public RequestResponseExcelMethodProcessor(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(RequestExcel.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		parameter = parameter.nestedIfOptional();
		Object data = readWithMessageConverters(webRequest, parameter);
		validateIfNecessary(data, parameter);
		return data;
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.hasMethodAnnotation(ResponseExcel.class);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);
		writeWithMessageConverters(returnValue, returnType, webRequest);
	}

	/**
	 * 写入转换
	 *
	 * @param returnValue 返回结果
	 * @param parameter   参数
	 * @param webRequest  请求头
	 * @since 2023/05/28
	 */
	private void writeWithMessageConverters(Object returnValue, MethodParameter parameter, NativeWebRequest webRequest) throws IOException {

		HttpServletResponse response = webRequest.getNativeRequest(HttpServletResponse.class);
		Assert.state(response != null, "No HttpServletResponse");
		ServletOutputStream outputStream = response.getOutputStream();
		ResponseExcelInfo responseExcelInfo = new ResponseExcelInfo(parameter.getMethodAnnotation(ResponseExcel.class));
		String name = responseExcelInfo.getName();
		response.setContentType(RESPONSE_EXCEL_CONTENT_TYPE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		if (Scene.TEMPLATE.equals(responseExcelInfo.getScene())) {
			// 模板
			response.setHeader(RESPONSE_EXCEL_CONTENT_DISPOSITION,
					RESPONSE_EXCEL_ATTACHMENT + URLEncoder.encode(
							name.substring(name.indexOf("/") + 1), StandardCharsets.UTF_8.name()));
			InputStream inputStream = resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + name).getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			FileCopyUtils.copy(bufferedInputStream, outputStream);

		} else {
			Assert.notNull(returnValue, "The method annotated with @ResponseExcel can not return null in non-template mode");
			validateArgParamOrReturnValueType(parameter);

			response.setHeader(RESPONSE_EXCEL_CONTENT_DISPOSITION,
					RESPONSE_EXCEL_ATTACHMENT + URLEncoder.encode(
							name, StandardCharsets.UTF_8.name()) + responseExcelInfo.getSuffix().getValue());

			if (CSV == responseExcelInfo.getSuffix()) {
				outputStream.write(new byte[]{(byte) 0xef, (byte) 0xbb, (byte) 0xbf});
			}
			try (ExcelWriter excelWriter = EasyExcel.write(outputStream)
					.charset(StandardCharsets.UTF_8).excelType(responseExcelInfo.getSuffix()).build()) {
				List<WriteSheet> writeSheetList = responseExcelInfo.getSheetInfoList()
						.stream()
						.map(sheetInfo -> EasyExcel.writerSheet(sheetInfo.getName())
								.head(sheetInfo.getHeadClazz())
								.build()
						)
						.collect(Collectors.toList());
				@SuppressWarnings("unchecked")
				List<List<Object>> multiSheetData = (List<List<Object>>) returnValue;

				Assert.isTrue(writeSheetList.size() <= multiSheetData.size(), "Redundant @Sheet annotation in @ResponseExcel");

				for (int i = 0; i < writeSheetList.size(); i++) {
					WriteSheet writeSheet = writeSheetList.get(i);
					List<Object> singleSheetData = multiSheetData.get(i);
					excelWriter.write(singleSheetData, writeSheet);
				}
			} catch (Exception exception) {
				throw new EasyExcelException(exception.getMessage());
			}
		}

	}


	/**
	 * 结果校验
	 *
	 * @param data      数据
	 * @param parameter 参数
	 * @since 2023/05/28
	 */
	private void validateIfNecessary(Object data, MethodParameter parameter) {

		if (parameter.hasParameterAnnotation(Validated.class)
				|| parameter.hasParameterAnnotation(Valid.class)) {
			@SuppressWarnings("unchecked")
			List<Object> flattenData = ((List<List<Object>>) data).stream()
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
			for (Object target : flattenData) {
				Set<ConstraintViolation<Object>> constraintViolationSet = BeanValidators.validate(target);
				if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
					String errorMsg = constraintViolationSet.stream()
							.map(ConstraintViolation::getMessage)
							.distinct()
							.iterator()
							.next();
					throw new EasyExcelException(errorMsg);
				}
			}
		}

	}

	/**
	 * 读取转换
	 *
	 * @param request   亲求头
	 * @param parameter 参数
	 * @return java.lang.Object
	 * @since 2023/05/28
	 */
	private Object readWithMessageConverters(NativeWebRequest request, MethodParameter parameter) throws IOException {
		validateArgParamOrReturnValueType(parameter);
		HttpServletRequest httpServletRequest
				= request.getNativeRequest(HttpServletRequest.class);
		Assert.state(httpServletRequest != null, "No HttpServletRequest");
		return readWithMessageConverters(httpServletRequest, parameter);
	}

	/**
	 * 读取转换
	 *
	 * @param request         请求头
	 * @param methodParameter
	 * @return java.lang.Object
	 * @since 2023/05/25
	 */
	private Object readWithMessageConverters(HttpServletRequest request, MethodParameter methodParameter) throws IOException {

		ExcelTypeEnum excelTypeEnums = getExcelTypeEnums(request);
		RequestExcelInfo requestExcelInfo = new RequestExcelInfo(methodParameter.getParameterAnnotation(RequestExcel.class));
		InputStream is;
		if (request instanceof MultipartFile) {
			MultipartRequest multipartRequest = (MultipartRequest) request;
			is = multipartRequest.getMultiFileMap().values().stream().flatMap(Collection::stream)
					.findFirst().map(f -> {
						try {
							return f.getInputStream();
						} catch (IOException e) {
//							e.printStackTrace();
							return null;
						}

					}).orElse(new ByteArrayInputStream(new byte[0]));

		} else {
			is = request.getInputStream();
		}

		CollectorReadListener collectorReadListener = new CollectorReadListener();
		try (ExcelReader excelReader = EasyExcel.read(is).excelType(excelTypeEnums).build()) {
			List<ReadSheet> readSheets = requestExcelInfo.getSheetInfoList().stream().map(sheetInfo -> EasyExcel.readSheet(sheetInfo.getIndex())
					.head(sheetInfo.getHeadClazz())
					.registerReadListener(collectorReadListener)
					.build()).collect(Collectors.toList());
			excelReader.read(readSheets);
		} catch (Exception exception) {
			throw new EasyExcelException(exception.getMessage());
		}
		return collectorReadListener.sheetPartition();
	}

	/**
	 * 获取excel的后缀类型
	 *
	 * @param request 请求头
	 * @return com.alibaba.excel.support.ExcelTypeEnum
	 * @since 2023/05/26
	 */
	private ExcelTypeEnum getExcelTypeEnums(HttpServletRequest request) {
		String header = request.getHeader(EXCEL_TYPE_HEADER);
		return Opt.ofNullable(header).filter(a -> null != ExcelTypeEnum.valueOf(header)).map(a -> ExcelTypeEnum.valueOf(a)).orElse(ExcelTypeEnum.XLSX);
	}


	/**
	 * 校验
	 *
	 * @param parameter 参数
	 * @since 2023/05/28
	 */
	private void validateArgParamOrReturnValueType(MethodParameter parameter) {

		ResolvableType resolvableType = ResolvableType.forMethodParameter(parameter);
		boolean outerList = Optional.ofNullable(resolvableType.resolve())
				.map(List.class::isAssignableFrom)
				.orElse(null);
		if (!outerList) {
			throw new EasyExcelException(
					"@RequestExcel or @ResponseExcel must be annotated with List<List<>>");
		}
		boolean innerList = Optional.<ResolvableType>of(resolvableType.getGeneric(0))
				.map(ResolvableType::resolve)
				.map(List.class::isAssignableFrom)
				.orElse(false);
		if (!innerList) {
			throw new EasyExcelException(
					"@RequestExcel or @ResponseExcel must be annotated with List<List<>>");
		}
	}
}
