package com.aor.numbers.aggregator;
import com.aor.numbers.deduplicator.IListDeduplicator;
import com.aor.numbers.sorter.IListSorter;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class ListAggregatorTest {
    private ArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }

    @Test
    public void sum() {
        final ListAggregator aggregator = new ListAggregator(list);

        final int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {
        final ListAggregator aggregator = new ListAggregator(list);

        final int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void maxAllNegative() {
        final List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-4);
        list.add(-5);

        final ListAggregator aggregator = new ListAggregator(list);

        final int max = aggregator.max();

        assertEquals(-1, max);
    }


    @Test
    public void min() {
        final ListAggregator aggregator = new ListAggregator(list);

        final int min = aggregator.min();

        assertEquals(1, min);
    }

    @Test
    public void distinct() {
        final ListAggregator aggregator = new ListAggregator(list);

        class DeduplicateStub implements IListDeduplicator {
            @Override
            public List<Integer> deduplicate(final IListSorter sorter) {
                final List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                list.add(5);
                return list;
            }
        }

        class SorterStub implements IListSorter {
            @Override
            public List<Integer> sort() {
                final List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(2);
                list.add(4);
                list.add(5);
                return list;
            }
        }

        final int distinct = aggregator.distinct(new DeduplicateStub(), new SorterStub());

        assertEquals(4, distinct);
    }


    @Test
    public void distinctBug() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        final ListAggregator aggregator = new ListAggregator(list);

        class DeduplicateStub implements IListDeduplicator {
            @Override
            public List<Integer> deduplicate(final IListSorter sorter) {
                final List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                return list;
            }
        }

        class SorterStub implements IListSorter {
            @Override
            public List<Integer> sort() {
                final List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(2);
                list.add(4);
                return list;
            }
        }

        final int distinct = aggregator.distinct(new DeduplicateStub(), new SorterStub());

        assertEquals(3, distinct);
    }
}