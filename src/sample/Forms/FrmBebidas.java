package sample.Forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.BebidasDAO;
import sample.models.TipoBebidaDAO;

public class FrmBebidas extends Stage {

    Label lblNombre, lblTipoBebida, lblPrecio;
    TextField txtNombre, txtPrecio;
    ComboBox<TipoBebidaDAO> cbxTipo;
    Button btnGuardar;
    VBox vBox;
    Scene escena;
    BebidasDAO objBDAO;
    TableView<BebidasDAO> tbvBebidas;
    int opc;

    public FrmBebidas(TableView<BebidasDAO> tbvBebidas, BebidasDAO objBDAO){
        if(objBDAO!=null) {
            opc = 1;
            this.objBDAO = objBDAO;
        }
        else {
            opc = 2;
            this.objBDAO = new BebidasDAO();
        }

        CrearUI();
        this.setTitle("Gestión de Bebidas");
        this.setScene(escena);
        this.show();

        this.tbvBebidas = tbvBebidas;
    }

    private void CrearUI() {
        lblNombre     = new Label("Nombre:");
        lblTipoBebida = new Label("Tipo de Bebida:");
        lblPrecio     = new Label("Precio:");

        txtNombre     = new TextField();
        txtPrecio     = new TextField();


        txtNombre.setText(objBDAO.getNomBebidas());
        txtPrecio.setText(objBDAO.getPrecio()+"");

        cbxTipo = new ComboBox<>();
        cbxTipo.setItems(new TipoBebidaDAO().getAllTipoB());

        btnGuardar = new Button("Guardar Bebida");
        btnGuardar.setOnAction(event -> Guardar());

        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(8,8,8,8));

        vBox.getChildren().addAll(lblNombre, txtNombre, lblTipoBebida, cbxTipo,
                lblPrecio, txtPrecio, btnGuardar);

        escena = new Scene(vBox, 350,350);
    }

    private void Guardar(){
        objBDAO.setNomBebidas(txtNombre.getText());
        objBDAO.setIdTipoBebida(cbxTipo.getSelectionModel().getSelectedIndex() + 1);
        objBDAO.setPrecio(Float.parseFloat(txtPrecio.getText()));

        if(opc == 1)
            objBDAO.updBebida();
        else
            objBDAO.insBebida();

        tbvBebidas.setItems(objBDAO.getAllBebida());
        tbvBebidas.refresh();
        this.close();
    }
}
