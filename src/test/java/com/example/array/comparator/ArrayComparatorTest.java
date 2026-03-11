package com.example.array.comparator;

import com.example.array.entity.CustomArray;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayComparatorTest {

    @Test
    void testByIdSortsAscending() {
        // given
        CustomArray a = new CustomArray(new int[]{1});
        CustomArray b = new CustomArray(new int[]{2});
        CustomArray c = new CustomArray(new int[]{3});
        List<CustomArray> list = Arrays.asList(c, a, b);
        // when
        list.sort(ArrayComparator.byId());
        // then
        assertEquals(a, list.get(0));
        assertEquals(b, list.get(1));
        assertEquals(c, list.get(2));
    }

    @Test
    void testByLengthSortsAscending() {
        // given
        CustomArray short1 = new CustomArray(new int[]{1});
        CustomArray medium = new CustomArray(new int[]{1, 2, 3});
        CustomArray long1  = new CustomArray(new int[]{1, 2, 3, 4, 5});
        List<CustomArray> list = Arrays.asList(long1, short1, medium);
        // when
        list.sort(ArrayComparator.byLength());
        // then
        assertEquals(1, list.get(0).length());
        assertEquals(3, list.get(1).length());
        assertEquals(5, list.get(2).length());
    }

    @Test
    void testByFirstElementSortsAscending() {
        // given
        CustomArray a = new CustomArray(new int[]{10, 1, 1});
        CustomArray b = new CustomArray(new int[]{5, 99, 99});
        CustomArray c = new CustomArray(new int[]{1, 50, 50});
        List<CustomArray> list = Arrays.asList(a, b, c);
        // when
        list.sort(ArrayComparator.byFirstElement());
        // then
        assertEquals(1,  list.get(0).getElements()[0]);
        assertEquals(5,  list.get(1).getElements()[0]);
        assertEquals(10, list.get(2).getElements()[0]);
    }

    @Test
    void testByFirstElementEmptyArrayComesFirst() {
        // given
        CustomArray empty    = new CustomArray(new int[]{});
        CustomArray nonEmpty = new CustomArray(new int[]{1});
        Comparator<CustomArray> cmp = ArrayComparator.byFirstElement();
        // when
        int result = cmp.compare(empty, nonEmpty);
        // then
        assertTrue(result < 0);
    }

    @Test
    void testBySumSortsAscending() {
        // given
        CustomArray a = new CustomArray(new int[]{1, 2, 3});       // sum=6
        CustomArray b = new CustomArray(new int[]{10, 20, 30});    // sum=60
        CustomArray c = new CustomArray(new int[]{5});             // sum=5
        List<CustomArray> list = Arrays.asList(b, a, c);
        // when
        list.sort(ArrayComparator.bySum());
        // then
        assertEquals(c, list.get(0));
        assertEquals(a, list.get(1));
        assertEquals(b, list.get(2));
    }
}
