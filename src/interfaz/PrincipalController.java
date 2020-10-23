package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import negocio.Agrupaciones;
import negocio.Region;
import negocio.Regiones;

import java.io.File;

public class PrincipalController {
    public Label lblDireccion;
    public ListView lvwResultados;
    public ComboBox cboDistrito;
    public ComboBox cboSeccion;

    public void cambiarUbicacion(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione ubicación de los datos");

        if (!lblDireccion.getText().equals("Seleccione origen de datos"))
            dc.setInitialDirectory(new File(lblDireccion.getText()));
        File file =  dc.showDialog(null);
        if (file != null)
            lblDireccion.setText(file.getPath());
    }

    public void cargarDatos(ActionEvent actionEvent) {
        Agrupaciones agrupaciones = new Agrupaciones(lblDireccion.getText());

        ObservableList ol;
        ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        lvwResultados.setItems(ol);

        Regiones regiones = new Regiones(lblDireccion.getText());
        ol = FXCollections.observableArrayList(regiones.getDistritos());
        cboDistrito.setItems(ol);

    }

    public void filtrarSecciones(ActionEvent actionEvent) {
        Region distrito = (Region) cboDistrito.getValue();
        ObservableList ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSeccion.setItems(ol);
    }
}