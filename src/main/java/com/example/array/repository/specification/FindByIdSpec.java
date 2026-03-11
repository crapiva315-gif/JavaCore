package com.example.array.repository.specification;

import com.example.array.entity.CustomArray;

public class FindByIdSpec implements ArraySpecification {
  private final long id;

  public FindByIdSpec(long id) {
    this.id = id;
  }

  @Override
  public boolean matches(CustomArray customArray) {
    return customArray.getId() == id;
  }
}
