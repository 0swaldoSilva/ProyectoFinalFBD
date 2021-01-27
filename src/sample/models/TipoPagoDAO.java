package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class TipoPagoDAO {
    int cveTipoPago;
    String nomTipoPago;

    public ObservableList<TipoPagoDAO> getAllTipoPago(){
        ObservableList<TipoPagoDAO> listaTP = FXCollections.observableArrayList();

        try{
            TipoPagoDAO objTP;
            String query = "select * from tipoPago order by cveTipoPago";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objTP = new TipoPagoDAO();
                objTP.setCveTipoPago(res.getInt("cveTipoPago"));
                objTP.setNomTipoPago(res.getString("nomTipoPago"));
                listaTP.add(objTP);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaTP;
    }

    //cveTipoPago
    public int getCveTipoPago() {return cveTipoPago;}
    public void setCveTipoPago(int cveTipoPago) {this.cveTipoPago = cveTipoPago;}

    //nomTipoPago
    public String getNomTipoPago() {return nomTipoPago;}
    public void setNomTipoPago(String nomTipoPago) {this.nomTipoPago = nomTipoPago;}

    @Override
    public String toString(){
        return nomTipoPago;
    }
}
