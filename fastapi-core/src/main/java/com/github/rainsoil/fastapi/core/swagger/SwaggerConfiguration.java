package com.github.rainsoil.fastapi.core.swagger;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *
 * @author luyanan
 * @since 2023/04/03
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    
    
    @Value("${server.port}")
    private Integer port;
    
    @Value("${server.servlet.context-path:''}")
    private String contextPath;
    
    /**
     * Docket
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     * @since 2022/12/31/031
     */
    @Bean
    public Docket productApi() {
        //schema
        List<GrantType> grantTypes = new ArrayList<>();
        //密码模式
        String passwordTokenUrl =
                "http://localhost:" + port + (StrUtil.isBlank(contextPath) ? "" : contextPath) + "/login";
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(
                passwordTokenUrl);
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        OAuth oAuth = new OAuthBuilder().name("oauth2").grantTypes(grantTypes).build();
        
        //context
        //scope方位
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add(new AuthorizationScope("read", "read  resources"));
        scopes.add(new AuthorizationScope("write", "write resources"));
        scopes.add(new AuthorizationScope("reads", "read all resources"));
        scopes.add(new AuthorizationScope("writes", "write all resources"));
        
        SecurityReference securityReference = new SecurityReference("oauth2",
                scopes.toArray(new AuthorizationScope[] {}));
        SecurityContext securityContext = new SecurityContext(Lists.newArrayList(securityReference),
                PathSelectors.ant("/api/**"));
        //schemas
        List<SecurityScheme> securitySchemes = Lists.newArrayList(oAuth);
        //securyContext
        List<SecurityContext> securityContexts = Lists.newArrayList(securityContext);
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
                .build().securityContexts(securityContexts).securitySchemes(securitySchemes).apiInfo(apiInfo());
        
        
    }
    
    
    /**
     * apiInfo
     *
     * @return springfox.documentation.service.ApiInfo
     * @since 2022/12/31/031
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("knife4j-oauth2-demo").description("")
                .termsOfServiceUrl("https://doc.xiaominfo.com")
                .contact(new Contact("Developers", "https://gitee.com/xiaoym/knife4j", "")).license("Open Source")
                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0").version("1.0.0").build();
        
    }
    
    
}
