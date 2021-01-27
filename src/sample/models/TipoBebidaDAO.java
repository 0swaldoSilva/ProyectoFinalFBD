package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class TipoBebidaDAO {
    int idTipoBebida;
    String nomTipoBebida;

    public ObservableList<TipoBebidaDAO> getAllTipoB(){
        ObservableList<TipoBebidaDAO> listaTB = FXCollections.observableArrayList();

        try{
            TipoBebidaDAO objTB;
            String query = "select * from tipoBebida order by idTipoBebida";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objTB = new TipoBebidaDAO();
                objTB.setIdTipoBebida(res.getInt("idTipoBebida"));
                objTB.setNomTipoBebida(res.getString("nomTipoBebida"));
                listaTB.add(objTB);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaTB;
    }

    //cveTipoPago
    public int getIdTipoBebida() {return idTipoBebida;}
    public void setIdTipoBebida(int idTipoBebida) {this.idTipoBebida = idTipoBebida;}

    //nomTipoPago
    public String getNomTipoBebida() {return nomTipoBebida;}
    public void setNomTipoBebida(String nomTipoBebida) {this.nomTipoBebida = nomTipoBebida;}

    @Override
    public String toString(){
        return nomTipoBebida;
    }
}
