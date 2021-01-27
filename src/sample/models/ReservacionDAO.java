package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class ReservacionDAO {
    int cveReserv, numMesa, cantMesas, numPersonas;
    String horario;
    String fecha;

    public ObservableList<ReservacionDAO> getAllReservacion(){
        ObservableList<ReservacionDAO> listaR = FXCollections.observableArrayList();

        try{
            ReservacionDAO objR;
            String query = "select * from reservacion order by cveReserv";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objR = new ReservacionDAO();
                objR.setCveReserv(res.getInt("cveReserv"));
                objR.setNumMesa(res.getInt("numMesa"));
                objR.setCantMesas(res.getInt("cantMesas"));
                objR.setHorario(res.getString("horario"));
                objR.setFecha(res.getString("fecha"));
                objR.setNumPersonas(res.getInt("numPersonas"));
                listaR.add(objR);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaR;
    }

    //cveReserv
    public int getCveReserv() {return cveReserv;}
    public void setCveReserv(int cveReserv) {this.cveReserv = cveReserv;}

    //numMesa
    public int getNumMesa() {return numMesa;}
    public void setNumMesa(int numMesa) {this.numMesa = numMesa;}

    //cantMesas
    public int getCantMesas() {return cantMesas;}
    public void setCantMesas(int cantMesas) {this.cantMesas = cantMesas;}

    //numPersonas
    public int getNumPersonas() {return numPersonas;}
    public void setNumPersonas(int numPersonas) {this.numPersonas = numPersonas;}

    //fecImpresion
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}

    //horario
    public String getHorario() {return horario;}
    public void setHorario(String horario) {this.horario = horario;}
}
