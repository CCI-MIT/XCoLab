package org.xcolab.util.http;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.xcolab.util.functions.Supplier;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheProvider;
import org.xcolab.util.http.caching.CacheProviderNoOpImpl;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.HeaderRequestInterceptor;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.RestTemplateErrorHandler;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Component
public final class RequestUtils {

    private static final RestTemplate restTemplate;

    private static String servicesPort;

    private static CacheProvider cacheProvider = new CacheProviderNoOpImpl();

    static {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        restTemplate.setInterceptors(HeaderRequestInterceptor
                .newAsSingletonList(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));
    }

    private RequestUtils() {
    }


    public static <T> T getFirstFromList(UriBuilder uriBuilder,
                                         ParameterizedTypeReference<List<T>> typeReference) throws EntityNotFoundException {
        return getFirstFromList(uriBuilder, typeReference, null, CacheRetention.NONE);
    }

    public static <T> T getFirstFromList(final UriBuilder uriBuilder,
            final ParameterizedTypeReference<List<T>> typeReference, CacheKey<T, T> cacheKey,
            CacheRetention cacheRetention)
            throws EntityNotFoundException {
        uriBuilder.addRange(0, 1);
        return getCached(cacheRetention, cacheKey, new Supplier<T>() {
                    @Override
                    public T get() {
                        final List<T> list = getList(uriBuilder, typeReference);
                        if (list.isEmpty()) {
                            throw new UncheckedEntityNotFoundException();
                        }
                        return list.get(0);
                    }
                });
    }

    public static <R> List<R> getList(UriBuilder uriBuilder,
                                      ParameterizedTypeReference<List<R>> typeReference) {
        return getList(uriBuilder, typeReference, null, CacheRetention.NONE);
    }

    public static <T, R> List<R> getList(final UriBuilder uriBuilder,
            final ParameterizedTypeReference<List<R>> typeReference,
            final CacheKey<T, List<R>> cacheKey, CacheRetention cacheRetention) {
        return getCached(cacheRetention, cacheKey, new Supplier<List<R>>() {
            @Override
            public List<R> get() {
                ResponseEntity<List<R>> response = restTemplate.exchange(uriBuilder.buildString(),
                        HttpMethod.GET, null, typeReference);
                return response.getBody();
            }
        });
    }

    public static <T> T get(UriBuilder uriBuilder, Class<T> entityType)
            throws EntityNotFoundException {
        return get(uriBuilder, entityType, null, CacheRetention.NONE);
    }

    public static <T, R> R get(UriBuilder uriBuilder, Class<R> entityType,
            CacheKey<T, R> cacheKey, CacheRetention cacheRetention)
            throws EntityNotFoundException {
        try {
            return getUnchecked(uriBuilder, entityType, cacheKey, cacheRetention);
        } catch (UncheckedEntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public static <R> R getUnchecked(UriBuilder uriBuilder, Class<R> returnType) {
        return getUnchecked(uriBuilder, returnType, null, CacheRetention.NONE);
    }

    public static <T, R> R getUnchecked(final UriBuilder uriBuilder, final Class<R> returnType,
            CacheKey<T, R> cacheKey, CacheRetention cacheRetention) {
        return getCached(cacheRetention, cacheKey, new Supplier<R>() {
                    @Override
                    public R get() {
                        return restTemplate.getForObject(uriBuilder.buildString(), returnType);
                    }
                });
    }

    public static int getCount(UriBuilder uriBuilder) {
        return getCount(uriBuilder, null, CacheRetention.REQUEST);
    }

    public static int getCount(final UriBuilder uriBuilder,
            final CacheKey<?, Integer> cacheKey, CacheRetention cacheRetention) {
        return getCached(cacheRetention, cacheKey, new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        final HttpHeaders httpHeaders = restTemplate.headForHeaders(uriBuilder.buildString());
                        final List<String> countHeaders = httpHeaders.get("X-Total-Count");
                        if (countHeaders.isEmpty()) {
                            return 0;
                        }

                        return Integer.valueOf(countHeaders.get(0));
                    }
                });
    }

    private static <T> T getCached(CacheRetention cacheRetention, CacheKey<?, T> cacheKey,
            Supplier<T> supplier) {
        T ret;
        final boolean cacheActive = cacheProvider.isActive() && cacheKey != null
                && cacheRetention != CacheRetention.NONE;
        if (cacheActive) {
            ret = cacheProvider.get(cacheKey, cacheRetention);
            if (ret != null) {
                return ret;
            }
        }
        ret = supplier.get();
        if (cacheActive) {
            cacheProvider.add(cacheKey, cacheRetention, ret);
        }
        return ret;
    }

    public static boolean put(UriBuilder uriBuilder) {
        return put(uriBuilder, null, null);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity) {
        return put(uriBuilder, entity, null);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity, CacheKey<T, T> cacheKey) {

        final boolean cacheActive = cacheProvider.isActive() && cacheKey != null;
        if (cacheActive) {
            cacheProvider.replace(cacheKey, CacheRetention.REQUEST, entity);
        }

        HttpEntity<T> httpEntity = new HttpEntity<>(entity);

        restTemplate.exchange(uriBuilder.buildString(), HttpMethod.PUT, httpEntity, Void.class);
        return true;
    }

    public static boolean delete(UriBuilder uriBuilder) {
        restTemplate.exchange(uriBuilder.buildString(), HttpMethod.DELETE, null, Void.class);
        return true;
    }

    public static <T> T post(UriBuilder uriBuilder, Object entity, Class<T> returnType) {
        return restTemplate.postForObject(uriBuilder.buildString(), entity, returnType);
    }

    public static void setCacheProvider(CacheProvider cacheProvider) {
        RequestUtils.cacheProvider = cacheProvider;
    }

    public static String getServicesPort() {

        if (servicesPort != null) {
            return servicesPort;
        } else {
            Properties prop = new Properties();
            String servicesPort = "";

            final String propertiesPath = System.getProperty("user.home") + File.separator
                    + ".xcolab.application.properties";

            try (InputStream inputStream = new FileInputStream(propertiesPath)) {
                prop.load(inputStream);
                servicesPort = prop.getProperty("services.port");
                RequestUtils.servicesPort = servicesPort;
            } catch (IOException e) {
                RequestUtils.servicesPort = "8080";
            }
            return servicesPort;
        }
    }
}
