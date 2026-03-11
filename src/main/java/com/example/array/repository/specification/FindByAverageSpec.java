package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;
import com.example.array.warehouse.ArrayWarehouse;

import java.util.IntSummaryStatistics;
import java.util.Optional;

public class FindByAverageSpec implements ArraySpecification {
  private final double avg;
  private final ComparisonOperator operator;
  private final ArrayWarehouse warehouse = ArrayWarehouse.getInstance();

  public FindByAverageSpec(double avg, ComparisonOperator operator) {
    this.avg = avg;
    this.operator = operator;
  }

  @Override
  public boolean matches(CustomArray customArray) {
    Optional<IntSummaryStatistics> statistics = warehouse.getStatistics(customArray.getId());
    if (statistics.isEmpty()) {
      return false;
    }
    double average = statistics.get().getAverage();
    return switch (operator) {
      case GREATER_THAN -> average > avg;
      case LESS_THAN -> average < avg;
      case EQUAL_TO -> Double.compare(average, avg) == 0;
    };
  }
}
