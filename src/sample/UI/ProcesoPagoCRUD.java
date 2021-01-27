package sample.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProcesoPagoCRUD extends Stage {

    Scene escena, escena2, escena3;

    //Pantalla 1
    Label lblPlatillo, lblBebida, lblCombo, lblCantPlatillo, lblCantBebida, lblCantCombo, lblTipoPago, lblBanco;
    TextField txtCantPlatillo, txtCantBebida, txtCantCombo;
    Button btnConfirmar;
    HBox hBoxPlatillo, hBoxBebida, hBoxCombo;
    VBox vBoxPlatillo, vBoxCPlatillo, vBoxBebida, vBoxCBebida, vBoxCombo, vBoxCCombo, vBoxPrincipal, vBoxTipoPago;
    ComboBox<PlatillosDAO> cbxPlatillos;
    ComboBox<BebidasDAO>   cbxBebidas;
    ComboBox<CombosDAO>    cbxCombos;
    ComboBox<TipoPagoDAO>  cbxTipoPago;
    ComboBox<BancosDAO>    cbxBanco;
    PlatillosDAO objPDAO;
    BebidasDAO   objBDAO;
    CombosDAO    objCDAO;
    TipoPagoDAO  objTPDAO;
    BancosDAO    objBADAO;
    CuentasDAO   objCUDAO;

    public ProcesoPagoCRUD(){
        CrearUI();

        this.setTitle("Proceso de Pago");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        objPDAO  = new PlatillosDAO();
        objBDAO  = new BebidasDAO();
        objCDAO  = new CombosDAO();
        objTPDAO = new TipoPagoDAO();
        objBADAO = new BancosDAO();
        objCUDAO = new CuentasDAO();

        lblPlatillo     = new Label("Platillo");
        lblCantPlatillo = new Label("Cantidad");
        lblBebida       = new Label("Bebida");
        lblCantBebida   = new Label("Cantidad");
        lblCombo        = new Label("Combo");
        lblCantCombo    = new Label("Cantidad");
        lblTipoPago     = new Label("Tipo de Pago");
        lblBanco        = new Label("Banco");

        txtCantPlatillo = new TextField();
        txtCantBebida   = new TextField();
        txtCantCombo    = new TextField();

        txtCantPlatillo.setText("0");
        txtCantBebida.setText("0");
        txtCantCombo.setText("0");

        txtCantPlatillo.setPrefWidth(70);
        txtCantBebida.setPrefWidth(70);
        txtCantCombo.setPrefWidth(70);

        cbxPlatillos = new ComboBox<>();
        cbxBebidas   = new ComboBox<>();
        cbxCombos    = new ComboBox<>();
        cbxTipoPago  = new ComboBox<>();
        cbxBanco     = new ComboBox<>();

        cbxPlatillos.setItems(new PlatillosDAO().getAllPlatillo());
        cbxBebidas.setItems(new BebidasDAO().getAllBebida());
        cbxCombos.setItems(new CombosDAO().getAllCombo());
        cbxTipoPago.setItems(new TipoPagoDAO().getAllTipoPago());
        cbxBanco.setItems(new BancosDAO().getAllBanco());

        btnConfirmar = new Button("Confirmar");
        btnConfirmar.setOnAction(event -> CuentasCRUD());

        vBoxPlatillo  = new VBox();
        vBoxCPlatillo = new VBox();
        vBoxBebida    = new VBox();
        vBoxCBebida   = new VBox();
        vBoxCombo     = new VBox();
        vBoxCCombo    = new VBox();
        vBoxTipoPago  = new VBox();
        vBoxPrincipal = new VBox();

        hBoxPlatillo  = new HBox();
        hBoxBebida    = new HBox();
        hBoxCombo     = new HBox();

        hBoxPlatillo.setSpacing(20);
        hBoxBebida.setSpacing(20);
        hBoxCombo.setSpacing(20);

        vBoxPlatillo.setSpacing(5);
        vBoxCPlatillo.setSpacing(5);
        vBoxBebida.setSpacing(5);
        vBoxCBebida.setSpacing(5);
        vBoxCombo.setSpacing(5);
        vBoxCCombo.setSpacing(5);
        vBoxTipoPago.setSpacing(5);
        vBoxPrincipal.setSpacing(20);

        hBoxPlatillo.setAlignment(Pos.CENTER);
        hBoxBebida.setAlignment(Pos.CENTER);
        hBoxCombo.setAlignment(Pos.CENTER);
        vBoxTipoPago.setAlignment(Pos.CENTER);
        vBoxPrincipal.setAlignment(Pos.CENTER);

        vBoxPlatillo.setPrefWidth(140);
        vBoxBebida.setPrefWidth(140);
        vBoxCombo.setPrefWidth(140);

        vBoxPlatillo.getChildren().addAll(lblPlatillo, cbxPlatillos);
        vBoxCPlatillo.getChildren().addAll(lblCantPlatillo, txtCantPlatillo);
        vBoxBebida.getChildren().addAll(lblBebida, cbxBebidas);
        vBoxCBebida.getChildren().addAll(lblCantBebida, txtCantBebida);
        vBoxCombo.getChildren().addAll(lblCombo, cbxCombos);
        vBoxCCombo.getChildren().addAll(lblCantCombo, txtCantCombo);
        vBoxTipoPago.getChildren().addAll(lblTipoPago, cbxTipoPago, lblBanco, cbxBanco);

        hBoxPlatillo.getChildren().addAll(vBoxPlatillo, vBoxCPlatillo);
        hBoxBebida.getChildren().addAll(vBoxBebida, vBoxCBebida);
        hBoxCombo.getChildren().addAll(vBoxCombo, vBoxCCombo);

        vBoxPrincipal.getChildren().addAll(hBoxPlatillo, hBoxBebida, hBoxCombo,
                vBoxTipoPago, btnConfirmar);

        escena = new Scene(vBoxPrincipal);
        escena.getStylesheets().add("sample/css/cantidad.css");
    }

    //Pantalla 2
    Label lblTitulo, lblCveCuenta, lblFecha, lblTPago, lblPlat, lblBebi, lblComb, lblTotal,
            lblTotalPlat, lblTotalBebi, lblTotalComb, lblTota, lblComision, lblTotalComision;
    Button btnListo, btnFactura;
    DatePicker fecha;

    HBox hBoxProd, hBoxComision, hBoxBtn;
    VBox vBoxGrl, vBoxCuenta, vBoxProd, vBoxPrecio;

    int cantPlatillo, cantBebida, cantCombo, cveCuenta;
    String fecImpresion, platillo, bebida, combo;
    float totalPlatillo, totalBebida, totalCombo, total;

    void CuentasCRUD() {
        try {
            if(cbxTipoPago.getValue() != null) {
                if (cbxPlatillos.getValue() != null || cbxBebidas.getValue() != null || cbxCombos.getValue() != null) {
                    fecha = new DatePicker(LocalDate.now());

                    //última clave de Cuenta
                    objCUDAO.ultimaCve();
                    cveCuenta = objCUDAO.getCveCuenta() + 1;

                    //Cantidad de cada producto.
                    cantPlatillo = Integer.parseInt(txtCantPlatillo.getText());
                    cantBebida = Integer.parseInt(txtCantBebida.getText());
                    cantCombo = Integer.parseInt(txtCantCombo.getText());

                    if (cantPlatillo > 0 || cantBebida > 0 || cantCombo > 0) {
                        //Tomamos el nombre del platillo.
                        platillo = cbxPlatillos.getValue() + "";
                        bebida = cbxBebidas.getValue() + "";
                        combo = cbxCombos.getValue() + "";

                        //Obtenemos el precio de cada producto.
                        objPDAO.getPrecioPlatillo(cbxPlatillos.getSelectionModel().getSelectedIndex() + 1);
                        objBDAO.getPrecioBebida(cbxBebidas.getSelectionModel().getSelectedIndex() + 1);
                        objCDAO.getPrecioCombo(cbxCombos.getSelectionModel().getSelectedIndex() + 1);

                        //Sacamos el total $$$
                        totalPlatillo = cantPlatillo * objPDAO.getPrecio();
                        totalBebida = cantBebida * objBDAO.getPrecio();
                        totalCombo = cantCombo * objCDAO.getPrecio();
                        total = totalPlatillo + totalBebida + totalCombo;

                        comision();

                        fecImpresion = fecha.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                        if (cantPlatillo >= 0 && cantBebida >= 0 && cantCombo >= 0) {
                            lblTitulo = new Label("\"LA IGLESIA\"");
                            lblCveCuenta = new Label("Cuenta: " + cveCuenta);
                            lblFecha = new Label("Fecha: " + fecImpresion);
                            lblTPago = new Label("Tipo de pago: " + cbxTipoPago.getValue());
                            lblTotal = new Label("Total");
                            lblTota = new Label(total + "");
                            lblPlat = new Label(platillo);
                            lblBebi = new Label(bebida);
                            lblComb = new Label(combo);
                            lblTotalPlat = new Label(totalPlatillo + "");
                            lblTotalBebi = new Label(totalBebida + "");
                            lblTotalComb = new Label(totalCombo + "");
                            lblComision = new Label("Comision");
                            lblTotalComision = new Label(comisionTotal + "");

                            lblTitulo.setId("lblTitulo");

                            btnListo = new Button("Listo");
                            btnFactura = new Button("Generar Factura");

                            btnListo.setOnAction(event -> Insert(1));
                            btnFactura.setOnAction(event -> Insert(2));

                            hBoxProd = new HBox();
                            hBoxBtn = new HBox();
                            hBoxComision = new HBox();

                            vBoxCuenta = new VBox();
                            vBoxProd = new VBox();
                            vBoxPrecio = new VBox();
                            vBoxGrl = new VBox();

                            hBoxProd.setSpacing(30);
                            hBoxComision.setSpacing(30);
                            hBoxBtn.setSpacing(30);
                            vBoxCuenta.setSpacing(10);
                            vBoxProd.setSpacing(10);
                            vBoxPrecio.setSpacing(10);
                            vBoxGrl.setSpacing(20);

                            hBoxProd.setAlignment(Pos.CENTER);
                            hBoxComision.setAlignment(Pos.CENTER);
                            hBoxBtn.setAlignment(Pos.CENTER);
                            vBoxCuenta.setAlignment(Pos.CENTER);
                            vBoxPrecio.setAlignment(Pos.CENTER_RIGHT);
                            vBoxGrl.setAlignment(Pos.CENTER);

                            vBoxGrl.setId("vBox");

                            vBoxCuenta.getChildren().addAll(lblCveCuenta, lblFecha, lblTPago);

                            if (cbxPlatillos.getValue() != null && cbxBebidas.getValue() != null && cbxCombos.getValue() != null) {
                                vBoxProd.getChildren().addAll(lblPlat, lblBebi, lblComb, lblTotal);
                                vBoxPrecio.getChildren().addAll(lblTotalPlat, lblTotalBebi, lblTotalComb, lblTota);
                            } else {
                                if (cbxPlatillos.getValue() != null && cbxBebidas.getValue() != null && cbxCombos.getValue() == null) {
                                    vBoxProd.getChildren().addAll(lblPlat, lblBebi, lblTotal);
                                    vBoxPrecio.getChildren().addAll(lblTotalPlat, lblTotalBebi, lblTota);
                                } else {
                                    if (cbxPlatillos.getValue() != null && cbxBebidas.getValue() == null && cbxCombos.getValue() == null) {
                                        vBoxProd.getChildren().addAll(lblPlat, lblTotal);
                                        vBoxPrecio.getChildren().addAll(lblTotalPlat, lblTota);
                                    } else {
                                        if (cbxPlatillos.getValue() != null && cbxBebidas.getValue() == null && cbxCombos.getValue() != null) {
                                            vBoxProd.getChildren().addAll(lblPlat, lblComb, lblTotal);
                                            vBoxPrecio.getChildren().addAll(lblTotalPlat, lblTotalComb, lblTota);
                                        } else {
                                            if (cbxPlatillos.getValue() == null && cbxBebidas.getValue() == null && cbxCombos.getValue() != null) {
                                                vBoxProd.getChildren().addAll(lblComb, lblTotal);
                                                vBoxPrecio.getChildren().addAll(lblTotalComb, lblTota);
                                            } else {
                                                if (cbxPlatillos.getValue() == null && cbxBebidas.getValue() != null && cbxCombos.getValue() != null) {
                                                    vBoxProd.getChildren().addAll(lblComb, lblTotal);
                                                    vBoxPrecio.getChildren().addAll(lblTotalComb, lblTota);
                                                } else {
                                                    if (cbxPlatillos.getValue() == null && cbxBebidas.getValue() != null && cbxCombos.getValue() == null) {
                                                        vBoxProd.getChildren().addAll(lblBebi, lblTotal);
                                                        vBoxPrecio.getChildren().addAll(lblTotalBebi, lblTota);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            hBoxComision.getChildren().addAll(lblComision, lblTotalComision);
                            hBoxProd.getChildren().addAll(vBoxProd, vBoxPrecio);
                            hBoxBtn.getChildren().addAll(btnListo, btnFactura);

                            if (ban) {
                                vBoxGrl.getChildren().addAll(lblTitulo, vBoxCuenta, hBoxProd, hBoxComision, hBoxBtn);
                            } else {
                                vBoxGrl.getChildren().addAll(lblTitulo, vBoxCuenta, hBoxProd, hBoxBtn);
                            }

                            escena2 = new Scene(vBoxGrl, 1366, 720);
                            escena2.getStylesheets().add("sample/css/recibo.css");
                            this.setScene(escena2);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Hubo un problema :c");
                            alert.setContentText("NO se permiten valores negativos");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Hubo un problema :c");
                        alert.setContentText("Debe haber almenos un producto");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Hubo un problema :c");
                    alert.setContentText("Debe haber almenos un producto");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Hubo un problema :c");
                alert.setContentText("No has ingresado el tipo de pago");
                alert.showAndWait();
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    //Pantalla 3

    PlatConsumDAO objPlat;
    BebiConsumDAO objBebi;
    ComboConsumDAO objComb;
    FacturasDAO objFDAO;
    SociosDAO objSDAO;
    Label lblSocio, lblCveFactura, lblMesa, lblHora, lblFactura;
    ComboBox<SociosDAO> cbxSocio;
    ComboBox<MesasDAO> cbxMesas;
    VBox vBoxSocio, vBoxMesa, vBoxGeneral, vBoxFactura;
    Button btnGuardar;
    String hrImpresion;
    int cveFactura;

    void FacturasCRUD(){
        objFDAO     = new FacturasDAO();
        objSDAO     = new SociosDAO();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        hrImpresion = "" + dateFormat.format(date);

        objFDAO.ultimaCve();
        cveFactura =(objFDAO.getCveFactura() + 1);

        lblCveFactura = new Label("Clave: "+ cveFactura);
        lblFactura    = new Label("FACTURA");
        lblSocio      = new Label("Socio:");
        lblMesa       = new Label("Mesa número:");
        lblHora       = new Label("Hora: "+hrImpresion);

        cbxSocio = new ComboBox<>();
        cbxMesas = new ComboBox<>();

        cbxSocio.setItems(new SociosDAO().getAllSocios());
        cbxMesas.setItems(new MesasDAO().getAllMesa());

        btnGuardar = new Button("Listo");
        btnGuardar.setOnAction(event -> Insert(3));

        vBoxSocio   = new VBox();
        vBoxMesa    = new VBox();
        vBoxGeneral = new VBox();
        vBoxFactura = new VBox();

        vBoxGeneral.setId("vBoxGeneral");

        vBoxSocio.setSpacing(10);
        vBoxMesa.setSpacing(10);
        vBoxGeneral.setSpacing(20);

        vBoxSocio.setAlignment(Pos.CENTER);
        vBoxMesa.setAlignment(Pos.CENTER);
        vBoxFactura.setAlignment(Pos.CENTER);
        vBoxGeneral.setAlignment(Pos.CENTER);

        vBoxFactura.getChildren().addAll(lblTitulo, lblFactura, lblCveFactura);
        vBoxSocio.getChildren().addAll(lblSocio, cbxSocio);
        vBoxMesa.getChildren().addAll(lblMesa, cbxMesas);
        vBoxGeneral.getChildren().addAll(vBoxFactura, vBoxSocio, vBoxMesa,hBoxProd, hBoxComision, lblHora, btnGuardar);

        escena3 = new Scene(vBoxGeneral, 1366, 720);
        escena3.getStylesheets().add("sample/css/recibo.css");
        this.setScene(escena3);
    }

    void Insert(int opc){
        switch (opc){
            case 1:
                objCUDAO = new CuentasDAO();

                objCUDAO.setCveTipoPago(cbxTipoPago.getSelectionModel().getSelectedIndex()+1);
                objCUDAO.setFecImpresion(fecImpresion);
                objCUDAO.setTotalPagar(total);

                objCUDAO.insCuenta();
                this.close();
                break;
            case 2:
                objCUDAO = new CuentasDAO();

                objCUDAO.setCveTipoPago(cbxTipoPago.getSelectionModel().getSelectedIndex()+1);
                objCUDAO.setFecImpresion(fecImpresion);
                objCUDAO.setTotalPagar(total);

                objCUDAO.insCuenta();
                FacturasCRUD();
                break;
            case 3:
                if(cbxSocio.getValue() != null && cbxMesas.getValue() != null) {
                    objFDAO = new FacturasDAO();
                    objPlat = new PlatConsumDAO();
                    objBebi = new BebiConsumDAO();
                    objComb = new ComboConsumDAO();

                    objSDAO.cveSocio(cbxSocio.getValue() + "");
                    objPDAO.getClave(cbxPlatillos.getValue() + "");
                    objBDAO.getClave(cbxBebidas.getValue() + "");
                    objCDAO.getClave(cbxCombos.getValue() + "");

                    //insertar en tabla Facturas
                    objFDAO.setCveCuenta(cveCuenta);
                    objFDAO.setCveSocios(objSDAO.getCveSocios());
                    objFDAO.setNumMesa(cbxMesas.getSelectionModel().getSelectedIndex() + 1);
                    objFDAO.setHrImpresion(hrImpresion);
                    objFDAO.insFactura();
                    objFDAO.insFactura();

                    objFDAO.ultimaCve();

                    //insertar en tabla platConsum
                    if (cbxPlatillos != null && cantPlatillo > 0) {
                        objPlat.setCvePlatillo(objPDAO.getCvePlatillo());
                        objPlat.setCveFactura(objFDAO.getCveFactura());
                        objPlat.setCantPlat(cantPlatillo);
                        objPlat.insertar();
                    }

                    //insertar en tabla bebiConsum
                    if (cbxBebidas != null && cantBebida > 0) {
                        objBebi.setCveBebidas(objBDAO.getCveBebidas());
                        objBebi.setCveFactura(objFDAO.getCveFactura());
                        objBebi.setCantBebida(cantBebida);
                        objBebi.insertar();
                    }

                    //insertar en tabla comboConsum
                    if (cbxCombos != null && cantCombo > 0) {
                        objComb.setCveCombo(objCDAO.getCveCombo());
                        objComb.setCveFactura(objFDAO.getCveFactura());
                        objComb.setCantCombo(cantCombo);
                        objComb.insertar();
                    }

                    this.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Hubo un problema :c");
                    alert.setContentText("Olvidaste llenar algún campo");
                    alert.showAndWait();
                }
                break;
        }
    }

    float comisionTotal;
    boolean ban;

    void comision(){
        int tipoPago, tipoPagoBanco, comisionBanco;

        objBADAO.elementosComision(cbxBanco.getSelectionModel().getSelectedIndex()+1);

        tipoPago      = cbxTipoPago.getSelectionModel().getSelectedIndex()+1;
        tipoPagoBanco = objBADAO.getCveTipoPago();
        comisionBanco = objBADAO.getComision();

        if (tipoPago == tipoPagoBanco){
            comisionTotal = (total*comisionBanco)/100;
            total += comisionTotal;
            ban = true;
        }else{
            ban = false;
        }
    }
}