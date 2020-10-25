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
    public ComboBox cboCircuito;

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
        ObservableList ol;
        //Generamos lista de secciones del dsitrito elegido
        Region distrito = (Region) cboDistrito.getValue();
        ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSeccion.setItems(ol);
        cboCircuito.getItems().removeAll();
    }

    public void filtrarCircuitos(ActionEvent actionEvent) {
        ObservableList ol;
        //Genereamos lista de circuitos de la seccion
        if (cboSeccion.getItems() != null){
        Region seccion = (Region) cboSeccion.getValue();
        ol = FXCollections.observableArrayList(seccion.getSubregiones());
        cboCircuito.setItems(ol);
        }
        else
            cboCircuito.getItems().removeAll();
    }
}