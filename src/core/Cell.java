package core;

import java.util.List;
import java.util.Map;

public class Cell {
    private Map<String, Integer> keys;
    private Map<String, Double> facts;

    public Cell(Map<String, Integer> keys, Map<String, Double> facts) {
        this.keys = keys;
        this.facts = facts;
    }

    public boolean isResult(String key, Integer value) {
        return keys.get(key).equals(value);
    }

    public List<Double> getValues() {
        return List.copyOf(facts.values());
    }

    @Override
    public String toString() {
        return "Cell{" +
                "keys=" + keys +
                ", facts=" + facts +
                '}';
    }
}