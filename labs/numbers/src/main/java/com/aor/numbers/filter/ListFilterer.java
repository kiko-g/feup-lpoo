package com.aor.numbers.filter;

import java.util.ArrayList;
import java.util.List;

public class ListFilterer {
    private List<Integer> list;

    public ListFilterer(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> filter(IListFilter filter) {
        List<Integer> filtered = new ArrayList<>();
        for (Integer number : list)
            if (filter.accept(number))
                filtered.add(number);
        return filtered;
    }
}
