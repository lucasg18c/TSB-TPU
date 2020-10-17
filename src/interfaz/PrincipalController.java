package interfaz;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import negocio.Agrupacion;
import negocio.Agrupaciones;
import soporte.TextFile;

import java.io.File;

public class PrincipalController {
    public Label lblDireccion;
    public TextArea txtAgrupaciones;

    public void cambiarUbicacion(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione ubicación de los datos");
        dc.setInitialDirectory(new File(lblDireccion.getText()));
        File file =  dc.showDialog(null);
        if (file != null)
            lblDireccion.setText(file.getPath());
    }

    public void cargarDatos(ActionEvent actionEvent) {
        Agrupaciones agrupaciones = new Agrupaciones(lblDireccion.getText());
        txtAgrupaciones.setText(agrupaciones.toString());
        //Borrador

        TextFile fileRegiones = new TextFile(lblDireccion.getText() + "\\descripcion_regiones.dsv");
        System.out.println(fileRegiones.leerEncabezado());

        TextFile fileMesas = new TextFile(lblDireccion.getText() + "\\mesas_totales_agrp_politica.dsv");
        System.out.println(fileMesas.leerEncabezado());
    }
}