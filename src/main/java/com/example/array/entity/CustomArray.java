package com.example.array.entity;

import com.example.array.observer.ArraySubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomArray {
  private static long idCounter = 0;
  private final long id;
  private int[] elements;
  private final List<ArraySubscriber> subscribers = new ArrayList<>();

  public CustomArray(int[] elements) {
    this.id = ++idCounter;
    this.elements = Arrays.copyOf(elements, elements.length);
  }

  public int[] getElements() {
    return Arrays.copyOf(elements, elements.length);
  }

  public void setElement(int index, int value) {
    this.elements[index] = value;
    notifySubscribers();
  }

  public void setElements(int[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
    notifySubscribers();
  }

  public int length() {
    return elements.length;
  }

  public long getId() {
    return id;
  }

  public void addSubscriber(ArraySubscriber arraySubscriber) {
    subscribers.add(arraySubscriber);
  }

  public void notifySubscribers() {
    for (ArraySubscriber arraySubscriber : subscribers) {
      arraySubscriber.onArrayChanged(this);
    }
  }

  public void removeSubscriber(ArraySubscriber arraySubscriber) {
    subscribers.remove(arraySubscriber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomArray that = (CustomArray) o;
    return id == that.id && Arrays.equals(elements, that.elements);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(elements) + Long.hashCode(id);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CustomArray{");
    sb.append("id=").append(id);
    sb.append(", elements=").append(Arrays.toString(elements));
    sb.append('}');
    return sb.toString();
  }


}
