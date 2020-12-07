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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Users.AbstractUser;
import progettogruppo7.Users.UserFactory;

/**
 *
 * @author Grazia D'Amore
 */
public class SystemAdmin_JDBC {
    private Statement stm;
    private AbstractUser admin;

    public SystemAdmin_JDBC(AbstractUser admin) {
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
        this.admin = admin;
    }
    
    public void loadUsersFromDatabase(AbstractUser admin){
        this.loadPlannersFromDatabase(admin);
        this.loadMaintainersFromDatabase(admin);
    }
    
    public void saveUsersOnDatabase(LinkedList<AbstractUser> users){
        this.savePlannersOnDatabase(users);
        this.saveMaintainersOnDatabase(users);
    }
    
    public void loadPlannersFromDatabase(AbstractUser admin){
        try {
            ResultSet planners = stm.executeQuery("select * from planner");
            while (planners.next()){
                admin.createUser(planners.getString("Username"),planners.getString("Password_"), UserFactory.Role.PLANNER);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void loadMaintainersFromDatabase(AbstractUser admin){
        try {
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            while (maintainers.next()){
                admin.createUser(maintainers.getString("Username"),maintainers.getString("Password_"), UserFactory.Role.MAINTAINER);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                admin.getUser(avails.getString("Maintainer")).addSlotAvailability(Integer.valueOf(avails.getString("Week")), UserFactory.weekDay.valueOf(avails.getString("WeekDay")), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void saveMaintainersOnDatabase(LinkedList<AbstractUser> maintainers){
        for(AbstractUser m : maintainers){
            if(m.getRole() == UserFactory.Role.MAINTAINER){
                try {
                    stm.executeUpdate(m.getInsertQuery());
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void savePlannersOnDatabase(LinkedList<AbstractUser> planners){
        for(AbstractUser m : planners){
            if (m.getRole() == UserFactory.Role.PLANNER){
                try {
                    stm.executeUpdate(m.getInsertQuery());
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
