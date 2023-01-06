package com.skillbox;

@FunctionalInterface
public interface Predicate<T> {

    Predicate ALWAYS_TRUE = t -> true;
    Predicate ALWAYS_FALSE = t -> false;

    boolean test(T t);

    default Predicate<T> or(Predicate<T> other) {
        return t -> test(t) || other.test(t);
    }

    default Predicate<T> and(Predicate<T> other) {
        return t -> test(t) && other.test(t);
    }

    default Predicate<T> not() {
        return t -> !test(t);
    }
}
