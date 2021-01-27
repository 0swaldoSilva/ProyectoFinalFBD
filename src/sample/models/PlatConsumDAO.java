package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class PlatConsumDAO {
    int cvePlatillo, cveFactura, cantPlat;

    public ObservableList<PlatConsumDAO> getAllPlat(){
        ObservableList<PlatConsumDAO> listaP = FXCollections.observableArrayList();

        try{
            PlatConsumDAO objP;
            String query = "select * from platConsum";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objP = new PlatConsumDAO();
                objP.setCvePlatillo(res.getInt("cvePlatillo"));
                objP.setCveFactura(res.getInt("cveFactura"));
                objP.setCantPlat(res.getInt("cantPlat"));
                listaP.add(objP);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaP;
    }

    public void insertar(){
        try{
            String query = "INSERT INTO platConsum (cvePlatillo,cveFactura,cantPlat) " +
                    "values ("+cvePlatillo+","+cveFactura+","+cantPlat+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cvePlatillo
    public int getCvePlatillo() {return cvePlatillo;}
    public void setCvePlatillo(int cvePlatillo) {this.cvePlatillo = cvePlatillo;}

    //cveFactura
    public int getCveFactura() {return cveFactura;}
    public void setCveFactura(int cveFactura) {this.cveFactura = cveFactura;}

    //cantPlat
    public int getCantPlat() {return cantPlat;}
    public void setCantPlat(int cantPlat) {this.cantPlat = cantPlat;}
}
