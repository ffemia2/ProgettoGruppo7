/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
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
public class SystemAdmin_JDBC extends JDBC{
    private Statement stm;
    private AbstractUser admin;

    public SystemAdmin_JDBC(AbstractUser admin) {
    
        this.admin = admin;
    }

    public Statement getStm() {
        return stm;
    }

    public AbstractUser getAdmin() {
        return admin;
    }
    public void loadUsersFromDatabase(){
        try {
           
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPwd());
            this.stm = conn.createStatement();
            this.loadPlannersFromDatabase();
            this.loadMaintainersFromDatabase();
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveUsersOnDatabase(LinkedList<AbstractUser> users){
        try {
           
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPwd());
            this.stm = conn.createStatement();
            this.savePlannersOnDatabase(users);
            this.saveMaintainersOnDatabase(users);
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void loadPlannersFromDatabase(){
        try {
            ResultSet planners = stm.executeQuery("select * from planner");
            while (planners.next()){
                this.getAdmin().createUser(planners.getString("Username"),planners.getString("Password_"), UserFactory.Role.PLANNER);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void loadMaintainersFromDatabase(){
        try {
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            while (maintainers.next()){
                this.getAdmin().createUser(maintainers.getString("Username"),maintainers.getString("Password_"), UserFactory.Role.MAINTAINER);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                this.getAdmin().getUser(avails.getString("Maintainer")).addSlotAvailability(Integer.valueOf(avails.getString("Week")), UserFactory.weekDay.valueOf(avails.getString("WeekDay")), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void saveMaintainersOnDatabase(LinkedList<AbstractUser> maintainers){
        for(AbstractUser m : maintainers){
            if(m.getRole() == UserFactory.Role.MAINTAINER){
                try {
                    this.getStm().executeUpdate(m.getInsertQuery());
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
                    this.getStm().executeUpdate(m.getInsertQuery());
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
