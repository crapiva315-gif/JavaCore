package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;

public class FindByLengthSpec implements ArraySpecification {
  private final int length;
  private final ComparisonOperator operator;

  public FindByLengthSpec(int length, ComparisonOperator operator) {
    this.length = length;
    this.operator = operator;
  }

  @Override
  public boolean matches(CustomArray customArray) {
    int arrayLength = customArray.length();
    return switch (operator) {
      case GREATER_THAN -> arrayLength > length;
      case LESS_THAN -> arrayLength < length;
      case EQUAL_TO -> arrayLength == length;
    };
  }
}
