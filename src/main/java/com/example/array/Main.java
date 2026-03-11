package com.example.array;

import com.example.array.entity.CustomArray;
import com.example.array.factory.CustomArrayFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        CustomArray customArray = CustomArrayFactory.createArray(new int[]{1,4,5,2,3});
        IntSummaryStatistics statistics = IntStream.of(customArray.getElements()).summaryStatistics();
        System.out.println(statistics);
    }
}
