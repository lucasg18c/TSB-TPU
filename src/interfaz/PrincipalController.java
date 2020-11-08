package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    public Resultados res;
    public AnchorPane apnVentana;

    public void cambiarUbicacion(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione ubicación de los datos");

        if (!lblDireccion.getText().equals("Seleccione origen de datos")) {
            dc.setInitialDirectory(new File(lblDireccion.getText()));
        }
        File file =  dc.showDialog(null);
        if (file != null){
            apnVentana.setCursor(Cursor.WAIT);

            try
            {
                lblDireccion.setText(file.getPath());
                cargarDatos(null);
            }
            catch (Exception exception){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ubicación inválida");
                alert.setHeaderText(null);
                alert.setContentText("No se han encontrado los archivos necesarios en esta ubicación");
                alert.showAndWait();
            }
            finally {
                apnVentana.setCursor(Cursor.DEFAULT);
            }
        }
    }

    public void cargarDatos(ActionEvent actionEvent) {

        Agrupaciones.leerAgrupaciones(lblDireccion.getText());
        //ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        //lvwResultados.setItems(ol);

        Regiones regiones = new Regiones(lblDireccion.getText());
        ObservableList ol = FXCollections.observableArrayList(regiones.getDistritos());
        cboDistrito.setItems(ol);

        res = new Resultados(lblDireccion.getText());

        ol = FXCollections.observableArrayList(res.getResultadosRegion("00"));
        lvwResultados.setItems(ol);
    }

    public void elegirDistrito(ActionEvent actionEvent) {
        //Generamos lista de secciones del dsitrito elegido
        Region distrito = (Region) cboDistrito.getValue();
        ObservableList ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSeccion.setItems(ol);
        cboCircuito.setItems(null);
        //mostramos resultados del dsitrito
        ol = FXCollections.observableArrayList(res.getResultadosRegion(distrito.getCodigo()));
        lvwResultados.setItems(ol);
    }

    public void elegirSeccion(ActionEvent actionEvent) {
        ObservableList ol;
        //Genereamos lista de circuitos de la seccion
        if (cboSeccion.getValue() != null){
            Region seccion = (Region) cboSeccion.getValue();
            ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cboCircuito.setItems(ol);
            //Resultados de la seccion
            ol = FXCollections.observableArrayList(res.getResultadosRegion(seccion.getCodigo()));
            lvwResultados.setItems(ol);
        }
        else
            cboCircuito.setItems(null);
    }

    public void elegirCircuito(ActionEvent actionEvent) {
        ObservableList ol;
        //Genereamos lista de circuitos de la seccion
        if (cboCircuito.getValue() != null){
            Region circuito = (Region) cboCircuito.getValue();

            //R4esultados del Circuito
            ol = FXCollections.observableArrayList(res.getResultadosRegion(circuito.getCodigo()));
            lvwResultados.setItems(ol);
        }
        else
            cboCircuito.setItems(null);
    }
}