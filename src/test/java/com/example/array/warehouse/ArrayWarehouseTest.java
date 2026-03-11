package com.example.array.warehouse;

import com.example.array.entity.CustomArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayWarehouseTest {

    private ArrayWarehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = ArrayWarehouse.getInstance();
    }

    @Test
    void testRecalculateStoresCorrectSum() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5});
        // when
        warehouse.recalculate(array);
        // then
        Optional<IntSummaryStatistics> stats = warehouse.getStatistics(array.getId());
        assertTrue(stats.isPresent());
        assertEquals(15L, stats.get().getSum());
    }

    @Test
    void testRecalculateStoresCorrectMin() {
        // given
        CustomArray array = new CustomArray(new int[]{5, 3, 8, 1, 7});
        // when
        warehouse.recalculate(array);
        // then
        Optional<IntSummaryStatistics> stats = warehouse.getStatistics(array.getId());
        assertTrue(stats.isPresent());
        assertEquals(1, stats.get().getMin());
    }

    @Test
    void testRecalculateStoresCorrectMax() {
        // given
        CustomArray array = new CustomArray(new int[]{5, 3, 8, 1, 7});
        // when
        warehouse.recalculate(array);
        // then
        Optional<IntSummaryStatistics> stats = warehouse.getStatistics(array.getId());
        assertTrue(stats.isPresent());
        assertEquals(8, stats.get().getMax());
    }

    @Test
    void testRecalculateStoresCorrectAverage() {
        // given
        CustomArray array = new CustomArray(new int[]{2, 4, 6});
        // when
        warehouse.recalculate(array);
        // then
        Optional<IntSummaryStatistics> stats = warehouse.getStatistics(array.getId());
        assertTrue(stats.isPresent());
        assertEquals(4.0, stats.get().getAverage());
    }

    @Test
    void testOnArrayChangedUpdatesStats() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});
        warehouse.recalculate(array);
        // when
        array.setElement(0, 100);
        warehouse.onArrayChanged(array);
        // then
        Optional<IntSummaryStatistics> stats = warehouse.getStatistics(array.getId());
        assertTrue(stats.isPresent());
        assertEquals(105L, stats.get().getSum());
    }

    @Test
    void testGetStatisticsReturnsEmptyForUnknownId() {
        // given
        // when
        Optional<IntSummaryStatistics> result = warehouse.getStatistics(-999L);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void testRemoveDeletesStatistics() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});
        warehouse.recalculate(array);
        // when
        warehouse.remove(array.getId());
        // then
        assertTrue(warehouse.getStatistics(array.getId()).isEmpty());
    }

    @Test
    void testGetInstanceReturnsSameInstance() {
        // given
        // when
        ArrayWarehouse first = ArrayWarehouse.getInstance();
        ArrayWarehouse second = ArrayWarehouse.getInstance();
        // then
        assertSame(first, second);
    }
}
