package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class BebidasDAO {
    int cveBebidas, idTipoBebida;
    String nomBebidas;
    float precio;

    public ObservableList<BebidasDAO> getAllBebida(){
        ObservableList<BebidasDAO> listaB = FXCollections.observableArrayList();

        try{
            BebidasDAO objB;
            String query = "select * from bebidas order by cveBebidas";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objB = new BebidasDAO();
                objB.setCveBebidas(res.getInt("cveBebidas"));
                objB.setIdTipoBebida(res.getInt("idTipoBebida"));
                objB.setNomBebidas(res.getString("nomBebidas"));
                objB.setPrecio(res.getFloat("precio"));
                listaB.add(objB);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaB;
    }

    public void insBebida(){
        try{
            String query = "INSERT INTO bebidas (idTipoBebida,nomBebidas,precio) " +
                    "values ("+idTipoBebida+",'"+nomBebidas+"',"+precio+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void updBebida(){
        try{
            String query = "UPDATE bebidas SET idTipoBebida = "+idTipoBebida+
                    ",nomBebidas = '"+nomBebidas+"'," + "precio = "+precio+
                    " WHERE cveBebidas = "+cveBebidas;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void delBebida(){
        try{
            String query = "DELETE FROM bebidas WHERE cveBebidas = "+cveBebidas;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveBebidas
    public int getCveBebidas() {return cveBebidas;}
    public void setCveBebidas(int cveBebidas) {this.cveBebidas = cveBebidas;}

    //idTipoBebida
    public int getIdTipoBebida() {return idTipoBebida;}
    public void setIdTipoBebida(int idTipoBebida) {this.idTipoBebida = idTipoBebida;}

    //nomBebidas
    public String getNomBebidas() {return nomBebidas;}
    public void setNomBebidas(String nomBebidas) {this.nomBebidas = nomBebidas;}

    //precio
    public float getPrecio() {return precio;}
    public void setPrecio(float precio) {this.precio = precio;}

    @Override
    public String toString(){
        return nomBebidas;
    }

    public void getPrecioBebida(int cveBebidas){
        try{
            String query = "select precio from bebidas where cveBebidas = "+cveBebidas;
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                setPrecio(res.getFloat("precio"));
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void getClave(String nomBebidas){
        try{
            String query = "select cveBebidas from bebidas where nomBebidas = '"+nomBebidas+"'";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                setCveBebidas(res.getInt("cveBebidas"));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
