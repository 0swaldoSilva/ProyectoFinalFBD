package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CuentasDAO {
    int cveCuenta, cveTipoPago;
    String fecImpresion;
    float totalPagar;

    public ObservableList<CuentasDAO> getAllCuenta() {
        ObservableList<CuentasDAO> listaC = FXCollections.observableArrayList();

        try {
            CuentasDAO objC;
            String query = "select * from cuenta order by cveCuenta";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objC = new CuentasDAO();
                objC.setCveCuenta(res.getInt("cveCuenta"));
                objC.setCveTipoPago(res.getInt("cveTipoPago"));
                objC.setFecImpresion(res.getString("fecImpresion"));
                objC.setTotalPagar(res.getFloat("totalPagar"));
                listaC.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaC;
    }

    public void insCuenta(){
        try{
            String query = "INSERT INTO cuenta (cveTipoPago,fecImpresion,totalPagar) " +
                    "values ("+cveTipoPago+",'"+fecImpresion+"',"+totalPagar+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveCuenta
    public int getCveCuenta() {return cveCuenta;}
    public void setCveCuenta(int cveCuenta) {this.cveCuenta = cveCuenta;}

    //cveTipoPago
    public int getCveTipoPago() {return cveTipoPago;}
    public void setCveTipoPago(int cveTipoPago) {this.cveTipoPago = cveTipoPago;}

    //fecImpresion
    public String getNomBebidas() {return fecImpresion;}
    public void setFecImpresion(String fecImpresion) {this.fecImpresion = fecImpresion;}

    //totalPagar
    public float getTotalPagar() {return totalPagar;}
    public void setTotalPagar(float totalPagar) {this.totalPagar = totalPagar;}

    public void ultimaCve(){
        try{
            String query = "SELECT cveCuenta from cuenta order by cveCuenta desc limit 1;";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                setCveCuenta(res.getInt("cveCuenta"));
            }
        }catch (Exception e){}
    }
}
