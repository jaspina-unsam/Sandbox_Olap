import api.CsvParser;
import config.CubeBuilder;
import core.Cube;
import data.model.Model;
import api.TablePrinter;

public class App {
    public static void main(String[] args) throws Exception {
        // Creación del cubo
        CsvParser parser = new CsvParser(",", true);
        CubeBuilder builder = new CubeBuilder();

        // agregar dimensiones al cubo
        builder.addDimension("fechas", "id_fecha", Model.FECHA_DATA_TYPES, parser, "src/data/files/fechas.csv");
        builder.addDimension("productos", "id_producto", Model.PRODUCTO_DATA_TYPES, parser, "src/data/files/productos.csv");
        builder.addDimension("puntos_venta", "id_punto_venta", Model.PUNTO_VENTA_DATA_TYPES, parser, "src/data/files/puntos_venta.csv");
        builder.addFacts("ventas", parser, "src/data/files/ventas.csv");

        // buildear el cubo
        Cube cube = builder.buildCube();

        // print + consultas
        TablePrinter.display(new String[][] {
                { "Name", "Age", "City" },
                { "Alice", "24", "New York" },
                { "Bob", "30", "Los Angeles" },
                { "Charlie", "28", "Chicago" }
        });

        TablePrinter.display(cube);
        TablePrinter.display(cube.filter("fechas", "fecha", "2017-07-10"));
    }
}
