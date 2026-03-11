package com.example.array.observer;

import com.example.array.entity.CustomArray;

public interface ArraySubscriber {
  void onArrayChanged(CustomArray array);
}
