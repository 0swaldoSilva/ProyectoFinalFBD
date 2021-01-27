package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CombosDAO {

    int cveCombo;
    String nomCombo;
    float precio;

    public ObservableList<CombosDAO> getAllCombo(){
        ObservableList<CombosDAO> listaP = FXCollections.observableArrayList();

        try{
            CombosDAO objP;
            String query = "select * from combo order by cveCombo";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objP = new CombosDAO();
                objP.setCveCombo(res.getInt("cveCombo"));
                objP.setNomCombo(res.getString("nomCombo"));
                objP.setPrecio(res.getFloat("precio"));
                listaP.add(objP);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaP;
    }

    public void insCombo(){
        try{
            String query = "INSERT INTO combo (nomCombo,precio) " +
                    "values ('"+nomCombo+"',"+precio+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void updCombo(){
        try{
            String query = "UPDATE combo SET nomCombo = '"+nomCombo+"'," + "precio = "+precio+
                    " WHERE cveCombo = "+cveCombo;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void delCombo(){
        try{
            String query = "DELETE FROM combo WHERE cveCombo = "+cveCombo;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveCombo
    public int getCveCombo() {return cveCombo;}
    public void setCveCombo(int cveCombo) {this.cveCombo = cveCombo;}

    //nomCombo
    public String getNomCombo() {return nomCombo;}
    public void setNomCombo(String nomCombo) {this.nomCombo = nomCombo;}

    //precio
    public float getPrecio() {return precio;}
    public void setPrecio(float precio) {this.precio = precio;}

    @Override
    public String toString(){
        return nomCombo;
    }

    public void getPrecioCombo(int cveCombo){
        try{
            String query = "select precio from combo where cveCombo = "+cveCombo;
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                setPrecio(res.getFloat("precio"));
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void getClave(String nomCombo){
        try{
            String query = "select cveCombo from combo where nomCombo = '"+ nomCombo+"'";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                setCveCombo(res.getInt("cveCombo"));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
