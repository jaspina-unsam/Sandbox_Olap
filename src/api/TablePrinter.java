package api;

import core.Cube;

public class TablePrinter {

    public static void display(Cube cube) {

    }

    public static void display(String[][] tableData) {
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
