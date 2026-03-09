package com.example.array.reader;

import com.example.array.exception.CustomArrayException;

import java.util.List;

public interface ArrayReader {

    List<String> readLines(String filePath) throws CustomArrayException;
}
