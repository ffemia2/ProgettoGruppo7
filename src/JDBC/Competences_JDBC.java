/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Competence;
import progettogruppo7.Competences;

/**
 *
 * @author Rosanna
 */
public class Competences_JDBC extends JDBC {
    private Statement stm;
    private Competences competences;
    
    public Competences_JDBC(Competences competences) throws SQLException {
    {
        String url = "jdbc:postgresql://localhost/prova7";
        String user = "postgres";
        String pwd = "Spatzle126";

        try {
            java.sql.Connection conn = null;
            Class.forName("org.postgresql.Driver");
             
            conn = DriverManager.getConnection(url, user, pwd);
            this.stm = conn.createStatement();
            
            //conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }  
        this.competences = competences;
    }
    
    public void loadCompetencesFromDatabase(Competences competences){
         try {
            ResultSet competence = stm.executeQuery("select * from competence");
            while (competence.next()){
                String nome = competence.getString("nome");
                Competence comp = new Competence(nome);
                this.competences.insertCompetence(comp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void saveCompetencesOnDatabase(){ 
        for(Competence c : this.competences){
                try {
                    String sql = "select * from competence where nome='" + c.getName() + "'";
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next());
                    else {
                        stm.executeUpdate("insert into competence " + "values('" + c.getName() + "')");
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
}
