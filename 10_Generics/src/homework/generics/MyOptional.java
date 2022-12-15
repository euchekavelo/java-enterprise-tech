package homework.generics;

public class MyOptional {
    static MyOptional of(Object value) {
        return new MyOptional();
    }

    static MyOptional ofNullable(Object value) {
        return new MyOptional();
    }

    public Object get() {
        return new Object();
    }

    public boolean isPresent() {
        return true;
    }

    public Object orElse(Object other) {
        return other;
    }
}
