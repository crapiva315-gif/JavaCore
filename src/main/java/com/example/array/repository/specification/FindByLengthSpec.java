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
    if (length == 0) {
      return false;
    }
    int ArrayLength = customArray.length();
    return switch (operator) {
      case GREATER_THAN -> ArrayLength > length;
      case LESS_THAN -> ArrayLength < length;
      case EQUAL_TO -> ArrayLength == length;
    };
  }
}
