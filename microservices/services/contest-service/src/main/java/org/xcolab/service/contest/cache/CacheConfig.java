package org.xcolab.service.contest.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    public final static String CONTEST_PHASE_TYPE_CACHE = "contestPhaseTypeCache";
    public final static String PROPOSAL_DISCUSSION_THREADS_CACHE = "proposalDiscussionThreadsCache";
    public final static String CONTEST_TEAM_MEMBER_ROLES_CACHE = "contestTeamMemberRoleCache";
    public final static String CONTEST_ACTIVE_PHASE_CACHE = "contestActivePhaseCache";
    public final static String PROPOSAL_TO_PHASE_CACHE = "proposalToPhaseCache";

    @Bean
    @Override
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache contestPhaseTypeCache =
                new GuavaCache(CONTEST_PHASE_TYPE_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        GuavaCache proposalDiscussionThreadsCache =
                new GuavaCache(PROPOSAL_DISCUSSION_THREADS_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        GuavaCache contestTeamMemberRoleCache =
                new GuavaCache(CONTEST_TEAM_MEMBER_ROLES_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        GuavaCache contestActivePhaseCache =
                new GuavaCache(CONTEST_ACTIVE_PHASE_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        GuavaCache proposalToPhaseCache =
                new GuavaCache(PROPOSAL_TO_PHASE_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        cacheManager.setCaches(Arrays.asList(
                contestPhaseTypeCache,
                proposalDiscussionThreadsCache,
                contestTeamMemberRoleCache,
                contestActivePhaseCache,
                proposalToPhaseCache));

        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
