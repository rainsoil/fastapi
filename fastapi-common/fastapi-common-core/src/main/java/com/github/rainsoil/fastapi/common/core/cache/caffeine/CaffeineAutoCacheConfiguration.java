package com.github.rainsoil.fastapi.common.core.cache.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * cache缓存自动配置类
 *
 * @author luyanan
 * @since 2023/05/19
 **/
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnClass({Caffeine.class, CaffeineCacheManager.class})
@AutoConfigureBefore(CacheAutoConfiguration.class)
public class CaffeineAutoCacheConfiguration {
	@Bean
	@ConditionalOnMissingBean
	public CacheManagerCustomizers cacheManagerCustomizers(ObjectProvider<CacheManagerCustomizer<?>> customizers) {
		return new CacheManagerCustomizers(customizers.orderedStream().collect(Collectors.toList()));
	}

	@Bean("cacheResolver")
	public CacheManager cacheManager(CacheProperties cacheProperties,
									 CacheManagerCustomizers customizers,
									 ObjectProvider<Caffeine<Object, Object>> caffeine,
									 ObjectProvider<CaffeineSpec> caffeineSpec,
									 ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
		CaffeineAutoCacheManager cacheManager = createCacheManager(cacheProperties, caffeine, caffeineSpec, cacheLoader);
		List<String> cacheNames = cacheProperties.getCacheNames();
		if (!CollectionUtils.isEmpty(cacheNames)) {
			cacheManager.setCacheNames(cacheNames);
		}
		return customizers.customize(cacheManager);
	}

	private static CaffeineAutoCacheManager createCacheManager(CacheProperties cacheProperties,
															   ObjectProvider<Caffeine<Object, Object>> caffeine, ObjectProvider<CaffeineSpec> caffeineSpec,
															   ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
		CaffeineAutoCacheManager cacheManager = new CaffeineAutoCacheManager();
		setCacheBuilder(cacheProperties, caffeineSpec.getIfAvailable(), caffeine.getIfAvailable(), cacheManager);
		cacheLoader.ifAvailable(cacheManager::setCacheLoader);
		return cacheManager;
	}

	private static void setCacheBuilder(CacheProperties cacheProperties,
										@Nullable CaffeineSpec caffeineSpec,
										@Nullable Caffeine<Object, Object> caffeine,
										CaffeineCacheManager cacheManager) {
		String specification = cacheProperties.getCaffeine().getSpec();
		if (StringUtils.hasText(specification)) {
			cacheManager.setCacheSpecification(specification);
		} else if (caffeineSpec != null) {
			cacheManager.setCaffeineSpec(caffeineSpec);
		} else if (caffeine != null) {
			cacheManager.setCaffeine(caffeine);
		}
	}
}
