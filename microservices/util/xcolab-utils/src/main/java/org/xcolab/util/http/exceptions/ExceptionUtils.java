package org.xcolab.util.http.exceptions;

import java.util.Optional;

public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static <T> T getOrNull(CheckedSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (EntityNotFoundException | UncheckedEntityNotFoundException e) {
            return null;
        }
    }

    public static <T> Optional<T> getOptional(CheckedSupplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (EntityNotFoundException | UncheckedEntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @FunctionalInterface
    public interface CheckedSupplier<T> {

        public T get() throws EntityNotFoundException;
    }

}
