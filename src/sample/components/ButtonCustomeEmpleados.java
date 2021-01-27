package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.Forms.FrmEmpleados;
import sample.models.EmpleadosDAO;

import java.util.Optional;

public class ButtonCustomeEmpleados extends TableCell<EmpleadosDAO, String> {

    private Button btnCelda;
    private EmpleadosDAO objEDAO;

    public ButtonCustomeEmpleados(int opc){

        switch (opc) {
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                    objEDAO = sample.components.ButtonCustomeEmpleados.this.getTableView().getItems().get(sample.components.ButtonCustomeEmpleados.this.getIndex());
                    new FrmEmpleados(sample.components.ButtonCustomeEmpleados.this.getTableView(), objEDAO);
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
                        objEDAO = sample.components.ButtonCustomeEmpleados.this.getTableView().getItems().get(sample.components.ButtonCustomeEmpleados.this.getIndex());
                        objEDAO.delEmpleado();

                        //Actualizamos el TableView
                        sample.components.ButtonCustomeEmpleados.this.getTableView().setItems(objEDAO.getAllEmpleados());
                        sample.components.ButtonCustomeEmpleados.this.getTableView().refresh();
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

