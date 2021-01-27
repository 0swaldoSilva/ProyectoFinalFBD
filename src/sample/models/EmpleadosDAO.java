package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {

    int cveEmpleado, cvePuesto;
    String nomEmpleado, rfc, domicilio, telefono, nomPuesto;

    public ObservableList<EmpleadosDAO> getAllEmpleados(){
        ObservableList<EmpleadosDAO> listaE = FXCollections.observableArrayList();

        try{
            EmpleadosDAO objE;
            String query = "select * from empleados order by cveEmpleado";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objE = new EmpleadosDAO();
                objE.setCveEmpleado(res.getInt("cveEmpleado"));
                objE.setNomEmpleado(res.getString("nomEmpleado"));
                objE.setRfc(res.getString("rfc"));
                objE.setDomicilio(res.getString("domicilio"));
                objE.setTelefono(res.getString("telefono"));
                listaE.add(objE);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaE;
    }

    public void insEmpleado(){
        try{
            String query = "INSERT INTO empleados (cvePuesto,nomEmpleado,rfc,domicilio,telefono) " +
                    "values ("+cvePuesto+",'"+nomEmpleado+"','"+rfc+"','"+domicilio+"','"+telefono+"')";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void updEmpleado(){
        try{
            String query = "UPDATE empleados SET cvePuesto = "+cvePuesto+", nomEmpleado = '"+nomEmpleado+"'," +
                    "rfc = '"+rfc+"', domicilio = '"+domicilio+"', telefono = '"+telefono+
                    "' WHERE cveEmpleado = "+cveEmpleado;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    public void delEmpleado(){
        try{
            String query = "DELETE FROM empleados WHERE cveEmpleado = "+cveEmpleado;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){e.printStackTrace();}
    }

    //cveEmpleado
    public int getCveEmpleado() {return cveEmpleado;}
    public void setCveEmpleado(int cveEmpleado) {this.cveEmpleado = cveEmpleado;}

    //cvePuesto
    public int getCvePuesto() {return cvePuesto;}
    public void setCvePuesto(int cvePuesto) {this.cvePuesto = cvePuesto;}

    //nomEmpleado
    public String getNomEmpleado() {return nomEmpleado;}
    public void setNomEmpleado(String nomEmpleado) {this.nomEmpleado = nomEmpleado;}

    //rfc
    public String getRfc() {return rfc;}
    public void setRfc(String rfc) {this.rfc = rfc;}

    //domicilio
    public String getDomicilio() {return domicilio;}
    public void setDomicilio(String domicilio) {this.domicilio = domicilio;}

    //telefono
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getNomPuesto() {return nomPuesto;}
    public void setNomPuesto(String nomPuesto) {this.nomPuesto = nomPuesto;}
}
