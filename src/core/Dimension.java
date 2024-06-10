package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Dimension {
    private String name;
    private String idKey;
    private Map<String, Level> levels;
    private List<String> hierarchy;

    public Dimension(String name, String idKey, Map<String, Level> levels, List<String> hierarchy) {
        this.name = name;
        this.idKey = idKey;
        this.levels = levels;
        this.hierarchy = hierarchy;
    }

    public void addLevel(Level level) {
        levels.put(level.getName(), level);
    }

    public void addLevels(Map<String, Level> levels) {
        this.levels = levels;
    }

    public String getLevelNameAt(int index) {
        return hierarchy.get(index);
    }

    public List<String> getLevels() {
        return this.hierarchy;
    }

    public List<Integer> getIdList(String levelName, String value) {
        List<Integer> ids = new ArrayList<>();
        List<Object> elements = levels.get(levelName).getElements();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).toString().equals(value)) {
                ids.add((Integer) levels.get(idKey).getElements().get(i));
            }
        }
        return ids;
    }

    public String getName() {
        return this.name;
    }

    public String getIdKey() {
        return this.idKey;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "name='" + name + '\'' +
                ", idKey='" + idKey + '\'' +
                ", levels=" + levels +
                ", hierarchy=" + hierarchy +
                '}';
    }
}
