package com.example.array.service;

import com.example.array.entity.CustomArray;
import com.example.array.exception.CustomArrayException;
import com.example.array.service.impl.ArrayServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayServiceTest {

    private static final CustomArray ARRAY = new CustomArray(new int[]{3, 1, 4, 1, 5, 9, 2, 6});
    private static final CustomArray SINGLE = new CustomArray(new int[]{42});
    private static final CustomArray NEGATIVES = new CustomArray(new int[]{-5, -1, -10, -3});

    private final ArrayService service = new ArrayServiceImpl();

    @Test
    void testGetMinReturnsMinimum() throws CustomArrayException {
        // given
        // when
        int result = service.getMin(ARRAY);
        // then
        assertEquals(1, result);
    }

    @Test
    void testGetMinSingleElement() throws CustomArrayException {
        // given
        // when
        int result = service.getMin(SINGLE);
        // then
        assertEquals(42, result);
    }

    @Test
    void testGetMinNegatives() throws CustomArrayException {
        // given
        // when
        int result = service.getMin(NEGATIVES);
        // then
        assertEquals(-10, result);
    }

    @Test
    void testGetMinThrowsOnEmpty() {
        // given
        CustomArray empty = new CustomArray(new int[]{});
        // when
        // then
        assertThrows(CustomArrayException.class, () -> service.getMin(empty));
    }

    @Test
    void testGetMaxReturnsMaximum() throws CustomArrayException {
        // given
        // when
        int result = service.getMax(ARRAY);
        // then
        assertEquals(9, result);
    }

    @Test
    void testGetMaxNegatives() throws CustomArrayException {
        // given
        // when
        int result = service.getMax(NEGATIVES);
        // then
        assertEquals(-1, result);
    }

    @Test
    void testGetMaxThrowsOnEmpty() {
        // given
        CustomArray empty = new CustomArray(new int[]{});
        // when
        // then
        assertThrows(CustomArrayException.class, () -> service.getMax(empty));
    }

    @Test
    void testFindSumReturnsCorrectSum() throws CustomArrayException {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5});
        // when
        long result = service.findSum(array);
        // then
        assertEquals(15L, result);
    }

    @Test
    void testFindSumWithNegatives() throws CustomArrayException {
        // given
        CustomArray array = new CustomArray(new int[]{-3, 3, -1, 1});
        // when
        long result = service.findSum(array);
        // then
        assertEquals(0L, result);
    }

    @Test
    void testFindSumThrowsOnEmpty() {
        // given
        CustomArray empty = new CustomArray(new int[]{});
        // when
        // then
        assertThrows(CustomArrayException.class, () -> service.findSum(empty));
    }

    @Test
    void testSortBubbleReturnsSortedArray() throws CustomArrayException {
        // given
        CustomArray array = new CustomArray(new int[]{5, 3, 1, 4, 2});
        CustomArray expected = new CustomArray(new int[]{1, 2, 3, 4, 5});
        // when
        CustomArray result = service.sortBubble(array);
        // then
        assertEquals(expected, result);
    }

    @Test
    void testSortBubbleAlreadySorted() throws CustomArrayException {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});
        CustomArray expected = new CustomArray(new int[]{1, 2, 3});
        // when
        CustomArray result = service.sortBubble(array);
        // then
        assertEquals(expected, result);
    }

    @Test
    void testSortBubbleDoesNotMutateOriginal() throws CustomArrayException {
        // given
        CustomArray original = new CustomArray(new int[]{5, 3, 1});
        CustomArray snapshot = new CustomArray(new int[]{5, 3, 1});
        // when
        service.sortBubble(original);
        // then
        assertEquals(snapshot, original);
    }

    @Test
    void testSortSelectionReturnsSortedArray() throws CustomArrayException {
        // given
        CustomArray array = new CustomArray(new int[]{5, 3, 1, 4, 2});
        CustomArray expected = new CustomArray(new int[]{1, 2, 3, 4, 5});
        // when
        CustomArray result = service.sortSelection(array);
        // then
        assertEquals(expected, result);
    }

    @Test
    void testSortSelectionWithNegatives() throws CustomArrayException {
        // given
        CustomArray array = new CustomArray(new int[]{0, -3, 5, -1, 2});
        CustomArray expected = new CustomArray(new int[]{-3, -1, 0, 2, 5});
        // when
        CustomArray result = service.sortSelection(array);
        // then
        assertEquals(expected, result);
    }

    @Test
    void testSortSelectionDoesNotMutateOriginal() throws CustomArrayException {
        // given
        CustomArray original = new CustomArray(new int[]{5, 3, 1});
        CustomArray snapshot = new CustomArray(new int[]{5, 3, 1});
        // when
        service.sortSelection(original);
        // then
        assertEquals(snapshot, original);
    }
}
