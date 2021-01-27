package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion{
    private static String server = "127.0.0.1";
    private static String user   = "root";
    private static String pwd    = "oswaldo1234";
    private static String db     = "restaurantefbd";

    public static Connection con;

    public static void crearConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+server+":3306/"+db, user, pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
