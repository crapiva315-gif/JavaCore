package com.example.array.factory;

import com.example.array.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomArrayFactory {

  private static final Logger logger = LogManager.getLogger(CustomArrayFactory.class);

  private CustomArrayFactory() {
  }

  public static CustomArray createArray(int[] elements) {
    CustomArray array = new CustomArray(elements);
    logger.debug("Created CustomArray: {}", array);
    return array;
  }
}
