package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class PlatillosDAO {

    int cvePlatillo, idTipoPlat;
    String nomPlatillo;
    float precio;

    public ObservableList<PlatillosDAO> getAllPlatillo(){
        ObservableList<PlatillosDAO> listaP = FXCollections.observableArrayList();

        try{
            PlatillosDAO objP;
            String query = "select * from platillos order by cvePlatillo";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objP = new PlatillosDAO();
                objP.setCvePlatillo(res.getInt("cvePlatillo"));
                objP.setIdTipoPlat(res.getInt("idTipoPlat"));
                objP.setNomPlatillo(res.getString("nomPlatillo"));
                objP.setPrecio(res.getFloat("precio"));
                listaP.add(objP);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaP;
    }

    public void insPlatillo(){
        try{
            String query = "INSERT INTO platillos (idTipoPlat,nomPlatillo,precio) " +
                    "values ("+idTipoPlat+",'"+nomPlatillo+"',"+precio+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void updPlatillo(){
        try{
            String query = "UPDATE platillos SET idTipoPlat = "+idTipoPlat+
                    ",nomPlatillo = '"+nomPlatillo+"'," + "precio = "+precio+
                    " WHERE cvePlatillo = "+cvePlatillo;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void delPlatillo(){
        try{
            String query = "DELETE FROM platillos WHERE cvePlatillo = "+cvePlatillo;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cvePlatillo
    public int getCvePlatillo() {return cvePlatillo;}
    public void setCvePlatillo(int cvePlatillo) {this.cvePlatillo = cvePlatillo;}

    //idTipoPlat
    public int getIdTipoPlat() {return idTipoPlat;}
    public void setIdTipoPlat(int idTipoPlat) {this.idTipoPlat = idTipoPlat;}

    //nomPlatillo
    public String getNomPlatillo() {return nomPlatillo;}
    public void setNomPlatillo(String nomPlatillo) {this.nomPlatillo = nomPlatillo;}

    //precio
    public float getPrecio() {return precio;}
    public void setPrecio(float precio) {this.precio = precio;}

    @Override
    public String toString(){
        return nomPlatillo;
    }

    public void getPrecioPlatillo(int cvePlatillo){
        try{
            String query = "select precio from platillos where cvePlatillo = "+cvePlatillo;
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                setPrecio(res.getFloat("precio"));
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void getClave(String nomPlatillo){
        try{
            String query = "select cvePlatillo from platillos where nomPlatillo = '"+nomPlatillo+"'";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                setCvePlatillo(res.getInt("cvePlatillo"));
            }

        }catch (Exception e){e.printStackTrace();}
    }
}
