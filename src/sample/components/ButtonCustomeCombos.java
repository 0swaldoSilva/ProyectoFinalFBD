package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.Forms.FrmCombos;
import sample.models.CombosDAO;

import java.util.Optional;

public class ButtonCustomeCombos extends TableCell<CombosDAO, String> {

    private Button btnCelda;
    private CombosDAO objCDAO;

    public ButtonCustomeCombos(int opc){
        switch (opc) {
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                    objCDAO = sample.components.ButtonCustomeCombos.this.getTableView().getItems().get(sample.components.ButtonCustomeCombos.this.getIndex());
                    new FrmCombos(sample.components.ButtonCustomeCombos.this.getTableView(), objCDAO);
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
                        objCDAO = sample.components.ButtonCustomeCombos.this.getTableView().getItems().get(sample.components.ButtonCustomeCombos.this.getIndex());
                        objCDAO.delCombo();

                        //Actualizamos el TableView
                        sample.components.ButtonCustomeCombos.this.getTableView().setItems(objCDAO.getAllCombo());
                        sample.components.ButtonCustomeCombos.this.getTableView().refresh();
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
