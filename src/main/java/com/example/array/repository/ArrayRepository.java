package com.example.array.repository;

import com.example.array.entity.CustomArray;
import com.example.array.repository.specification.ArraySpecification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepository {

  void add(CustomArray array);

  void remove(long id);

  Optional<CustomArray> findById(long id);

  List<CustomArray> getAll();

  List<CustomArray> findBySpecification(ArraySpecification specification);
  List<CustomArray> sort(Comparator<CustomArray> comparator);

}
