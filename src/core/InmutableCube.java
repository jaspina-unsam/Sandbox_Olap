package core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InmutableCube{
    private Map<String, Dimension> dimensionMap;
    private Facts facts;


    public InmutableCube() {
        dimensionMap = new HashMap<>();
    }

    public void addDimension(String name, Dimension dimension) {
        dimensionMap.put(name, dimension);
    }

    public void addFacts(Facts facts) {
        this.facts = facts;
    }

    public Set<String> getDimensionNames() {
        return dimensionMap.keySet();
    }

    public Set<String> getFeatureNames() {
        return facts.getFeatureNames();
    }

    public Dimension getDimension(String name) {
        return dimensionMap.get(name);
    }

    public Collection<Dimension> getDimensions() {
        return dimensionMap.values();
    }

    public Set<Object> getLevel(String dimensionName, String levelName) {
        return this.getDimension(dimensionName).getLevel(levelName);
    }

}