package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class PuestosDAO {
    int cvePuesto;
    String nomPuesto;
    float sueldo;

    public ObservableList<PuestosDAO> getAllPuesto(){
        ObservableList<PuestosDAO> listaP = FXCollections.observableArrayList();

        try{
            PuestosDAO objP;
            String query = "select * from puestos order by cvePuesto";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objP = new PuestosDAO();
                objP.setCvePuesto(res.getInt("cvePuesto"));
                objP.setNomPuesto(res.getString("nomPuesto"));
                objP.setSueldo(res.getFloat("sueldo"));
                listaP.add(objP);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaP;
    }

    //cvePuesto
    public int getCvePuesto() {return cvePuesto;}
    public void setCvePuesto(int cvePuesto) {this.cvePuesto = cvePuesto;}

    //nomPuesto
    public String getNomPuesto() {return nomPuesto;}
    public void setNomPuesto(String nomPuesto) {this.nomPuesto = nomPuesto;}

    //sueldo
    public float getSueldo() {return sueldo;}
    public void setSueldo(float sueldo) {this.sueldo = sueldo;}

    @Override
    public String toString(){
        return nomPuesto;
    }
}
