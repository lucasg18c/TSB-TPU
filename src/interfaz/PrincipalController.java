package interfaz;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import soporte.TextFile;

import java.io.File;

public class PrincipalController {

    public Button btnCambiar;
    public Label lblDireccion;

    public void cambiarUbicacion(ActionEvent evento){
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione la ubicación de los datos");
        if (!lblDireccion.getText().equals("seleccione la ubicación de los datos")){
            dc.setInitialDirectory(new File(lblDireccion.getText()));
        }

        File origenDatos = dc.showDialog(null);

        if (origenDatos != null)
            lblDireccion.setText(origenDatos.getPath());
    }

    public void cargarDatos(ActionEvent actionEvent) {
        TextFile fileAgrupaciones = new TextFile(lblDireccion.getText() + "\\descripcion_postulaciones.dsv");
        TextFile fileRegiones = new TextFile(lblDireccion.getText() + "\\descripcion_regiones.dsv");
        TextFile fileMesas = new TextFile(lblDireccion.getText() + "\\mesas_totales_agrp_politica.dsv");

        System.out.println(fileAgrupaciones.leerEncabezado());
        System.out.println(fileRegiones.leerEncabezado());
        System.out.println(fileMesas.leerEncabezado());
    }
}
