package com.example.array.validator;

import com.example.array.validator.impl.ArrayValidatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ArrayValidatorTest {

    private final ArrayValidator validator = new ArrayValidatorImpl();

    @ParameterizedTest
    @ValueSource(strings = {"1; 2; 3", "3 4 7", "1 - 2 - 3", "5; -1; 15", "42"})
    void testValidLinesReturnTrue(String line) {
        // given
        // when
        boolean result = validator.isValidLine(line);
        // then
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1y1 21 32", "1; 2; x3; 6..5; 77", "100; abc; 300", "!@#"})
    void testInvalidLinesReturnFalse(String line) {
        // given
        // when
        boolean result = validator.isValidLine(line);
        // then
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void testEmptyLinesReturnFalse(String line) {
        // given
        // when
        boolean result = validator.isValidLine(line);
        // then
        assertFalse(result);
    }
}
