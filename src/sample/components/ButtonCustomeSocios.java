package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.Forms.FrmSocios;
import sample.models.SociosDAO;

import java.util.Optional;

public class ButtonCustomeSocios extends TableCell<SociosDAO, String> {

    private Button btnCelda;
    private SociosDAO objSDAO;

    public ButtonCustomeSocios(int opc){

        switch (opc) {
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                    objSDAO = sample.components.ButtonCustomeSocios.this.getTableView().getItems().get(sample.components.ButtonCustomeSocios.this.getIndex());
                    new FrmSocios(sample.components.ButtonCustomeSocios.this.getTableView(), objSDAO);
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
                        objSDAO = sample.components.ButtonCustomeSocios.this.getTableView().getItems().get(sample.components.ButtonCustomeSocios.this.getIndex());
                        objSDAO.delSocio();

                        //Actualizamos el TableView
                        sample.components.ButtonCustomeSocios.this.getTableView().setItems(objSDAO.getAllSocios());
                        sample.components.ButtonCustomeSocios.this.getTableView().refresh();
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