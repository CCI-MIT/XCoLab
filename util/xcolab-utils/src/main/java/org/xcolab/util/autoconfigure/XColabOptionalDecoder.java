package org.xcolab.util.autoconfigure;

import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

/**
 * This is a copy of {@link feign.optionals.OptionalDecoder}, because the original class throws a
 * {@link NullPointerException} when {@link Optional#empty()} is returned by the
 * {@link org.springframework.cloud.openfeign.FeignClient} implementation.
 */
public class XColabOptionalDecoder implements Decoder {

    private final Decoder delegate;

    public XColabOptionalDecoder(Decoder delegate) {
        Objects.requireNonNull(delegate, "Decoder must not be null. ");
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (!isOptional(type)) {
            return delegate.decode(response, type);
        }
        if (response.status() == 404 || response.status() == 204) {
            return Optional.empty();
        }
        Type enclosedType = Util.resolveLastTypeParameter(type, Optional.class);

        /**
         * {@link Optional#ofNullable(Object)} allows the
         * {@link org.springframework.cloud.openfeign.FeignClient} implementation to return
         * {@link Optional#empty()}.
         */
        return Optional.ofNullable(delegate.decode(response, enclosedType));
    }

    private static boolean isOptional(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType().equals(Optional.class);
    }
}
