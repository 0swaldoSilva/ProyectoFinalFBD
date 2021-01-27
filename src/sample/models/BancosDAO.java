package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class BancosDAO {
    int cveBanco, cveTipoPago, comision;
    String nomBanco;

    public ObservableList<BancosDAO> getAllBanco(){
        ObservableList<BancosDAO> listaB = FXCollections.observableArrayList();

        try{
            BancosDAO objB;
            String query = "select * from banco order by cveBanco";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objB = new BancosDAO();
                objB.setCveBanco(res.getInt("cveBanco"));
                objB.setCveTipoPago(res.getInt("cveTipoPago"));
                objB.setComision(res.getInt("comision"));
                objB.setNomBanco(res.getString("nomBanco"));
                listaB.add(objB);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaB;
    }

    //cveCombo
    public int getCveBanco() {return cveBanco;}
    public void setCveBanco(int cveBanco) {this.cveBanco = cveBanco;}

    //cveTipoPago
    public int getCveTipoPago() {return cveTipoPago;}
    public void setCveTipoPago(int cveTipoPago) {this.cveTipoPago = cveTipoPago;}

    //comision
    public int getComision() {return comision;}
    public void setComision(int comision) {this.comision = comision;}

    //nomCombo
    public String getNomBanco() {return nomBanco;}
    public void setNomBanco(String nomBanco) {this.nomBanco = nomBanco;}

    @Override
    public String toString(){
        return nomBanco;
    }

    public void elementosComision(int cveBanco){
        try{
            String query = "select cveTipoPago, comision from banco where cveBanco = "+ cveBanco;
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                setCveTipoPago(res.getInt("cveTipoPago"));
                setComision(res.getInt("comision"));
            }
        }catch(Exception e){}
    }
}
