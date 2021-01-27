package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.Forms.FrmBebidas;
import sample.models.BebidasDAO;

import java.util.Optional;

public class ButtonCustomeBebidas extends TableCell<BebidasDAO, String> {

    private Button btnCelda;
    private BebidasDAO objBDAO;

    public ButtonCustomeBebidas(int opc){
        switch (opc) {
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                    objBDAO = sample.components.ButtonCustomeBebidas.this.getTableView().getItems().get(sample.components.ButtonCustomeBebidas.this.getIndex());
                    new FrmBebidas(sample.components.ButtonCustomeBebidas.this.getTableView(), objBDAO);
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
                        objBDAO = sample.components.ButtonCustomeBebidas.this.getTableView().getItems().get(sample.components.ButtonCustomeBebidas.this.getIndex());
                        objBDAO.delBebida();

                        //Actualizamos el TableView
                        sample.components.ButtonCustomeBebidas.this.getTableView().setItems(objBDAO.getAllBebida());
                        sample.components.ButtonCustomeBebidas.this.getTableView().refresh();
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