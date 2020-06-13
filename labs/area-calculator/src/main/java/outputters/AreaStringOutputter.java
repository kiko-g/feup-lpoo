package outputters;

import aggregators.SumProvider;

public class AreaStringOutputter {
    private SumProvider sumProvider;

    public AreaStringOutputter(SumProvider aggregator) {
        this.sumProvider = aggregator;
    }

    public String output() {
        return "Sum of areas: " + sumProvider.sum();
    }
}
