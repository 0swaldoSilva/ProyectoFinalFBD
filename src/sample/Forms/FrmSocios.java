package sample.Forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.SociosDAO;

public class FrmSocios extends Stage {

    Label lblNombre, lblDomicilio, lblTelefono;
    TextField txtNombre, txtDomicilio, txtTelefono;
    //ComboBox<TipoPlatilloDAO> cbxTipo;
    Button btnGuardar;
    VBox vBox;
    Scene escena;
    SociosDAO objSDAO;
    TableView<SociosDAO> tbvSocios;
    int opc;

    public FrmSocios(TableView<SociosDAO> tbvSocios, SociosDAO objSDAO){
        if(objSDAO!=null) {
            opc = 1;
            this.objSDAO = objSDAO;
        }
        else {
            opc = 2;
            this.objSDAO = new SociosDAO();
        }

        CrearUI();
        this.setTitle("Gestión de Socios");
        this.setScene(escena);
        this.show();

        this.tbvSocios = tbvSocios;
    }

    private void CrearUI() {
        lblNombre    = new Label("Nombre:");
        lblTelefono  = new Label("Teléfono:");
        lblDomicilio = new Label("Domicilio:");

        txtNombre    = new TextField();
        txtTelefono  = new TextField();
        txtDomicilio = new TextField();


        txtNombre.setText(objSDAO.getNomSocios());
        txtTelefono.setText(objSDAO.getTelefono());
        txtDomicilio.setText(objSDAO.getDomicilio());

        //cbxTipo = new ComboBox<>();
        //cbxTipo.setItems(new PuestosDAO().getAllPuestos());

        btnGuardar = new Button("Guardar Socio");
        btnGuardar.setOnAction(event -> Guardar());

        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(8,8,8,8));

        vBox.getChildren().addAll(lblNombre, txtNombre, lblDomicilio, txtDomicilio,
                lblTelefono, txtTelefono, btnGuardar);

        escena = new Scene(vBox, 350,350);
    }

    private void Guardar(){
        objSDAO.setNomSocios(txtNombre.getText());
        objSDAO.setTelefono(txtTelefono.getText());
        objSDAO.setDomicilio(txtDomicilio.getText());

        if(opc == 1)
            objSDAO.updSocio();
        else
            objSDAO.insSocio();

        tbvSocios.setItems(objSDAO.getAllSocios());
        tbvSocios.refresh();
        this.close();
    }
}