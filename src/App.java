import java.util.List;

import api.CsvParser;
import core.Dimension;
import core.Facts;
import core.InmutableCube;
import data.model.Model;

public class App {
    public static void main(String[] args) throws Exception {
        CsvParser csvParser = new CsvParser();
        InmutableCube cube = new InmutableCube();

        List<List<String>> csvFechas = csvParser.read("src/data/files/fechas.csv");
        List<List<String>> csvProductos = csvParser.read("src/data/files/productos.csv");
        List<List<String>> csvPuntosVenta = csvParser.read("src/data/files/puntos_venta.csv");
        List<List<String>> csvVentas = csvParser.read("src/data/files/ventas.csv");

        cube.addDimension(
            "fechas",
            new Dimension(
                csvFechas,
                Model.FECHA_DATA_TYPES
            )
        );
        cube.addDimension(
            "productos",
            new Dimension(
                csvProductos,
                Model.PRODUCTO_DATA_TYPES
            )
        );
        cube.addDimension(
            "puntos_venta",
            new Dimension(
                csvPuntosVenta,
                Model.PUNTO_VENTA_DATA_TYPES
            )
        );
        cube.addFacts(
            new Facts(
                csvVentas,
                Model.VENTAS_DATA_TYPES,
                List.of(
                    "id_producto",
                    "id_fecha",
                    "id_punto_venta"
                )
            )
        );

        System.out.println(cube.getDimensionNames());
        System.out.println(cube.getDimension("fechas").getLevels());
        System.out.println(cube.getDimension("fechas").getLevel("quarter"));
        System.out.println(cube.getLevel("fechas", "quarter"));
        System.out.println(cube.getLevel("fechas", "anio"));

        System.out.println(cube.getDimension("fechas").getIdList("quarter","3"));
        System.out.println(cube.getDimension("fechas").getIdList("fecha","2017-07-10"));
        System.out.println(cube.getFeatureNames());
    }
}
