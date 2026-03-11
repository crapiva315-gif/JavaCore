package com.example.array.repository;

import com.example.array.comparator.ArrayComparator;
import com.example.array.entity.CustomArray;
import com.example.array.repository.impl.ArrayRepositoryImpl;
import com.example.array.repository.specification.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryTest {

    // sum=6, avg=2.0, min=1, max=3, length=3
    private static final CustomArray ARRAY_A = new CustomArray(new int[]{3, 1, 2});
    // sum=100, avg=25.0, min=10, max=40, length=4
    private static final CustomArray ARRAY_B = new CustomArray(new int[]{10, 20, 30, 40});
    // sum=5, avg=5.0, min=5, max=5, length=1
    private static final CustomArray ARRAY_C = new CustomArray(new int[]{5});

    private ArrayRepository repository;

    @BeforeEach
    void setUp() {
        repository = ArrayRepositoryImpl.getInstance();
        for (CustomArray a : repository.getAll()) {
            repository.remove(a.getId());
        }
        repository.add(ARRAY_A);
        repository.add(ARRAY_B);
        repository.add(ARRAY_C);
    }

    // --- add / remove / findById ---

    @Test
    void testGetAllReturnsAllArrays() {
        // given
        // when
        List<CustomArray> result = repository.getAll();
        // then
        assertEquals(3, result.size());
    }

    @Test
    void testFindByIdReturnsCorrectArray() {
        // given
        long id = ARRAY_A.getId();
        // when
        Optional<CustomArray> result = repository.findById(id);
        // then
        assertTrue(result.isPresent());
        assertEquals(ARRAY_A, result.get());
    }

    @Test
    void testFindByIdReturnsEmptyForMissingId() {
        // given
        // when
        Optional<CustomArray> result = repository.findById(-999L);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void testRemoveDeletesArray() {
        // given
        long id = ARRAY_C.getId();
        // when
        repository.remove(id);
        // then
        assertTrue(repository.findById(id).isEmpty());
        assertEquals(2, repository.getAll().size());
    }

    @Test
    void testGetInstanceReturnsSameInstance() {
        // given
        // when
        ArrayRepository first = ArrayRepositoryImpl.getInstance();
        ArrayRepository second = ArrayRepositoryImpl.getInstance();
        // then
        assertSame(first, second);
    }

    // --- FindByIdSpec ---

    @Test
    void testFindByIdSpec() {
        // given
        FindByIdSpec spec = new FindByIdSpec(ARRAY_B.getId());
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_B, result.get(0));
    }

    // --- FindByLengthSpec ---

    @Test
    void testFindByLengthEqualTo() {
        // given
        FindByLengthSpec spec = new FindByLengthSpec(3, ComparisonOperator.EQUAL_TO);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_A, result.get(0));
    }

    @Test
    void testFindByLengthGreaterThan() {
        // given
        FindByLengthSpec spec = new FindByLengthSpec(1, ComparisonOperator.GREATER_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(2, result.size());
    }

    @Test
    void testFindByLengthLessThan() {
        // given
        FindByLengthSpec spec = new FindByLengthSpec(3, ComparisonOperator.LESS_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_C, result.get(0));
    }

    // --- FindBySumSpecification ---

    @Test
    void testFindBySumGreaterThan() {
        // given
        FindBySumSpecification spec = new FindBySumSpecification(10L, ComparisonOperator.GREATER_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_B, result.get(0));
    }

    @Test
    void testFindBySumLessThan() {
        // given
        FindBySumSpecification spec = new FindBySumSpecification(6L, ComparisonOperator.LESS_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_C, result.get(0));
    }

    @Test
    void testFindBySumEqualTo() {
        // given
        FindBySumSpecification spec = new FindBySumSpecification(6L, ComparisonOperator.EQUAL_TO);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_A, result.get(0));
    }

    // --- FindByMaxSpec ---

    @Test
    void testFindByMaxGreaterThan() {
        // given
        FindByMaxSpec spec = new FindByMaxSpec(4, ComparisonOperator.GREATER_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(2, result.size());
    }

    @Test
    void testFindByMaxEqualTo() {
        // given
        FindByMaxSpec spec = new FindByMaxSpec(5, ComparisonOperator.EQUAL_TO);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_C, result.get(0));
    }

    // --- FindByMinSpec ---

    @Test
    void testFindByMinLessThan() {
        // given
        FindByMinSpec spec = new FindByMinSpec(5, ComparisonOperator.LESS_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_A, result.get(0));
    }

    @Test
    void testFindByMinEqualTo() {
        // given
        FindByMinSpec spec = new FindByMinSpec(5, ComparisonOperator.EQUAL_TO);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_C, result.get(0));
    }

    // --- FindByAverageSpec ---

    @Test
    void testFindByAverageEqualTo() {
        // given
        FindByAverageSpec spec = new FindByAverageSpec(5.0, ComparisonOperator.EQUAL_TO);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(1, result.size());
        assertEquals(ARRAY_C, result.get(0));
    }

    @Test
    void testFindByAverageGreaterThan() {
        // given
        FindByAverageSpec spec = new FindByAverageSpec(4.0, ComparisonOperator.GREATER_THAN);
        // when
        List<CustomArray> result = repository.findBySpecification(spec);
        // then
        assertEquals(2, result.size());
    }

    // --- sort ---

    @Test
    void testSortById() {
        // given
        // when
        List<CustomArray> sorted = repository.sort(ArrayComparator.byId());
        // then
        assertTrue(sorted.get(0).getId() < sorted.get(1).getId());
        assertTrue(sorted.get(1).getId() < sorted.get(2).getId());
    }

    @Test
    void testSortByLength() {
        // given
        // when
        List<CustomArray> sorted = repository.sort(ArrayComparator.byLength());
        // then
        assertEquals(1, sorted.get(0).length());
        assertEquals(3, sorted.get(1).length());
        assertEquals(4, sorted.get(2).length());
    }

    @Test
    void testSortByFirstElement() {
        // given
        // when
        List<CustomArray> sorted = repository.sort(ArrayComparator.byFirstElement());
        // then
        assertEquals(3, sorted.get(0).getElements()[0]);
        assertEquals(5, sorted.get(1).getElements()[0]);
        assertEquals(10, sorted.get(2).getElements()[0]);
    }

    @Test
    void testSortBySum() {
        // given
        // when
        List<CustomArray> sorted = repository.sort(ArrayComparator.bySum());
        // then
        assertEquals(ARRAY_C, sorted.get(0));
        assertEquals(ARRAY_A, sorted.get(1));
        assertEquals(ARRAY_B, sorted.get(2));
    }

    // --- observer integration ---

    @Test
    void testSetElementTriggersWarehouseUpdate() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 1, 1});
        repository.add(array);
        // when
        array.setElement(0, 100);
        // then
        long newSum = com.example.array.warehouse.ArrayWarehouse.getInstance()
                .getStatistics(array.getId())
                .map(java.util.IntSummaryStatistics::getSum)
                .orElse(0L);
        assertEquals(102L, newSum);
    }
}
