package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import metrics.Measure;

public class Cube {
    private Map<String, Dimension> dimensions;
    private Map<String, Measure> measures;
    private List<String> facts;
    private List<Cell> cells;

    public Cube() {
        this.dimensions = new HashMap<>();
        this.measures = new HashMap<>();
        this.facts = new ArrayList<>();
        this.cells = new ArrayList<>();
    }

    private Cube(Map<String, Dimension> dimensions, Map<String, Measure> measures, List<String> facts, List<Cell> cells) {
        this.dimensions = dimensions;
        this.measures = measures;
        this.facts = facts;
        this.cells = cells;
    }

    public void addDimension(Dimension dimension) {
        dimensions.put(dimension.getName(), dimension);
    }

    public Set<String> getDimensionNames() {
        return dimensions.keySet();
    }

    public Dimension getDimension(String name) {
        return dimensions.get(name);
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public void addFact(String fact) {
        facts.add(fact);
    }

    public void addMeasure(String name, Measure metric) {
        measures.put(name, metric);
    }

    public Cube filter(String dimension, String level, String value) {
        String key = dimensions.get(dimension).getIdKey();
        List<Cell> newCells = new ArrayList<>();
        for (Cell cell : cells) {
            List<Integer> idsToSearch = dimensions.get(dimension).getIdList(level, value);
            for (Integer id : idsToSearch) {
                if (cell.isResult(key, id)) {
                    newCells.add(cell);
                    // System.out.println(obs);
                }
            }
        }
        return new Cube(this.dimensions, this.measures, this.facts, newCells);
    }

    @Override
    public String toString() {
        return "Cube[Cells=" + cells.size() + ']';
    }
}
