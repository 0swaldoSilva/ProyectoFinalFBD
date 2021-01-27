package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ComboConsumDAO {
    int cveCombo, cveFactura, cantCombo;

    public ObservableList<ComboConsumDAO> getAllComb(){
        ObservableList<ComboConsumDAO> listaC = FXCollections.observableArrayList();

        try{
            ComboConsumDAO objC;
            String query = "select * from comboConsum";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objC = new ComboConsumDAO();
                objC.setCveCombo(res.getInt("cveCombo"));
                objC.setCveFactura(res.getInt("cveFactura"));
                objC.setCantCombo(res.getInt("cantCombo"));
                listaC.add(objC);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaC;
    }

    public void insertar(){
        try{
            String query = "INSERT INTO comboConsum (cveCombo,cveFactura,cantCombo) " +
                    "values ("+cveCombo+","+cveFactura+","+cantCombo+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveCombo
    public int getCveCombo() {return cveCombo;}
    public void setCveCombo(int cveCombo) {this.cveCombo = cveCombo;}

    //cveFactura
    public int getCveFactura() {return cveFactura;}
    public void setCveFactura(int cveFactura) {this.cveFactura = cveFactura;}

    //cantCombo
    public int getCantCombo() {return cantCombo;}
    public void setCantCombo(int cantCombo) {this.cantCombo = cantCombo;}
}
