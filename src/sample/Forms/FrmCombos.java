package sample.Forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.CombosDAO;

public class FrmCombos extends Stage {

    Label lblNombre, lblPrecio;
    TextField txtNombre, txtPrecio;
    Button btnGuardar;
    VBox vBox;
    Scene escena;
    CombosDAO objCDAO;
    TableView<CombosDAO> tbvCombos;
    int opc;

    public FrmCombos(TableView<CombosDAO> tbvCombos, CombosDAO objCDAO){
        if(objCDAO!=null) {
            opc = 1;
            this.objCDAO = objCDAO;
        }
        else {
            opc = 2;
            this.objCDAO = new CombosDAO();
        }

        CrearUI();
        this.setTitle("Gestión de Bebidas");
        this.setScene(escena);
        this.show();

        this.tbvCombos = tbvCombos;
    }

    private void CrearUI() {
        lblNombre = new Label("Nombre:");
        lblPrecio = new Label("Precio:");

        txtNombre = new TextField();
        txtPrecio = new TextField();


        txtNombre.setText(objCDAO.getNomCombo());
        txtPrecio.setText(objCDAO.getPrecio()+"");

        btnGuardar = new Button("Guardar Combo");
        btnGuardar.setOnAction(event -> Guardar());

        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(8,8,8,8));

        vBox.getChildren().addAll(lblNombre, txtNombre, lblPrecio, txtPrecio, btnGuardar);

        escena = new Scene(vBox, 350,350);
    }

    private void Guardar(){
        objCDAO.setNomCombo(txtNombre.getText());
        objCDAO.setPrecio(Float.parseFloat(txtPrecio.getText()));

        if(opc == 1)
            objCDAO.updCombo();
        else
            objCDAO.insCombo();

        tbvCombos.setItems(objCDAO.getAllCombo());
        tbvCombos.refresh();
        this.close();
    }
}
