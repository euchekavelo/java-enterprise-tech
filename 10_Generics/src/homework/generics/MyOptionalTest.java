package homework.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MyOptionalTest {
    private static final String STRING = "skillbox.ru";

    @DisplayName("of and get test")
    @Test
    void ofAndGetTest() {
        assertDoesNotThrow(() -> MyOptional.of(""));
        assertDoesNotThrow(() -> {
            var my = MyOptional.of(1d);
            my.get();
        });
        var str = MyOptional.of(STRING);
        assertEquals(STRING, str.get());
        assertThrows(InvalidParameterException.class, () -> MyOptional.of(null));
    }

    @DisplayName("ofNullable test")
    @Test
    void ofNullableTest() {
        assertDoesNotThrow(() -> MyOptional.ofNullable(null));

        var opt1 = MyOptional.ofNullable(null);
        var opt2 = MyOptional.ofNullable(null);
        assertEquals(opt1, opt2);

        var str = MyOptional.ofNullable(STRING);
        assertEquals(STRING, str.get());
    }

    @DisplayName("isPresent test")
    @Test
    void isPresentTest() {
        var opt1 = MyOptional.ofNullable(null);
        assertFalse(opt1.isPresent());
        var opt2 = MyOptional.ofNullable(STRING);
        assertTrue(opt2.isPresent());
        var opt3 = MyOptional.of(STRING);
        assertTrue(opt3.isPresent());
    }

    @DisplayName("orElse test")
    @Test
    void orElseTest() {
        var opt1 = MyOptional.ofNullable(null).orElse(STRING);
        assertEquals(STRING, opt1);
        var opt2 = MyOptional.ofNullable("STRING").orElse(STRING);
        assertEquals("STRING", opt2);
    }
}
