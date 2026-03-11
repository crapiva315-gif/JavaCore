package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;
import com.example.array.warehouse.ArrayWarehouse;

import java.util.IntSummaryStatistics;
import java.util.Optional;

public class FindByMinSpec implements ArraySpecification {

  private final int min;
  private final ComparisonOperator operator;
  private final ArrayWarehouse warehouse = ArrayWarehouse.getInstance();

  public FindByMinSpec(int min, ComparisonOperator operator) {
    this.min = min;
    this.operator = operator;
  }

  @Override
  public boolean matches(CustomArray array) {
    Optional<IntSummaryStatistics> statistics = warehouse.getStatistics(array.getId());
    if (statistics.isEmpty()) {
      return false;
    }
    long arrayMin = statistics.get().getMin();
    return switch (operator) {
      case GREATER_THAN -> arrayMin > min;
      case LESS_THAN    -> arrayMin < min;
      case EQUAL_TO     -> arrayMin == min;
    };
  }
}
