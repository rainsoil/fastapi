package com.github.rainsoil.fastapi.common.core.cache.caffeine;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * caffeine缓存自动配置超时时间
 *
 * @author luyanan
 * @since 2023/05/19
 **/
public class CaffeineAutoCacheManager extends CaffeineCacheManager {

	private static final Field CACHE_LOADER_FIELD;

	static {
		CACHE_LOADER_FIELD = Objects.requireNonNull(ReflectionUtils.findField(CaffeineCacheManager.class, "cacheLoader"));
		ReflectionUtils.makeAccessible(CACHE_LOADER_FIELD);
	}

	@Nullable
	private CaffeineSpec caffeineSpec = null;

	public CaffeineAutoCacheManager() {
		super();
	}

	public CaffeineAutoCacheManager(String... cacheNames) {
		super(cacheNames);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	protected CacheLoader<Object, Object> getCacheLoader() {
		return (CacheLoader<Object, Object>) ReflectionUtils.getField(CACHE_LOADER_FIELD, this);
	}

	@Override
	public void setCaffeine(Caffeine<Object, Object> caffeine) {
		throw new IllegalArgumentException("mica-caffeine not support customization Caffeine bean，you can customize CaffeineSpec bean.");
	}

	@Override
	public void setCaffeineSpec(CaffeineSpec caffeineSpec) {
		super.setCaffeineSpec(caffeineSpec);
		this.caffeineSpec = caffeineSpec;
	}

	@Override
	public void setCacheSpecification(String cacheSpecification) {
		super.setCacheSpecification(cacheSpecification);
		this.caffeineSpec = CaffeineSpec.parse(cacheSpecification);
	}

	/**
	 * Build a common Caffeine Cache instance for the specified cache name,
	 * using the common Caffeine configuration specified on this cache manager.
	 *
	 * @param name the name of the cache
	 * @return the native Caffeine Cache instance
	 * @see #createCaffeineCache
	 */
	@Override
	protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
		String[] cacheArrays = name.split(StringPool.HASH);
		if (cacheArrays.length < 2) {
			return super.createNativeCaffeineCache(name);
		}

		// 转换时间，支持时间单位例如：300ms，第二个参数是默认单位
		Duration duration = DurationStyle.detectAndParse(cacheArrays[1], ChronoUnit.SECONDS);
		Caffeine<Object, Object> cacheBuilder;
		if (this.caffeineSpec != null) {
			cacheBuilder = Caffeine.from(caffeineSpec);
		} else {
			cacheBuilder = Caffeine.newBuilder();
		}
		CacheLoader<Object, Object> cacheLoader = getCacheLoader();
		if (cacheLoader == null) {
			return cacheBuilder.expireAfterAccess(duration).build();
		} else {
			return cacheBuilder.expireAfterAccess(duration).build(cacheLoader);
		}
	}

}
