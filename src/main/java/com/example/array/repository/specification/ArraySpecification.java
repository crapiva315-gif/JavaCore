package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;

public interface ArraySpecification {
  boolean matches(CustomArray customArray);
}
