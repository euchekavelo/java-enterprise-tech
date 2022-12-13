package homework.exceptions;

import java.util.Optional;

public class ExceptionTask {

    public static Optional<String> mergeStrings(String first, String second) {
        return Optional.of(first.length() > second.length() ? first + second : second + first);
    }

    public static long getPower(int n, int p) throws Exception {
        if (n < 0 || p < 0) {
            throw new Exception();
        } else if (n == 0 && p == 0) {
            throw new Exception();
        } else {
            return (long) (Math.pow(n, p));
        }
    }

    public static void customException(int a) {
        throwUncheckedException();
    }

    public static void exceptionProcessing() throws Exception {
        throwCheckedException();
        throwUncheckedException();
    }

    private static void throwCheckedException() throws Exception {
        throw new Exception("Checked exception");
    }

    private static void throwUncheckedException() {
        throw new RuntimeException("Unchecked exception");
    }
}
