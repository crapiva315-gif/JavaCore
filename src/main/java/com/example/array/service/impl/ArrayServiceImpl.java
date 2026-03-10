package com.example.array.service.impl;

import com.example.array.entity.CustomArray;
import com.example.array.exception.CustomArrayException;
import com.example.array.service.ArrayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayServiceImpl implements ArrayService {

    private static final Logger logger = LogManager.getLogger(ArrayServiceImpl.class);

    @Override
    public int getMin(CustomArray array) throws CustomArrayException {
        if (array == null || array.length() == 0) {
            throw new CustomArrayException("Array is null or empty");
        }
        int[] elements = array.getElements();
        int min = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
        }
        logger.debug("getMin result: {}", min);
        return min;
    }

    @Override
    public int getMax(CustomArray array) throws CustomArrayException {
        if (array == null || array.length() == 0) {
            throw new CustomArrayException("Array is null or empty");
        }
        int[] elements = array.getElements();
        int max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        logger.debug("getMax result: {}", max);
        return max;
    }

    @Override
    public long findSum(CustomArray array) throws CustomArrayException {
        if (array == null || array.length() == 0) {
            throw new CustomArrayException("Array is null or empty");
        }
        int[] elements = array.getElements();
        long sum = Arrays.stream(elements).asLongStream().sum();
        logger.debug("findSum result: {}", sum);
        return sum;
    }

    @Override
    public CustomArray sortBubble(CustomArray array) throws CustomArrayException {
        if (array == null || array.length() == 0) {
            throw new CustomArrayException("Array is null or empty");
        }
        int[] elements = array.getElements();
        int n = elements.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
        CustomArray sorted = new CustomArray(elements);
        logger.debug("sortBubble result: {}", sorted);
        return sorted;
    }

    @Override
    public CustomArray sortSelection(CustomArray array) throws CustomArrayException {
        if (array == null || array.length() == 0) {
            throw new CustomArrayException("Array is null or empty");
        }
        int[] elements = array.getElements();
        int n = elements.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (elements[j] < elements[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = elements[minIndex];
            elements[minIndex] = elements[i];
            elements[i] = temp;
        }
        CustomArray sorted = new CustomArray(elements);
        logger.debug("sortSelection result: {}", sorted);
        return sorted;
    }
}
