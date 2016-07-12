package org.xcolab.util.http;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.xcolab.util.http.caching.CacheProvider;
import org.xcolab.util.http.caching.CacheProviderNoOpImpl;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.ServiceNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Component
public final class RequestUtils {

    private static final int CACHE_TIMEOUT = 3;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static String servicesPort;

    private static CacheProvider cacheProvider = new CacheProviderNoOpImpl();

    private RequestUtils() {
    }

    public static <T> T getFirstFromList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference) throws EntityNotFoundException {
        return getFirstFromList(uriBuilder, typeReference, null);
    }

    public static <T> T getFirstFromList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference, String cacheQueryIdentifier)
            throws EntityNotFoundException {
        uriBuilder.addRange(0, 1);

        final boolean cacheActive = cacheProvider.isActive() && cacheQueryIdentifier != null;
        final String cachePrefix = "_" + typeReference.getType() + "_listFirst_";
        T ret;
        if (cacheActive) {
            //noinspection unchecked
            ret = (T) cacheProvider.get(sanitize(cachePrefix + cacheQueryIdentifier));
            if (ret != null) {
                return ret;
            }
        }

        final List<T> list = getList(uriBuilder, typeReference);
        if (list.isEmpty()) {
            throw new EntityNotFoundException();
        }
        ret = list.get(0);

        if (cacheActive) {
            cacheProvider.add(sanitize(cachePrefix + cacheQueryIdentifier), CACHE_TIMEOUT, ret);
        }

        return ret;
    }

    public static <T> List<T> getList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference) {
        return getList(uriBuilder, typeReference, null);
    }

    public static <T> List<T> getList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference,
            String cacheQueryIdentifier) {
        List<T> ret;
        final boolean cacheActive = cacheProvider.isActive() && cacheQueryIdentifier != null;
        final String cachePrefix = "_" + typeReference.getType() + "_list_";
        if (cacheActive) {
            //noinspection unchecked
            ret = (List<T>) cacheProvider.get(sanitize(cachePrefix + cacheQueryIdentifier));
            if (ret != null) {
                return ret;
            }
        }
        ResponseEntity<List<T>> response = restTemplate.exchange(uriBuilder.buildString(),
                HttpMethod.GET, null, typeReference);
        ret = response.getBody();

        if (cacheActive) {
            cacheProvider.add(sanitize(cachePrefix + cacheQueryIdentifier), CACHE_TIMEOUT, ret);
        }
        return ret;
    }

    public static <T> T get(UriBuilder uriBuilder, Class<T> entityType)
            throws EntityNotFoundException {
        return get(uriBuilder, entityType, null);
    }

    public static <T> T get(UriBuilder uriBuilder, Class<T> entityType,
            String cacheQueryIdentifier)
            throws EntityNotFoundException {
        try {
            return getUnchecked(uriBuilder, entityType, cacheQueryIdentifier);
        } catch (UncheckedEntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public static <T> T getUnchecked(UriBuilder uriBuilder, Class<T> entityType) {
        return getUnchecked(uriBuilder, entityType, null);
    }

    public static <T> T getUnchecked(UriBuilder uriBuilder, Class<T> entityType,
            String cacheQueryIdentifier) {
        try {
            T ret;
            final boolean cacheActive = cacheProvider.isActive() && cacheQueryIdentifier != null;
            final String cachePrefix = "_" + entityType.getSimpleName() + "_";
            if (cacheActive) {
                //noinspection unchecked
                ret = (T) cacheProvider.get(sanitize(cachePrefix + cacheQueryIdentifier));
                if (ret != null) {
                    return ret;
                }
            }
            ret = restTemplate.getForObject(uriBuilder.buildString(), entityType);
            if (cacheActive) {
                cacheProvider.add(sanitize(cachePrefix + cacheQueryIdentifier), CACHE_TIMEOUT, ret);
            }
            return ret;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                if (isNotFoundException(e)) {
                    throw new UncheckedEntityNotFoundException();
                }
                throw new ServiceNotFoundException(uriBuilder.buildString());
            }
            throw e;
        }
    }

    public static int getCount(UriBuilder uriBuilder) {
        return getCount(uriBuilder, Object.class, null);
    }

    public static int getCount(UriBuilder uriBuilder,
            Class<?> entityType, String cacheQueryIdentifier) {
        Integer ret;
        final boolean cacheActive = cacheProvider.isActive() && cacheQueryIdentifier != null;
        final String cachePrefix = "_" + entityType.getSimpleName() + "_count_";
        if (cacheActive) {
            //noinspection unchecked
            ret = (Integer) cacheProvider.get(sanitize(cachePrefix + cacheQueryIdentifier));
            if (ret != null) {
                return ret;
            }
        }

        try {
            final HttpHeaders httpHeaders = restTemplate
                    .headForHeaders(uriBuilder.buildString());
            final List<String> countHeaders = httpHeaders.get("X-Total-Count");
            if (countHeaders.isEmpty()) {
                return 0;
            }

            ret = Integer.valueOf(countHeaders.get(0));
            if (cacheActive) {
                cacheProvider.add(sanitize(cachePrefix + cacheQueryIdentifier), CACHE_TIMEOUT, ret);
            }
            return ret;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ServiceNotFoundException(uriBuilder.buildString());
            }
            throw e;
        }
    }

    private static boolean isNotFoundException(HttpClientErrorException e) {
        return e.getResponseBodyAsString().contains("NotFoundException");
    }

    public static boolean put(UriBuilder uriBuilder) {
        return put(uriBuilder, null, null);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity) {
        return put(uriBuilder, entity, null);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity, String cacheKey) {

        final boolean cacheActive = cacheProvider.isActive() && cacheKey != null;
        if (cacheActive) {
            cacheProvider.replace(cacheKey, CACHE_TIMEOUT, entity);
        }

        HttpEntity<T> httpEntity = new HttpEntity<>(entity);

        restTemplate
                .exchange(uriBuilder.buildString(), HttpMethod.PUT, httpEntity, Void.class);
        return true;
    }

    public static boolean delete(UriBuilder uriBuilder) {
        restTemplate.exchange(uriBuilder.buildString(), HttpMethod.DELETE, null, Void.class);
        return true;
    }

    public static <T> T post(UriBuilder uriBuilder, Object entity, Class<T> returnType) {
        return restTemplate.postForObject(uriBuilder.buildString(), entity, returnType);
    }

    private static String sanitize(String identifier) {
        return identifier.replaceAll("\\s", "+");
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

            try {
                final String propertiesPath = System.getProperty("user.home") + File.separator
                        + ".xcolab.application.properties";
                InputStream inputStream = new FileInputStream(propertiesPath);
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
