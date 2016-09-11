package org.xcolab.util.functions;

//TODO: replace with java.util.function.Supplier once we move to Java 8
public interface Supplier<T> {
    T get();
}