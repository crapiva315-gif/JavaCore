package com.example.array.service;

import com.example.array.entity.CustomArray;
import com.example.array.exception.CustomArrayException;

public interface ArrayService {

    int getMin(CustomArray array) throws CustomArrayException;

    int getMax(CustomArray array) throws CustomArrayException;

    long findSum(CustomArray array) throws CustomArrayException;

    CustomArray sortBubble(CustomArray array) throws CustomArrayException;

    CustomArray sortSelection(CustomArray array) throws CustomArrayException;
}
