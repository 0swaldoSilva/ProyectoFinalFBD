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
import sample.Forms.FrmCombos;
import sample.components.ButtonCustomeCombos;
import sample.models.CombosDAO;

public class CombosCRUD extends Stage {

    VBox vBox;
    Button btnAgregar;
    Scene escena;
    TableView<CombosDAO> tbvCombos;
    CombosDAO objCDAO;

    public CombosCRUD(){
        objCDAO = new CombosDAO();

        CrearUI();

        this.setTitle("Combos");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //Inicializa la tabla.
        tbvCombos = new TableView<>();
        CrearTabla();

        //Botón
        btnAgregar = new Button("Agregar Combo");
        btnAgregar.setOnAction(event -> {new FrmCombos(tbvCombos, null);});

        //VBox
        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,400,0,400));

        vBox.getChildren().addAll(tbvCombos, btnAgregar);

        escena = new Scene(vBox);
        escena.getStylesheets().add("sample/css/combo.css");
    }

    private void CrearTabla() {
        TableColumn<CombosDAO, Integer> tbcIdCombo = new TableColumn<>("Clave");
        tbcIdCombo.setCellValueFactory(new PropertyValueFactory<>("cveCombo"));

        TableColumn<CombosDAO, String> tbcNomCombo = new TableColumn<>("Platillo");
        tbcNomCombo.setCellValueFactory(new PropertyValueFactory<>("nomCombo"));

        TableColumn<CombosDAO, Float> tbcPrecioCombo = new TableColumn<>("Precio");
        tbcPrecioCombo.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<CombosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CombosDAO, String>, TableCell<CombosDAO, String>>() {
                    @Override
                    public TableCell<CombosDAO, String> call(TableColumn<CombosDAO, String> param) {
                        return new ButtonCustomeCombos(1);
                    }
                }
        );

        TableColumn<CombosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<CombosDAO, String>, TableCell<CombosDAO, String>>() {
                    @Override
                    public TableCell<CombosDAO, String> call(TableColumn<CombosDAO, String> param) {
                        return new ButtonCustomeCombos(2);
                    }
                }
        );

        tbvCombos.getColumns().addAll(tbcIdCombo, tbcNomCombo, tbcPrecioCombo, tbcEditar, tbcBorrar);
        tbvCombos.setItems(objCDAO.getAllCombo());
        tbvCombos.setPrefHeight(465);
    }

}
