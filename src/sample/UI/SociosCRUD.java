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
import sample.Forms.FrmSocios;
import sample.components.ButtonCustomeSocios;
import sample.models.SociosDAO;

public class SociosCRUD extends Stage {

    Scene escena;
    Button btnAgregar;
    VBox vBox;
    TableView<SociosDAO> tbvSocios;
    SociosDAO objSDAO;

    public SociosCRUD(){
        objSDAO = new SociosDAO();

        CrearUI();

        this.setTitle("Socios");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //Inicializa la tabla.
        tbvSocios = new TableView<>();
        CrearTabla();

        //Botón
        btnAgregar = new Button("Agregar Socio");
        btnAgregar.setOnAction(event -> new FrmSocios(tbvSocios, null));

        //VBox
        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,250,0,250));

        vBox.getChildren().addAll(tbvSocios, btnAgregar);

        escena = new Scene(vBox);
        escena.getStylesheets().add("sample/css/socio.css");
    }

    private void CrearTabla() {
        TableColumn<SociosDAO, Integer> tbcCveSocios = new TableColumn<>("Clave");
        tbcCveSocios.setCellValueFactory(new PropertyValueFactory<>("cveSocios"));

        TableColumn<SociosDAO, String> tbcNomSocios = new TableColumn<>("Nombre");
        tbcNomSocios.setCellValueFactory(new PropertyValueFactory<>("nomSocios"));

        TableColumn<SociosDAO, String> tbcTelefono = new TableColumn<>("Teléfono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<SociosDAO, String> tbcDomicilio = new TableColumn<>("Domicilio");
        tbcDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));

        TableColumn<SociosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<SociosDAO, String>, TableCell<SociosDAO, String>>() {
                    @Override
                    public TableCell<SociosDAO, String> call(TableColumn<SociosDAO, String> param) {
                        return new ButtonCustomeSocios(1);
                    }
                }
        );

        TableColumn<SociosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<SociosDAO, String>, TableCell<SociosDAO, String>>() {
                    @Override
                    public TableCell<SociosDAO, String> call(TableColumn<SociosDAO, String> param) {
                        return new ButtonCustomeSocios(2);
                    }
                }
        );

        tbvSocios.getColumns().addAll(tbcCveSocios,tbcNomSocios,tbcDomicilio,tbcTelefono,tbcEditar,tbcBorrar);
        tbvSocios.setItems(objSDAO.getAllSocios());
        tbvSocios.setPrefHeight(465);
    }

}
