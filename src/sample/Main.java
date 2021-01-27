package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.UI.Menu;
import sample.models.Conexion;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Conexion.crearConexion();

        new Menu();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
