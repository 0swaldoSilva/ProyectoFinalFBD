package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class SociosDAO {
    int cveSocios;
    String nomSocios, telefono, domicilio;

    public ObservableList<SociosDAO> getAllSocios(){
        ObservableList<SociosDAO> listaS = FXCollections.observableArrayList();

        try{
            SociosDAO objS;
            String query = "select * from socios order by cveSocios";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objS = new SociosDAO();
                objS.setCveSocios(res.getInt("cveSocios"));
                objS.setNomSocios(res.getString("nomSocios"));
                objS.setTelefono(res.getString("telefono"));
                objS.setDomicilio(res.getString("domicilio"));
                listaS.add(objS);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaS;
    }

    public void insSocio(){
        try{
            String query = "INSERT INTO socios (nomSocios,telefono,domicilio) " +
                    "values ('"+nomSocios+"','"+telefono+"','"+domicilio+"')";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void updSocio(){
        try{
            String query = "UPDATE socios SET nomSocios = '"+nomSocios+"'," +
                    "domicilio = '"+domicilio+"', telefono = "+telefono+
                    " WHERE cveSocios = "+cveSocios;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void delSocio(){
        try{
            String query = "DELETE FROM socios WHERE cveSocios = "+cveSocios;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveSocios
    public int getCveSocios() {return cveSocios;}
    public void setCveSocios(int cveSocios) {this.cveSocios = cveSocios;}

    //nomSocios
    public String getNomSocios() {return nomSocios;}
    public void setNomSocios(String nomSocios) {this.nomSocios = nomSocios;}

    //telefono
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    //domicilio
    public String getDomicilio() {return domicilio;}
    public void setDomicilio(String domicilio) {this.domicilio = domicilio;}

    public String toString(){return nomSocios;}

    public void cveSocio(String nomSocios){
        try {
            String query = "select cveSocios from socios where nomSocios = '"+ nomSocios+"';";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                setCveSocios(res.getInt("cveSocios"));
            }
        }catch (Exception e){}
    }
}
