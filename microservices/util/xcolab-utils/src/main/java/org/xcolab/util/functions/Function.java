package org.xcolab.util.functions;

//TODO: replace with java.util.function.Function once we move to Java 8
public interface Function<T, R> {
    R apply(T t);
}