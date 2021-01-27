package sample.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Forms.FrmEmpleados;
import sample.components.ButtonCustomeEmpleados;
import sample.models.EmpleadosDAO;

public class EmpleadosCRUD extends Stage {

    String text = "Hola";

    Scene escena;
    Button btnAgregar;
    VBox vBox;
    TableView<EmpleadosDAO> tbvEmpleados;
    EmpleadosDAO objEDAO;

    public EmpleadosCRUD(){
        objEDAO = new EmpleadosDAO();

        CrearUI();

        this.setTitle("Empleados");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //Inicializa la tabla.
        tbvEmpleados = new TableView<>();
        CrearTabla();

        //Botón
        btnAgregar = new Button("Agregar Empleado");
        btnAgregar.setOnAction(event -> new FrmEmpleados(tbvEmpleados, null));

        //VBox
        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,90,0,400));

        vBox.getChildren().addAll(tbvEmpleados, btnAgregar);

        escena = new Scene(vBox);
        escena.getStylesheets().add("sample/css/empleado.css");
    }

    private void CrearTabla() {
        TableColumn<EmpleadosDAO, Integer> tbcCveEmpleado = new TableColumn<>("Clave");
        tbcCveEmpleado.setCellValueFactory(new PropertyValueFactory<>("cveEmpleado"));

        TableColumn<EmpleadosDAO, String> tbcNomEmpleado = new TableColumn<>("Nombre");
        tbcNomEmpleado.setCellValueFactory(new PropertyValueFactory<>("nomEmpleado"));

        TableColumn<EmpleadosDAO, String> tbcRFC = new TableColumn<>("RFC");
        tbcRFC.setCellValueFactory(new PropertyValueFactory<>("rfc"));

        TableColumn<EmpleadosDAO, String> tbcDomicilio = new TableColumn<>("Domicilio");
        tbcDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));

        TableColumn<EmpleadosDAO, String> tbcTelefono = new TableColumn<>("Teléfono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<EmpleadosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> param) {
                        return new ButtonCustomeEmpleados(1);
                    }
                }
        );

        TableColumn<EmpleadosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> param) {
                        return new ButtonCustomeEmpleados(2);
                    }
                }
        );

        tbvEmpleados.getColumns().addAll(tbcCveEmpleado,tbcNomEmpleado,tbcRFC,tbcDomicilio,tbcTelefono,tbcEditar,tbcBorrar);
        tbvEmpleados.setItems(objEDAO.getAllEmpleados());
        tbvEmpleados.setPrefHeight(465);
    }

}
