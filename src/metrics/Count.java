package metrics;

import java.util.List;

public class Count extends Measure {

    public Count() {
        super("contar");
    }

    @Override
    public double calc(List<Double> values) {
        return values.size();
    }

}