package com.example.array.repository.impl;

import com.example.array.entity.CustomArray;
import com.example.array.repository.ArrayRepository;
import com.example.array.repository.specification.ArraySpecification;
import com.example.array.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ArrayRepositoryImpl implements ArrayRepository {
  private static final Logger logger = LogManager.getLogger(ArrayRepositoryImpl.class);
  private static ArrayRepositoryImpl instance;
  private final List<CustomArray> arrays = new ArrayList<>();
  private final ArrayWarehouse warehouse = ArrayWarehouse.getInstance();

  private ArrayRepositoryImpl() {
  }

  public static ArrayRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new ArrayRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void add(CustomArray array) {
    array.addSubscriber(warehouse);
    warehouse.recalculate(array);
    arrays.add(array);
    logger.info("Added array id={} to repository", array.getId());
  }

  @Override
  public void remove(long id) {
    Optional<CustomArray> found = findById(id);
    if (found.isPresent()) {
      CustomArray array = found.get();
      array.removeSubscriber(warehouse);
      arrays.remove(array);
      logger.info("Removed array id={} from repository", id);
    } else {
      logger.warn("Array id={} not found for removal", id);
    }
  }

  @Override
  public Optional<CustomArray> findById(long id) {
    for (CustomArray array : arrays) {
      if (array.getId() == id) {
        return Optional.of(array);
      }
    }
    return Optional.empty();
  }

  @Override
  public List<CustomArray> getAll() {
    return new ArrayList<>(arrays);
  }

  @Override
  public List<CustomArray> findBySpecification(ArraySpecification specification) {
    List<CustomArray> result = new ArrayList<>();
    for (CustomArray array : arrays) {
      if (specification.matches(array)) {
        result.add(array);
      }
    }
    return result;
  }

  @Override
  public List<CustomArray> sort(Comparator<CustomArray> comparator) {
    return arrays.stream().sorted(comparator).toList();
  }
}
