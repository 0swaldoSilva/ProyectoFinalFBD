package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class BebiConsumDAO {
    int cveBebidas, cveFactura, cantBebida;

    public ObservableList<BebiConsumDAO> getAllBebi(){
        ObservableList<BebiConsumDAO> listaB = FXCollections.observableArrayList();

        try{
            BebiConsumDAO objB;
            String query = "select * from bebiConsum";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objB = new BebiConsumDAO();
                objB.setCveBebidas(res.getInt("cveBebida"));
                objB.setCveFactura(res.getInt("cveFactura"));
                objB.setCantBebida(res.getInt("cantBebida"));
                listaB.add(objB);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaB;
    }

    public void insertar(){
        try{
            String query = "INSERT INTO bebiConsum (cveBebidas,cveFactura,cantBebida) " +
                    "values ("+cveBebidas+","+cveFactura+","+cantBebida+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveBebidas
    public int getCveBebidas() {return cveBebidas;}
    public void setCveBebidas(int cveBebidas) {this.cveBebidas = cveBebidas;}

    //cveFactura
    public int getCveFactura() {return cveFactura;}
    public void setCveFactura(int cveFactura) {this.cveFactura = cveFactura;}

    //cantBebida
    public int getCantBebida() {return cantBebida;}
    public void setCantBebida(int cantBebida) {this.cantBebida = cantBebida;}
}
