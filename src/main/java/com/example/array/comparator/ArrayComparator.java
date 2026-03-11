package com.example.array.comparator;

import com.example.array.entity.CustomArray;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayComparator {
  private ArrayComparator() {
  }

  public static Comparator<CustomArray> byId() {
    return Comparator.comparingLong(CustomArray::getId);
  }

  public static Comparator<CustomArray> byLength() {
    return Comparator.comparingInt(CustomArray::length);
  }

  public static Comparator<CustomArray> byFirstElement() {
    return (a, b) -> {
      if (a.length() == 0 && b.length() == 0) {
        return 0;
      }
      if (a.length() == 0) {
        return -1;
      }
      if (b.length() == 0) {
        return 1;
      }
      return Integer.compare(a.getElements()[0], b.getElements()[0]);
    };
  }

  public static Comparator<CustomArray> bySum() {
    return (a, b) -> {
      long sumA = Arrays.stream(a.getElements()).sum();
      long sumB = Arrays.stream(b.getElements()).sum();
      return Long.compare(sumA, sumB);
    };
  }
}
