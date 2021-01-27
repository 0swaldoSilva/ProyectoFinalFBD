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
import sample.Forms.FrmBebidas;
import sample.components.ButtonCustomeBebidas;
import sample.models.BebidasDAO;

public class BebidasCRUD extends Stage {

    Scene escena;
    Button btnAgregar;
    VBox vBox;
    TableView<BebidasDAO> tbvBebidas;
    BebidasDAO objBDAO;

    public BebidasCRUD(){
        objBDAO = new BebidasDAO();

        CrearUI();

        this.setTitle("Bebidas");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //Inicializa la tabla.
        tbvBebidas = new TableView<>();
        CrearTabla();

        //Botón
        btnAgregar = new Button("Agregar Bebida");
        btnAgregar.setOnAction(event -> new FrmBebidas(tbvBebidas, null));

        //VBox
        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,400,0,400));

        vBox.getChildren().addAll(tbvBebidas, btnAgregar);

        escena = new Scene(vBox);
        escena.getStylesheets().add("sample/css/bebida.css");
    }

    private void CrearTabla() {
        TableColumn<BebidasDAO, Integer> tbcCveBebidas = new TableColumn<>("Clave");
        tbcCveBebidas.setCellValueFactory(new PropertyValueFactory<>("cveBebidas"));

        TableColumn<BebidasDAO, String> tbcNomBebidas = new TableColumn<>("Bebida");
        tbcNomBebidas.setCellValueFactory(new PropertyValueFactory<>("nomBebidas"));

        TableColumn<BebidasDAO, Float> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<BebidasDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<BebidasDAO, String>, TableCell<BebidasDAO, String>>() {
                    @Override
                    public TableCell<BebidasDAO, String> call(TableColumn<BebidasDAO, String> param) {
                        return new ButtonCustomeBebidas(1);
                    }
                }
        );

        TableColumn<BebidasDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<BebidasDAO, String>, TableCell<BebidasDAO, String>>() {
                    @Override
                    public TableCell<BebidasDAO, String> call(TableColumn<BebidasDAO, String> param) {
                        return new ButtonCustomeBebidas(2);
                    }
                }
        );

        tbvBebidas.getColumns().addAll(tbcCveBebidas,tbcNomBebidas,tbcPrecio,tbcEditar,tbcBorrar);
        tbvBebidas.setItems(objBDAO.getAllBebida());
        tbvBebidas.setPrefHeight(465);
    }
}
