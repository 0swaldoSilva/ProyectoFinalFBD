package sample.Forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.EmpleadosDAO;
import sample.models.PuestosDAO;

public class FrmEmpleados extends Stage {

    Label lblPuesto, lblNombre, lblRFC, lblDomicilio, lblTelefono;
    TextField txtNombre, txtRFC, txtDomicilio, txtTelefono;
    ComboBox<PuestosDAO> cbxPuesto;
    Button btnGuardar;
    VBox vBox;
    Scene escena;
    EmpleadosDAO objEDAO;
    TableView<EmpleadosDAO> tbvEmpleados;
    int opc;

    public FrmEmpleados(TableView<EmpleadosDAO> tbvEmpleados, EmpleadosDAO objEDAO){
        if(objEDAO!=null) {
            opc = 1;
            this.objEDAO = objEDAO;
        }
        else {
            opc = 2;
            this.objEDAO = new EmpleadosDAO();
        }

        CrearUI();
        this.setTitle("Gestión de Empleados");
        this.setScene(escena);
        this.show();

        this.tbvEmpleados = tbvEmpleados;
    }

    private void CrearUI() {
        lblPuesto    = new Label("Puesto");
        lblNombre    = new Label("Nombre:");
        lblRFC       = new Label("RFC:");
        lblDomicilio = new Label("Domicilio:");
        lblTelefono  = new Label("Teléfono:");

        txtNombre    = new TextField();
        txtRFC       = new TextField();
        txtDomicilio = new TextField();
        txtTelefono  = new TextField();

        txtNombre.setText(objEDAO.getNomEmpleado());
        txtRFC.setText(objEDAO.getRfc());
        txtDomicilio.setText(objEDAO.getDomicilio());
        txtTelefono.setText(objEDAO.getTelefono());

        cbxPuesto = new ComboBox<>();
        cbxPuesto.setItems(new PuestosDAO().getAllPuesto());

        btnGuardar = new Button("Guardar Empleado");
        btnGuardar.setOnAction(event -> Guardar());

        vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(8,8,8,8));

        vBox.getChildren().addAll(lblNombre, txtNombre, lblPuesto, cbxPuesto, lblRFC, txtRFC,
                lblDomicilio, txtDomicilio, lblTelefono, txtTelefono, btnGuardar);

        escena = new Scene(vBox, 350,350);
    }

    private void Guardar(){
        objEDAO.setNomEmpleado(txtNombre.getText());
        objEDAO.setRfc(txtRFC.getText());
        objEDAO.setDomicilio(txtDomicilio.getText());
        objEDAO.setTelefono(txtTelefono.getText());
        objEDAO.setCvePuesto(cbxPuesto.getSelectionModel().getSelectedIndex() + 1);

        if (opc == 1)
            objEDAO.updEmpleado();
        else
            objEDAO.insEmpleado();

        tbvEmpleados.setItems(objEDAO.getAllEmpleados());
        tbvEmpleados.refresh();
        this.close();
    }
}

