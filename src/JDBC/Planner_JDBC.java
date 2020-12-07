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
import progettogruppo7.Users.AbstractUser;
import progettogruppo7.Users.SystemAdministrator;
import progettogruppo7.Users.UserFactory;

/**
 *
 * @author Grazia D'Amore
 */
public class Planner_JDBC {
    private Statement stm;
    private AbstractUser planner;
    
    
    public Planner_JDBC(AbstractUser planner) {
     {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String pwd = "ciao98";

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
    this.planner = planner;
    }
    
    public void loadMaintainersFromDatabase(AbstractUser planner){
         try {
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            while (maintainers.next()){
                planner.createUser(maintainers.getString("Username"),"", UserFactory.Role.MAINTAINER);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                planner.getUser(avails.getString("Maintainer")).addSlotAvailability(Integer.valueOf(avails.getString("Week")), UserFactory.weekDay.valueOf(avails.getString("WeekDay")), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
}
