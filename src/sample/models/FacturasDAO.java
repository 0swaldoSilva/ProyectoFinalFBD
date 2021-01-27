package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class FacturasDAO {
    int cveFactura, cveCuenta, cveSocios, numMesa;
    String hrImpresion;

    public ObservableList<FacturasDAO> getAllFactura(){
        ObservableList<FacturasDAO> listaF = FXCollections.observableArrayList();

        try{
            FacturasDAO objF;
            String query = "select * from facturas order by cveFactura";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objF = new FacturasDAO();
                objF.setCveFactura(res.getInt("cveFactura"));
                objF.setCveCuenta(res.getInt("cveCuenta"));
                objF.setCveSocios(res.getInt("cveSocios"));
                objF.setNumMesa(res.getInt("numMesa"));
                objF.setHrImpresion(res.getString("hrImpresion"));
                listaF.add(objF);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaF;
    }

    public void insFactura(){
        try{
            String query = "INSERT INTO facturas (cveCuenta,cveSocios,numMesa,hrImpresion) " +
                    "values ("+cveCuenta+","+cveSocios+","+numMesa+",'"+hrImpresion+"')";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveFactura
    public int getCveFactura() {return cveFactura;}
    public void setCveFactura(int cveFactura) {this.cveFactura = cveFactura;}

    //cveCuenta
    public int getCveCuenta() {return cveCuenta;}
    public void setCveCuenta(int cveCuenta) {this.cveCuenta = cveCuenta;}

    //cveSocios
    public int getCveSocios() {return cveSocios;}
    public void setCveSocios(int cveSocios) {this.cveSocios = cveSocios;}

    //numMesa
    public int getNumMesa() {return numMesa;}
    public void setNumMesa(int numMesa) {this.numMesa = numMesa;}

    //hrImpresion
    public String getHrImpresion() {return hrImpresion;}
    public void setHrImpresion(String hrImpresion) {this.hrImpresion = hrImpresion;}

    public void ultimaCve(){
        try{
            String query = "SELECT cveFactura from facturas order by cveFactura desc limit 1;";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                setCveFactura(res.getInt("cveFactura"));
            }
        }catch (Exception e){}
    }
}
