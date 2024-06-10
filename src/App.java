import api.CsvParser;
import config.CubeBuilder;
import core.Cube;
import data.model.Model;

public class App {
    public static void main(String[] args) throws Exception {
        // Creación del cubo
        CsvParser parser = new CsvParser(",", true);
        CubeBuilder builder = new CubeBuilder();
        builder.addDimension("fechas", "id_fecha", Model.FECHA_DATA_TYPES, parser, "src/data/files/fechas.csv");
        builder.addDimension("productos", "id_producto", Model.PRODUCTO_DATA_TYPES, parser, "src/data/files/productos.csv");
        builder.addDimension("puntos_venta", "id_punto_venta", Model.PUNTO_VENTA_DATA_TYPES, parser, "src/data/files/puntos_venta.csv");
        builder.addFacts("ventas", parser, "src/data/files/ventas.csv");

        Cube cube = builder.buildCube();

        System.out.println(cube.getDimensionNames());

        System.out.println(cube.getDimension("fechas").getLevels());
        System.out.println(cube.getDimension("productos").getLevels());
        System.out.println(cube.getDimension("puntos_venta").getLevels());

        System.out.println(cube.getDimension("fechas").getLevelNameAt(0));

        System.out.println(cube.getDimension("fechas").getIdList("quarter","3"));
        System.out.println(cube.getDimension("fechas").getIdList("fecha","2017-07-10"));

        System.err.println(cube);
        Cube cube2 = cube.filter("fechas", "fecha", "2017-07-10");

        System.out.println(cube2);

        Cube cube3 = cube.filter("fechas", "quarter", "3");
        System.out.println(cube3);
    }
}
