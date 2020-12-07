/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grazia D'Amore
 */
public class Connection {{
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String pwd = "ciao98";

        
        try {
            java.sql.Connection conn = null;
            Class.forName("org.postgresql.Driver");
             
            conn = DriverManager.getConnection(url, user, pwd);
            Statement stm = conn.createStatement();
            
            //conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }

}}
