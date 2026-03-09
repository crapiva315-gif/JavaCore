package com.example.array.service.impl;

import com.example.array.entity.CustomArray;
import com.example.array.exception.CustomArrayException;
import com.example.array.factory.CustomArrayFactory;
import com.example.array.reader.ArrayReader;
import com.example.array.reader.impl.ArrayReaderImpl;
import com.example.array.service.ArrayParserService;
import com.example.array.validator.ArrayValidator;
import com.example.array.validator.impl.ArrayValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArrayParserServiceImpl implements ArrayParserService {

    private static final Logger logger = LogManager.getLogger(ArrayParserServiceImpl.class);

    private static final String DELIMITER_REGEX = "\\s*[;,]\\s*|(?<=\\d)\\s*-\\s*(?=\\d)|\\s+";
    private final ArrayReader reader = new ArrayReaderImpl();
    private final ArrayValidator validator = new ArrayValidatorImpl();

    @Override
    public List<CustomArray> parseFromFile(String filePath) throws CustomArrayException {
        List<String> lines = reader.readLines(filePath);
        List<CustomArray> arrays = new ArrayList<>();
        for (String line : lines) {
            if (!validator.isValidLine(line)) {
                logger.warn("Skipping invalid or empty line: '{}'", line);
                continue;
            }
            String trimmed = line.trim();
            String[] numbers = trimmed.split(DELIMITER_REGEX);
            int[] elements = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                elements[i] = Integer.parseInt(numbers[i].trim());
            }
            CustomArray array = CustomArrayFactory.createArray(elements);
            arrays.add(array);
            logger.info("Parsed array from line '{}': {}", line, array);
        }
        return arrays;
    }
}
