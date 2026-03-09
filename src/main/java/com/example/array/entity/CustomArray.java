package com.example.array.entity;

import java.util.Arrays;

public class CustomArray {

    private int[] elements;

    public CustomArray(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public int[] getElements() {
        return Arrays.copyOf(elements, elements.length);
    }

    public void setElements(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public int length() {
        return elements.length;
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
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        return "CustomArray{elements=" + Arrays.toString(elements) + "}";
    }
}
