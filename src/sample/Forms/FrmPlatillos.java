package sample.Forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;

public class FrmPlatillos extends Stage {

    Label lblNombre, lblTipoPlat, lblPrecio;
    TextField txtNombre, txtPrecio;
    ComboBox<TipoPlatilloDAO> cbxTipo;
    Button btnGuardar;
    VBox vBox;
    Scene escena;
    PlatillosDAO objPDAO;
    TableView<PlatillosDAO> tbvPlatillos;
    int opc;

    public FrmPlatillos(TableView<PlatillosDAO> tbvPlatillos, PlatillosDAO objPDAO){
        if(objPDAO!=null) {
            opc = 1;
            this.objPDAO = objPDAO;
        }
        else {
            opc = 2;
            this.objPDAO = new PlatillosDAO();
        }

        CrearUI();
        this.setTitle("Gestión de Platillo");
        this.setScene(escena);
        this.show();

        this.tbvPlatillos = tbvPlatillos;
    }

    private void CrearUI() {
        lblNombre   = new Label("Nombre:");
        lblTipoPlat = new Label("Tipo de Platillo:");
        lblPrecio   = new Label("Precio:");

        txtNombre = new TextField();
        txtPrecio = new TextField();


        txtNombre.setText(objPDAO.getNomPlatillo());
        txtPrecio.setText(objPDAO.getPrecio()+"");

        cbxTipo = new ComboBox<>();
        cbxTipo.setItems(new TipoPlatilloDAO().getAllTipoP());

        btnGuardar = new Button("Guardar Platillo");
        btnGuardar.setOnAction(event -> Guardar());

        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(8,8,8,8));

        vBox.getChildren().addAll(lblNombre, txtNombre, lblTipoPlat, cbxTipo,
                lblPrecio, txtPrecio, btnGuardar);

        escena = new Scene(vBox, 350,350);
    }

    private void Guardar(){
        objPDAO.setNomPlatillo(txtNombre.getText());
        objPDAO.setIdTipoPlat(cbxTipo.getSelectionModel().getSelectedIndex() + 1);
        objPDAO.setPrecio(Float.parseFloat(txtPrecio.getText()));

        if(opc == 1)
            objPDAO.updPlatillo();
        else
            objPDAO.insPlatillo();

        tbvPlatillos.setItems(objPDAO.getAllPlatillo());
        tbvPlatillos.refresh();
        this.close();
    }
}