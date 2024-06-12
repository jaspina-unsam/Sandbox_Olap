package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
    private Map<String, Integer> keys;
    private Map<String, List<Double>> facts;

    public Cell(Map<String, Integer> keys, Map<String, List<Double>> facts) {
        this.keys = keys;
        this.facts = facts;
    }

    public Cell() {
        keys = new HashMap<>();
        facts = new HashMap<>();
    }

    public void addKey(String key, Integer value) {
        keys.put(key, value);
    }

    public void addFact(String key, Double value) {
        if (!facts.containsKey(key)) {
            List<Double> valuesList = new ArrayList<>();
            valuesList.add(value);
            facts.put(key, valuesList);
        } else {
            facts.get(key).add(value);
        }
    }

    public boolean isResult(String key, Integer value) {
        return keys.get(key).equals(value);
    }

    public static Cell cellFromGroup(List<Cell> cells) {
        Cell result = new Cell();
        for (Cell cell : cells) {
            for (String factName : cell.facts.keySet()) {
                for (Double value : cell.facts.get(factName)) {
                    result.addFact(factName, value);
                }
            }
        }
        return result;
    }

    public List<Double> getFacts(String key) {
        return facts.get(key);
    }

    @Override
    public String toString() {
        return "Cell[keys=" + keys + ", facts=" + facts + "]";
    }
}