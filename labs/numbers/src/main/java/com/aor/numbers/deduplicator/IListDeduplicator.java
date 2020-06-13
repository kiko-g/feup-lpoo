package com.aor.numbers.deduplicator;

import com.aor.numbers.sorter.IListSorter;

import java.util.List;

public interface IListDeduplicator {
    public List<Integer> deduplicate(IListSorter listSorter);
}