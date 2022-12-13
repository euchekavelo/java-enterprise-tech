package homework.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class ExceptionTest {
    @DisplayName("Exception catch test")
    @Test
    void catchException() {
        assertDoesNotThrow(ExceptionTask::exceptionProcessing);
    }

    @DisplayName("Custom exception catch test")
    @Test
    void catchCustomException() {
        assertDoesNotThrow(() -> ExceptionTask.customException(1));
        assertDoesNotThrow(() -> ExceptionTask.customException(10));
        assertThrows(InvalidZeroParameterException.class, () -> ExceptionTask.customException(0));
    }

    @DisplayName("getPower test")
    @Test
    void getPowerTest() throws Exception {
        assertDoesNotThrow(() -> ExceptionTask.getPower(10, 10));
        assertDoesNotThrow(() -> ExceptionTask.getPower(1, 1));
        assertNotEquals(-1L, ExceptionTask.getPower(1, 1));
        assertNotEquals(-1L, ExceptionTask.getPower(1, 1));
        assertEquals(-1L, ExceptionTask.getPower(-1, 1));
        assertEquals(-1L, ExceptionTask.getPower(0, 1));
    }

    @DisplayName("Optional test")
    @Test
    void optionalTest() {
        assertEquals(Optional.empty(), ExceptionTask.mergeStrings(null, null));
        assertEquals(Optional.empty(), ExceptionTask.mergeStrings("", ""));
        assertEquals("null", ExceptionTask.mergeStrings("", "null").orElseThrow());
        assertEquals("null", ExceptionTask.mergeStrings(null, "null").orElseThrow());
        assertEquals("bbbaa", ExceptionTask.mergeStrings("aa", "bbb").orElseThrow());
        assertEquals("aab", ExceptionTask.mergeStrings("aa", "b").orElseThrow());
    }
}
