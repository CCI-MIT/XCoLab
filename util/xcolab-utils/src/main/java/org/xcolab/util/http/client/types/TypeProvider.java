package org.xcolab.util.http.client.types;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class TypeProvider<T> {

    public static final TypeProvider<Long> LONG = new TypeProvider<>(Long.class,
            new ParameterizedTypeReference<List<Long>>() {});
    public static final TypeProvider<Boolean> BOOLEAN = new TypeProvider<>(Boolean.class,
            new ParameterizedTypeReference<List<Boolean>>() {});

    private final Class<T> entityType;
    private final ParameterizedTypeReference<List<T>> typeReference;

    public TypeProvider(Class<T> entityType, ParameterizedTypeReference<List<T>> typeReference) {

        this.entityType = entityType;
        this.typeReference = typeReference;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public ParameterizedTypeReference<List<T>> getTypeReference() {
        return typeReference;
    }
}
