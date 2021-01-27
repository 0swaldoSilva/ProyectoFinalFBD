package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class TipoPlatilloDAO {
    int idTipoPlat;
    String nomTipoPlat;

    public ObservableList<TipoPlatilloDAO> getAllTipoP(){
        ObservableList<TipoPlatilloDAO> listaTP = FXCollections.observableArrayList();

        try{
            TipoPlatilloDAO objTP;
            String query = "select * from tipoPlatillo order by idTipoPlat";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objTP = new TipoPlatilloDAO();
                objTP.setIdTipoPlat(res.getInt("idTipoPlat"));
                objTP.setNomTipoPlat(res.getString("nomTipoPlat"));
                listaTP.add(objTP);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaTP;
    }

    //idTipoPlat
    public int getIdTipoPlat() {return idTipoPlat;}
    public void setIdTipoPlat(int idTipoPlat) {this.idTipoPlat = idTipoPlat;}

    //nomTipoPlat
    public String getNomTipoPlat() {return nomTipoPlat;}
    public void setNomTipoPlat(String nomTipoPlat) {this.nomTipoPlat = nomTipoPlat;}

    @Override
    public String toString(){
        return nomTipoPlat;
    }
}
