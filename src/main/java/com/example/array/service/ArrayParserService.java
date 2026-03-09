package com.example.array.service;

import com.example.array.entity.CustomArray;
import com.example.array.exception.CustomArrayException;

import java.util.List;

public interface ArrayParserService {

    List<CustomArray> parseFromFile(String filePath) throws CustomArrayException;
}
