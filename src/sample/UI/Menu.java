package sample.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Stage {

    Scene  escena;
    Button btnEmpleado, btnSocio, btnBebida, btnPlatillo, btnCombo, btnCuenta,btnSalir;
    Label  lblEmpleado, lblSocio, lblBebida, lblPlatillo, lblCombo, lblCuenta,lblSalir;
    VBox   vBoxEmpleado, vBoxSocio, vBoxBebida, vBoxPlatillo, vBoxCombo, vBoxCuenta,
           vBoxSalir, vBoxLeft, vBoxCenter, vBoxRight;
    HBox   hBoxGrid;

    public Menu(){
        CrarUI();

        this.setTitle("Administración de Restaurante-Bar \"La iglesia\"");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrarUI() {
        //inicialización botones
        btnEmpleado = new Button();
        btnSocio    = new Button();
        btnBebida   = new Button();
        btnPlatillo = new Button();
        btnCombo    = new Button();
        btnCuenta   = new Button();
        btnSalir    = new Button();

        //accion al presionar el botón.
        btnEmpleado.setOnAction(event -> opcionMenu(1));
        btnSocio.setOnAction(event -> opcionMenu(2));
        btnBebida.setOnAction(event -> opcionMenu(3));
        btnPlatillo.setOnAction(event -> opcionMenu(4));
        btnCombo.setOnAction(event -> opcionMenu(5));
        btnCuenta.setOnAction(event -> opcionMenu(6));
        btnSalir.setOnAction(event -> opcionMenu(7));

        //inicialización etiquetas
        lblEmpleado = new Label("EMPLEADO");
        lblSocio    = new Label("SOCIO");
        lblBebida   = new Label("BEBIDA");
        lblPlatillo = new Label("PLATILLO");
        lblCombo    = new Label("COMBO");
        lblCuenta   = new Label("CUENTA");
        lblSalir    = new Label("SALIR");

        //id
        btnEmpleado.setId("btnEmpleado");
        btnSocio.setId("btnSocio");
        btnBebida.setId("btnBebida");
        btnPlatillo.setId("btnPlatillo");
        btnCombo.setId("btnCombo");
        btnCuenta.setId("btnCuenta");
        btnSalir.setId("btnSalir");

        //Ancho de los botones
        btnEmpleado.setPrefSize(150,150);
        btnSocio.setPrefSize(150,150);
        btnBebida.setPrefSize(150,150);
        btnPlatillo.setPrefSize(150,150);
        btnCombo.setPrefSize(150,150);
        btnCuenta.setPrefSize(150,150);
        btnSalir.setPrefSize(150,150);

        //inicialización cajas
        vBoxEmpleado = new VBox();
        vBoxSocio    = new VBox();
        vBoxBebida   = new VBox();
        vBoxPlatillo = new VBox();
        vBoxCombo    = new VBox();
        vBoxCuenta   = new VBox();
        vBoxSalir    = new VBox();
        vBoxLeft     = new VBox();
        vBoxCenter   = new VBox();
        vBoxRight    = new VBox();
        hBoxGrid     = new HBox();

        //espacio cajas.
        vBoxEmpleado.setSpacing(10);
        vBoxSocio.setSpacing(10);
        vBoxBebida.setSpacing(10);
        vBoxPlatillo.setSpacing(10);
        vBoxCombo.setSpacing(10);
        vBoxCuenta.setSpacing(10);
        vBoxSalir.setSpacing(10);
        vBoxLeft.setSpacing(20);
        vBoxCenter.setSpacing(20);
        hBoxGrid.setSpacing(20);

        //Posición
        vBoxEmpleado.setAlignment(Pos.CENTER);
        vBoxSocio.setAlignment(Pos.CENTER);
        vBoxBebida.setAlignment(Pos.CENTER);
        vBoxPlatillo.setAlignment(Pos.CENTER);
        vBoxCombo.setAlignment(Pos.CENTER);
        vBoxCuenta.setAlignment(Pos.CENTER);
        vBoxSalir.setAlignment(Pos.CENTER);
        vBoxLeft.setAlignment(Pos.CENTER);
        vBoxCenter.setAlignment(Pos.CENTER);
        vBoxRight.setAlignment(Pos.CENTER);
        hBoxGrid.setAlignment(Pos.CENTER);

        //Agregar elementos a las cajas.
        vBoxEmpleado.getChildren().addAll(btnEmpleado, lblEmpleado);
        vBoxSocio.getChildren().addAll(btnSocio, lblSocio);
        vBoxBebida.getChildren().addAll(btnBebida, lblBebida);
        vBoxPlatillo.getChildren().addAll(btnPlatillo, lblPlatillo);
        vBoxCombo.getChildren().addAll(btnCombo, lblCombo);
        vBoxCuenta.getChildren().addAll(btnCuenta, lblCuenta);
        vBoxSalir.getChildren().addAll(btnSalir, lblSalir);
        vBoxLeft.getChildren().addAll(vBoxEmpleado, vBoxBebida, vBoxCombo);
        vBoxCenter.getChildren().addAll(vBoxSocio, vBoxPlatillo, vBoxCuenta);
        vBoxRight.getChildren().addAll(vBoxSalir);
        hBoxGrid.getChildren().addAll(vBoxLeft, vBoxCenter, vBoxRight);

        //Asignación de escena
        escena = new Scene(hBoxGrid);
        escena.getStylesheets().add("sample/css/fbd.css");
    }

    void opcionMenu(int opc){
        switch (opc){
            case 1: new EmpleadosCRUD();
                break;
            case 2: new SociosCRUD();
                break;
            case 3: new BebidasCRUD();
                break;
            case 4: new PlatillosCRUD();
                break;
            case 5: new CombosCRUD();
                break;
            case 6: new ProcesoPagoCRUD();
                break;
            case 7: System.exit(0);
                break;
        }
    }
}
