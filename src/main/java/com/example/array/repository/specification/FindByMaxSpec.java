package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;
import com.example.array.warehouse.ArrayWarehouse;

import java.util.IntSummaryStatistics;
import java.util.Optional;

public class FindByMaxSpec implements ArraySpecification {

  private final int max;
  private final ComparisonOperator operator;
  private final ArrayWarehouse warehouse = ArrayWarehouse.getInstance();

  public FindByMaxSpec(int max, ComparisonOperator operator) {
    this.max = max;
    this.operator = operator;
  }

  @Override
  public boolean matches(CustomArray array) {
    Optional<IntSummaryStatistics> statistics = warehouse.getStatistics(array.getId());
    if (statistics.isEmpty()) {
      return false;
    }
    long arrayMax = statistics.get().getMax();
    return switch (operator) {
      case GREATER_THAN -> arrayMax > max;
      case LESS_THAN    -> arrayMax < max;
      case EQUAL_TO     -> arrayMax == max;
    };
  }
}
