package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class MesasDAO {
    int numMesa, cveEmpleado, maxLugares;

    public ObservableList<MesasDAO> getAllMesa(){
        ObservableList<MesasDAO> listaM = FXCollections.observableArrayList();

        try{
            MesasDAO objM;
            String query = "select * from mesas order by numMesa";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objM = new MesasDAO();
                objM.setNumMesa(res.getInt("numMesa"));
                objM.setCveEmpleado(res.getInt("cveEmpleado"));
                objM.setMaxLugares(res.getInt("maxLugares"));
                listaM.add(objM);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaM;
    }

    //numMesa
    public int getNumMesa() {return numMesa;}
    public void setNumMesa(int numMesa) {this.numMesa = numMesa;}

    //cveEmpleado
    public int getCveEmpleado() {return cveEmpleado;}
    public void setCveEmpleado(int cveEmpleado) {this.cveEmpleado = cveEmpleado;}

    //maxLugares
    public int getMaxLugares() {return maxLugares;}
    public void setMaxLugares(int maxLugares) {this.maxLugares = maxLugares;}

    @Override
    public String toString(){
        return numMesa+"";
    }
}
