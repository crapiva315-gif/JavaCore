package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;
import com.example.array.warehouse.ArrayWarehouse;

import java.util.IntSummaryStatistics;
import java.util.Optional;

public class FindBySumSpecification implements ArraySpecification {

  private final long sum;
  private final ComparisonOperator operator;
  private final ArrayWarehouse warehouse = ArrayWarehouse.getInstance();

  public FindBySumSpecification(long sum, ComparisonOperator operator) {
    this.sum = sum;
    this.operator = operator;
  }

  @Override
  public boolean matches(CustomArray array) {
    Optional<IntSummaryStatistics> statistics = warehouse.getStatistics(array.getId());
    if (statistics.isEmpty()) {
      return false;
    }
    long ArraySum = statistics.get().getSum();
    return switch (operator) {
      case GREATER_THAN -> ArraySum > this.sum;
      case LESS_THAN    -> ArraySum < this.sum;
      case EQUAL_TO     -> ArraySum == this.sum;
    };
  }
}
