package com.example.array.warehouse;

import com.example.array.entity.CustomArray;
import com.example.array.observer.ArraySubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ArrayWarehouse implements ArraySubscriber {
  private static final Logger logger = LogManager.getLogger(ArrayWarehouse.class);
  private static ArrayWarehouse instance;
  private final Map<Long, IntSummaryStatistics> statisticsMap = new HashMap<>();

  private ArrayWarehouse() {
  }

  public static ArrayWarehouse getInstance() {
    if (instance == null) {
      instance = new ArrayWarehouse();
    }
    return instance;
  }

  @Override
  public void onArrayChanged(CustomArray array) {
    recalculate(array);
  }

  public void recalculate(CustomArray customArray) {
    IntSummaryStatistics summaryStatistics = Arrays.stream(customArray.getElements()).summaryStatistics();
    statisticsMap.put(customArray.getId(), summaryStatistics);
    logger.debug("Recalculated statistics for array id={}: {}", customArray.getId(), summaryStatistics);
  }

  public Optional<IntSummaryStatistics> getStatistics(long arrayId) {
    return Optional.ofNullable(statisticsMap.get(arrayId));
  }

  public void remove(long arrayId) {
    statisticsMap.remove(arrayId);
    logger.debug("Removed statistics for array id={}", arrayId);
  }
}
