package sample.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.Forms.FrmPlatillos;
import sample.components.ButtonCustomePlatillos;
import sample.models.PlatillosDAO;

public class PlatillosCRUD extends Stage{

    VBox vBox;
    Button btnAgregar;
    Scene escena;
    TableView<PlatillosDAO> tbvPlatillos;
    PlatillosDAO objPDAO;

    public PlatillosCRUD(){
        objPDAO = new PlatillosDAO();

        CrearUI();

        this.setTitle("Platillos");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //Inicializa la tabla.
        tbvPlatillos = new TableView<>();
        CrearTabla();

        //Botón
        btnAgregar = new Button("Agregar Platillo");
        btnAgregar.setOnAction(event -> { new FrmPlatillos(tbvPlatillos, null); });

        //VBox
        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,400,0,400));

        vBox.getChildren().addAll(tbvPlatillos, btnAgregar);

        escena = new Scene(vBox);
        escena.getStylesheets().add("sample/css/platillo.css");
    }

    private void CrearTabla() {
        TableColumn<PlatillosDAO, Integer> tbcIdPlatillo = new TableColumn<>("Clave");
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("cvePlatillo"));

        TableColumn<PlatillosDAO, String> tbcNomPlatillo = new TableColumn<>("Platillo");
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nomPlatillo"));

        TableColumn<PlatillosDAO, Float> tbcPrecioPlatillo = new TableColumn<>("Precio");
        tbcPrecioPlatillo.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<PlatillosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
                    @Override
                    public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                        return new ButtonCustomePlatillos(1);
                    }
                }
        );

        TableColumn<PlatillosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
                    @Override
                    public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                        return new ButtonCustomePlatillos(2);
                    }
                }
        );

        tbvPlatillos.getColumns().addAll(tbcIdPlatillo, tbcNomPlatillo, tbcPrecioPlatillo, tbcEditar, tbcBorrar);
        tbvPlatillos.setItems(objPDAO.getAllPlatillo());
        tbvPlatillos.setPrefHeight(465);
    }

}