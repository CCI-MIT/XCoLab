package org.xcolab.util.http.caching.validation;

public interface CacheValidator<T> {

    boolean handles(Class<T> type);

    boolean isValid(T entity);

    Class<T> getEntityType();
}
