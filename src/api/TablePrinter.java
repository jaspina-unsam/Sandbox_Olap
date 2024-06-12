package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import core.Cell;
import core.Cube;
import core.Level;
import metrics.Measure;

public class TablePrinter {

    public static void display(Cube cube) {
        System.out.println(cube);
    }

    public static void display(Cube cube, String dimension) {
        Level lvl = cube.getDimension(dimension).getActiveLevel();
        Set<Object> levelElements = new TreeSet<>(lvl.getElements());
        String selectedFact = cube.getSelectedFact();
        Measure measure = cube.getSelectedMeasure();
        String[][] tableData = new String[levelElements.size()+1][2];
        tableData[0][0] = String.format("%s (%s)", lvl.getName(), dimension);
        tableData[0][1] = String.format("%s (%s)", selectedFact, measure.getName());

        for (int i = 0; i < levelElements.size(); i++) {
            String element = levelElements.toArray()[i].toString();
            Cell cell = cube.getCell(dimension, element.toString());
            String value = String.format("%.2f", measure.calc(cell.getFacts(selectedFact)));
            tableData[i+1][0] = element;
            tableData[i+1][1] = value;
        }
        print(tableData);
    }

    private static void print(String[][] tableData) {
        if (tableData == null || tableData.length == 0) {
            System.out.println("No data to display.");
            return;
        }

        int[] columnWidths = getColumnWidths(tableData);

        printSeparator(columnWidths);
        for (String[] row : tableData) {
            printRow(row, columnWidths);
            printSeparator(columnWidths);
        }
    }

    private static int[] getColumnWidths(String[][] tableData) {
        int columns = tableData[0].length;
        int[] columnWidths = new int[columns];

        for (String[] row : tableData) {
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }
        return columnWidths;
    }

    private static void printSeparator(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            for (int i = 0; i < width + 2; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    private static void printRow(String[] row, int[] columnWidths) {
        for (int i = 0; i < row.length; i++) {
            System.out.print("| ");
            System.out.print(row[i]);
            for (int j = row[i].length(); j < columnWidths[i]; j++) {
                System.out.print(" ");
            }
            System.out.print(" ");
        }
        System.out.println("|");
    }
}
