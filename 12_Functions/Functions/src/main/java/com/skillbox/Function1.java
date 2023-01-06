package com.skillbox;

@FunctionalInterface
public interface Function1<T, R> {

    R apply(T t);

    default <V> Function1<T, V> compose(Function1<R, V> after) {
        return t -> after.apply(apply(t));
    }
}
