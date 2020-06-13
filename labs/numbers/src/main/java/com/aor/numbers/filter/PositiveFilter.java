package com.aor.numbers.filter;

public class PositiveFilter implements IListFilter {
    @Override
    public boolean accept(Integer number) {
        return number > 0;
    }
}
