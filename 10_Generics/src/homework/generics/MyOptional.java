package homework.generics;

public class MyOptional<T> {

    private static final MyOptional<?> EMPTY = new MyOptional<>(null);
    private final T value;

    public MyOptional(T value) {
        this.value = value;
    }

    static <T> MyOptional<T> of(T value) {
        if (value == null) {
            throw new InvalidParameterException();
        }

        return new MyOptional<>(value);
    }

    static <T> MyOptional<T> ofNullable(T value) {
        if (value == null) {
            return (MyOptional<T>) EMPTY;
        }

        return new MyOptional<>(value);
    }

    public T get() {
        if (value == null) {
            throw new InvalidParameterException();
        }

        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T orElse(T other) {
        if (value == null) {
            return other;
        } else {
            return value;
        }
    }
}
