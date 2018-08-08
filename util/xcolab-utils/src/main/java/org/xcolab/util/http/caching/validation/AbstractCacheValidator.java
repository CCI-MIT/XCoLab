package org.xcolab.util.http.caching.validation;

public abstract class AbstractCacheValidator<T> implements CacheValidator<T> {

    private final Class<T> entityType;

    protected AbstractCacheValidator(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public boolean handles(Class<T> type) {
        return entityType.equals(type);
    }

    @Override
    public Class<T> getEntityType() {
        return entityType;
    }
}
