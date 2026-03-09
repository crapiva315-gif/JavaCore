package com.example.array;

import com.example.array.entity.CustomArray;
import com.example.array.exception.CustomArrayException;
import com.example.array.service.ArrayParserService;
import com.example.array.service.ArrayService;
import com.example.array.service.impl.ArrayParserServiceImpl;
import com.example.array.service.impl.ArrayServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ArrayParserService parserService = new ArrayParserServiceImpl();
        ArrayService arrayService = new ArrayServiceImpl();

        try {
            List<CustomArray> arrays = parserService.parseFromFile("data/arrays.txt");
            logger.info("Successfully parsed {} arrays from file", arrays.size());

            for (CustomArray array : arrays) {
                logger.info("Array: {}", array);
                int min = arrayService.findMin(array);
                int max = arrayService.findMax(array);
                long sum = arrayService.findSum(array);
                CustomArray bubbleSorted = arrayService.sortBubble(array);
                CustomArray selectionSorted = arrayService.sortSelection(array);

                logger.info("  min={}, max={}, sum={}", min, max, sum);
                logger.info("  bubble sort:    {}", bubbleSorted);
                logger.info("  selection sort: {}", selectionSorted);
            }
        } catch (CustomArrayException e) {
            logger.error("Application error: {}", e.getMessage(), e);
        }
    }
}
