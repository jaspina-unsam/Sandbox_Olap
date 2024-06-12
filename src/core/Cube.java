package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import metrics.Measure;

public class Cube {
    private Map<String, Dimension> dimensions;
    private List<Measure> measures;
    private List<String> facts;
    private List<Cell> cells;
    private int selectedFact;
    private int selectedMeasure;

    public Cube() {
        this.dimensions = new HashMap<>();
        this.measures = new ArrayList<>();
        this.facts = new ArrayList<>();
        this.cells = new ArrayList<>();
        this.selectedFact = 0;
        this.selectedMeasure = 0;
    }

    private Cube(Map<String, Dimension> dimensions, List<Measure> metrics, List<String> facts, List<Cell> cells) {
        this.dimensions = dimensions;
        this.measures = metrics;
        this.facts = facts;
        this.cells = cells;
        this.selectedFact = 0;
        this.selectedMeasure = 0;
    }

    public void addDimension(Dimension dimension) {
        dimensions.put(dimension.getName(), dimension);
    }

    public Set<String> getDimensionNames() {
        return dimensions.keySet();
    }

    public Dimension getDimension(String name) {
        if (!dimensions.containsKey(name)) {
            throw new IllegalArgumentException("Dimension not found");
        }
        return dimensions.get(name);
    }

    public boolean drillDown(String dimension) {
        Dimension dim = dimensions.get(dimension);
        if (dim.getCurrentLevel() < dim.getLevels().size() - 1) {
            dim.setCurrentLevel(dim.getCurrentLevel() + 1);
            return true;
        }
        return false;
    }

    public boolean rollUp(String dimension) {
        Dimension dim = dimensions.get(dimension);
        if (dim.getCurrentLevel() > 0) {
            dim.setCurrentLevel(dim.getCurrentLevel() - 1);
            return true;
        }
        return false;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void addFact(String fact) {
        facts.add(fact);
    }

    public void addMeasure(Measure metric) {
        measures.add(metric);
    }

    public void selectFact(String fact) {
        this.selectedFact = facts.indexOf(fact);
    }

    public void selectMeasure(String metric) {
        for (Measure measure : this.measures) {
            if (measure.getName().equals(metric)) {
                this.selectedMeasure = measures.indexOf(measure);
                break;
            }
        }
    }

    public Measure getSelectedMeasure() {
        return measures.get(selectedMeasure);
    }

    private List<Integer> getIdsToSearch(String dimension, String[] values) {
        List<Integer> idsToSearch = new ArrayList<>();
        for (String value : values) {
            idsToSearch.addAll(dimensions.get(dimension).getIdList(value));
        }
        return idsToSearch;
    }

    private List<Cell> searchCells(String dimension, String key, String[] values) {
        List<Cell> newCells = new ArrayList<>();
        List<Integer> idsToSearch = getIdsToSearch(dimension, values);
        for (int id : idsToSearch) {
            for (Cell cell : cells) {
                if (cell.isResult(key, id)) {
                    newCells.add(cell);
                }
            }
        }
        return newCells;
    }

    public Cube slice(String dimension, String value) {
        String key = dimensions.get(dimension).getIdKey();
        List<Cell> newCells = searchCells(dimension, key, new String[] { value });
        return new Cube(this.dimensions, this.measures, this.facts, newCells);
    }

    public Cube dice(String dimension, String[] values) {
        String key = dimensions.get(dimension).getIdKey();
        List<Cell> newCells = searchCells(dimension, key, values);
        return new Cube(this.dimensions, this.measures, this.facts, newCells);
    }

    // TODO Dice 3 versiones
    /**
     * String dim1 , String[] valoresDim1
     * * String dim1 , String[] valoresDim1, String dim2 , String[] valoresDim2
     * * String dim1 , String[] valoresDim1, String dim2 , String[] valoresDim2,
     * String dim3 , String[] valoresDim3
     */

    @Override
    public String toString() {
        return "Cube[Cells=" + cells.size() + ']';
    }

    public Cell getCell(String dimension, String value) {
        return Cell.cellFromGroup(searchCells(dimension, dimensions.get(dimension).getIdKey(), new String[] { value }));
    }

    public String getSelectedFact() {
        return facts.get(selectedFact);
    }

    /**
     * Slice y dice son extensiones de filter
     * roll-up y drill-down operan sobre el cubo o sobre dimensiones?
     */

}
