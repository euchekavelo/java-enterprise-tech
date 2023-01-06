package com.skillbox;

@FunctionalInterface
public interface Function2<T, U, R> {

    R apply(T t, U u);

    default <V> Function2<T, U, V> compose(Function1<R, V> after) {
        return (t, u) -> after.apply(apply(t, u));
    }

    default Function1<U, R> bind1(T t) {
        return u -> apply(t, u);
    }

    default Function1<T, R> bind2(U u) {
        return t -> apply(t, u);
    }
}
