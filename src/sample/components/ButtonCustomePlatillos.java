package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.Forms.FrmPlatillos;
import sample.models.PlatillosDAO;

import java.util.Optional;

public class ButtonCustomePlatillos extends TableCell<PlatillosDAO, String> {

    private Button btnCelda;
    private PlatillosDAO objPDAO;

    public ButtonCustomePlatillos(int opc){
        switch (opc) {
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                    objPDAO = sample.components.ButtonCustomePlatillos.this.getTableView().getItems().get(sample.components.ButtonCustomePlatillos.this.getIndex());
                    new FrmPlatillos(sample.components.ButtonCustomePlatillos.this.getTableView(), objPDAO);
                });
                break;
            case 2:
                btnCelda = new Button("Borrar");
                btnCelda.setOnAction(event -> {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Mensaje del sistema :)");
                    alerta.setHeaderText("Confirmando acción");
                    alerta.setContentText("¿Realmente deseas borrar el registro?");
                    Optional<ButtonType> result = alerta.showAndWait();
                    if(result.get() == ButtonType.OK){
                        objPDAO = sample.components.ButtonCustomePlatillos.this.getTableView().getItems().get(sample.components.ButtonCustomePlatillos.this.getIndex());
                        objPDAO.delPlatillo();

                        //Actualizamos el TableView
                        sample.components.ButtonCustomePlatillos.this.getTableView().setItems(objPDAO.getAllPlatillo());
                        sample.components.ButtonCustomePlatillos.this.getTableView().refresh();
                    }
                });
                break;
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
            setGraphic(btnCelda);
    }
}
