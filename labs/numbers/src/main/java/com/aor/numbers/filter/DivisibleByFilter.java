package com.aor.numbers.filter;

public class DivisibleByFilter implements IListFilter {
    private int divisor;

    public DivisibleByFilter (int divisor) {
        this.divisor = divisor;
    }

    @Override
    public boolean accept(Integer number) {
        return number % divisor == 0;
    }
}
