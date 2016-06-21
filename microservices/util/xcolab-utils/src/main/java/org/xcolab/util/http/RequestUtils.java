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
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.util.http.caching.CacheProvider;
import org.xcolab.util.http.caching.CacheProviderNoOpImpl;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.ServiceNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


@Component
public final class RequestUtils {

    private static String servicesPort = null;

    private static final int CACHE_TIMEOUT = 3;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static CacheProvider cacheProvider = new CacheProviderNoOpImpl();

    private RequestUtils() {
    }

    public static <T> T getFirstFromList(UriComponentsBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference) throws EntityNotFoundException {
        return getFirstFromList(uriBuilder, typeReference, null);
    }

    public static <T> T getFirstFromList(UriComponentsBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference, String cacheQueryIdentifier)
            throws EntityNotFoundException {
        uriBuilder.queryParam("startRecord", 0);
        uriBuilder.queryParam("limitRecord", 1);

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

    public static <T> List<T> getList(UriComponentsBuilder uriBuilder,
            ParameterizedTypeReference<List<T>> typeReference) {
        return getList(uriBuilder, typeReference, null);
    }

    public static <T> List<T> getList(UriComponentsBuilder uriBuilder,
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
        ResponseEntity<List<T>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, typeReference);
        ret = response.getBody();

        if (cacheActive) {
            cacheProvider.add(sanitize(cachePrefix + cacheQueryIdentifier), CACHE_TIMEOUT, ret);
        }
        return ret;
    }

    public static <T> T get(UriComponentsBuilder uriBuilder, Class<T> entityType)
            throws EntityNotFoundException {
        return get(uriBuilder, entityType, null);
    }

    public static <T> T get(UriComponentsBuilder uriBuilder, Class<T> entityType,
            String cacheQueryIdentifier)
            throws EntityNotFoundException {
        try {
            return getUnchecked(uriBuilder, entityType, cacheQueryIdentifier);
        } catch (UncheckedEntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public static <T> T getUnchecked(UriComponentsBuilder uriBuilder, Class<T> entityType) {
        return getUnchecked(uriBuilder, entityType, null);
    }

    public static <T> T getUnchecked(UriComponentsBuilder uriBuilder, Class<T> entityType,
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
            ret = restTemplate.getForObject(uriBuilder.build().toString(), entityType);
            if (cacheActive) {
                cacheProvider.add(sanitize(cachePrefix + cacheQueryIdentifier), CACHE_TIMEOUT, ret);
            }
            return ret;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                if (isNotFoundException(e)) {
                    throw new UncheckedEntityNotFoundException();
                }
                throw new ServiceNotFoundException(uriBuilder.build().toString());
            }
            throw e;
        }
    }

    public static int getCount(UriComponentsBuilder uriBuilder) {
        return getCount(uriBuilder, Object.class, null);
    }

    public static int getCount(UriComponentsBuilder uriBuilder,
            Class<?> entityType, String cacheQueryIdentifier) {
        Integer ret;
        final boolean cacheActive = cacheProvider.isActive() && cacheQueryIdentifier != null;
        final String cachePrefix = "_" + entityType.getSimpleName()  + "_count_";
        if (cacheActive) {
            //noinspection unchecked
            ret = (Integer) cacheProvider.get(sanitize(cachePrefix + cacheQueryIdentifier));
            if (ret != null) {
                return ret;
            }
        }

        try {
            final HttpHeaders httpHeaders = restTemplate
                    .headForHeaders(uriBuilder.build().toString());
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
                throw new ServiceNotFoundException(uriBuilder.build().toString());
            }
            throw e;
        }
    }

    private static boolean isNotFoundException(HttpClientErrorException e) {
        return e.getResponseBodyAsString().contains("NotFoundException");
    }

    public static  boolean put(UriComponentsBuilder uriBuilder) {
        return put(uriBuilder, null, null);
    }

    public static <T> boolean put(UriComponentsBuilder uriBuilder, T entity) {
        return put(uriBuilder, entity, null);
    }

    public static <T> boolean put(UriComponentsBuilder uriBuilder, T entity, String cacheKey) {

        final boolean cacheActive = cacheProvider.isActive() && cacheKey != null;
        if (cacheActive) {
            cacheProvider.replace(cacheKey, CACHE_TIMEOUT, entity);
        }

        HttpEntity<T> httpEntity = new HttpEntity<>(entity);

        restTemplate
                .exchange(uriBuilder.build().toString(), HttpMethod.PUT, httpEntity, Void.class);
        return true;
    }

    public static boolean delete(UriComponentsBuilder uriBuilder) {
        restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.DELETE, null, Void.class);
        return true;
    }

    public static <T> T post(UriComponentsBuilder uriBuilder, Object entity, Class<T> returnType) {
        return restTemplate.postForObject(uriBuilder.build().toString(), entity, returnType);
    }

    private static String sanitize(String identifier) {
        return identifier.replaceAll("\\s", "+");
    }

    public static void setCacheProvider(CacheProvider cacheProvider) {
        RequestUtils.cacheProvider = cacheProvider;
    }

    public static String getServicesPort() {

        if(servicesPort != null){ return servicesPort;}
        else {
            Properties prop = new Properties();
            String servicesPort = "";

            try {

                InputStream inputStream =
                        RequestUtils.class.getClassLoader().getResourceAsStream("application.properties");
                prop.load(inputStream);
                servicesPort = prop.getProperty("services.port");
                RequestUtils.servicesPort = servicesPort;
            } catch (IOException e) {
                e.printStackTrace();
                RequestUtils.servicesPort = "8080";
            }
            return servicesPort;
        }

    }
}
