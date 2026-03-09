package com.example.array.validator.impl;

import com.example.array.validator.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayValidatorImpl implements ArrayValidator {

    private static final Logger logger = LogManager.getLogger(ArrayValidatorImpl.class);

    private static final String NUMBER_REGEX = "^-?\\d+$";
    private static final String DELIMITER_REGEX = "\\s*[;,]\\s*|(?<=\\d)\\s*-\\s*(?=\\d)|\\s+";

    @Override
    public boolean isValidLine(String line) {
        if (line == null) {
            logger.warn("Line is null — treated as invalid");
            return false;
        }
        if (line.trim().isEmpty()) {
            logger.debug("Empty line — skipped");
            return false;
        }
        String[] tokens = line.trim().split(DELIMITER_REGEX);
        for (String token : tokens) {
            if (token.trim().isEmpty()) {
                continue;
            }
            if (!token.trim().matches(NUMBER_REGEX)) {
                logger.warn("Invalid token '{}' in line: '{}'", token, line);
                return false;
            }
        }
        return true;
    }
}
