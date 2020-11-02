package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import negocio.Agrupaciones;
import negocio.Region;
import negocio.Regiones;
import negocio.Resultados;

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
        if (file != null){
            lblDireccion.setText(file.getPath());
            cargarDatos(null);
        }
    }

    public void cargarDatos(ActionEvent actionEvent) {

        Agrupaciones.leerAgrupaciones(lblDireccion.getText());
        //ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        //lvwResultados.setItems(ol);

        Regiones regiones = new Regiones(lblDireccion.getText());
        ObservableList ol = FXCollections.observableArrayList(regiones.getDistritos());
        cboDistrito.setItems(ol);

        Resultados res = new Resultados(lblDireccion.getText());

    }

    public void filtrarSecciones(ActionEvent actionEvent) {
        //Generamos lista de secciones del dsitrito elegido
        Region distrito = (Region) cboDistrito.getValue();
        ObservableList ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSeccion.setItems(ol);
        cboCircuito.setItems(null);
    }

    public void filtrarCircuitos(ActionEvent actionEvent) {
        ObservableList ol;
        //Genereamos lista de circuitos de la seccion
        if (cboSeccion.getItems() != null && cboSeccion.getValue() != null){
            Region seccion = (Region) cboSeccion.getValue();
            ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cboCircuito.setItems(ol);
        }
        else
            cboCircuito.setItems(null);
    }
}