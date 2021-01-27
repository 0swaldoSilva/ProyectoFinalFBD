package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class JornadasDAO {
    String cveJornada, dscJornada, horario;

    public ObservableList<JornadasDAO> getAllJornada(){
        ObservableList<JornadasDAO> listaJ = FXCollections.observableArrayList();

        try{
            JornadasDAO objJ;
            String query = "select * from jornada order by cveJornada";
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objJ = new JornadasDAO();
                objJ.setCveJornada(res.getString("cveJornada"));
                objJ.setDscJornada(res.getString("dscJornada"));
                objJ.setHorario(res.getString("horario"));
                listaJ.add(objJ);
            }
        }catch (Exception e){e.printStackTrace();}
        return listaJ;
    }

    //cveJornada
    public String getCveJornada() {return cveJornada;}
    public void setCveJornada(String cveJornada) {this.cveJornada = cveJornada;}

    //dscJornada
    public String getDscJornada() {return dscJornada;}
    public void setDscJornada(String dscJornada) {this.dscJornada = dscJornada;}

    //horario
    public String getHorario() {return horario;}
    public void setHorario(String horario) {this.horario = horario;}
}
