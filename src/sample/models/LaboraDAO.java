package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class LaboraDAO {
    int cveEmpleado, cantJornadas;
    String cveJornada;

    public ObservableList<LaboraDAO> getAllLabora(){
        ObservableList<LaboraDAO> listaL = FXCollections.observableArrayList();

        try{
            LaboraDAO objL;
            String query = "select * from labora";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objL = new LaboraDAO();
                objL.setCveEmpleado(res.getInt("cveEmpleado"));
                objL.setCantJornadas(res.getInt("cantJornadas"));
                objL.setCveJornada(res.getString("cveJornada"));
                listaL.add(objL);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaL;
    }

    //cveEmpleado
    public int getCveEmpleado() {return cveEmpleado;}
    public void setCveEmpleado(int cveEmpleado) {this.cveEmpleado = cveEmpleado;}

    //cantJornadas
    public int getCantJornadas() {return cantJornadas;}
    public void setCantJornadas(int cantJornadas) {this.cantJornadas = cantJornadas;}

    //cveJornada
    public String getCveJornada() {return cveJornada;}
    public void setCveJornada(String cveJornada) {this.cveJornada = cveJornada;}

    @Override
    public String toString(){
        return cveJornada;
    }
}
