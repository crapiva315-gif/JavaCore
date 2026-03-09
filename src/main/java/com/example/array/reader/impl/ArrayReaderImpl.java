package com.example.array.reader.impl;

import com.example.array.exception.CustomArrayException;
import com.example.array.reader.ArrayReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArrayReaderImpl implements ArrayReader {

    private static final Logger logger = LogManager.getLogger(ArrayReaderImpl.class);

    @Override
    public List<String> readLines(String filePath) throws CustomArrayException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new CustomArrayException("File not found: " + filePath);
        }
        try {
            List<String> lines = Files.readAllLines(path);
            logger.debug("Read {} lines from file: {}", lines.size(), filePath);
            return lines;
        } catch (IOException e) {
            throw new CustomArrayException("Failed to read file: " + filePath, e);
        }
    }
}
