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
import progettogruppo7.Users.User;
import progettogruppo7.Users.UserFactory;

/**
 *
 * @author Grazia D'Amore
 */
public class Planner_JDBC extends JDBC{
    private Statement stm;
    private AbstractUser planner;
    
    
    public Planner_JDBC(AbstractUser planner) {
     
        {
            String url = super.getUrl();
            String user = super.getUser();
            String pwd = super.getPwd();

            try {


                Connection conn = DriverManager.getConnection(url, user, pwd);
                this.stm = conn.createStatement();

                //conn.close();

            }
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.planner = planner;
    }

    public Statement getStm() {
        return stm;
    }

    public AbstractUser getPlanner() {
        return planner;
    }
    
    
    public void loadMaintainersFromDatabase(){
         try {
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            if (maintainers != null){
                while (maintainers.next()){
                    this.getPlanner().createUser(maintainers.getString("Username"),"", UserFactory.Role.MAINTAINER);
                }
                ResultSet avails = stm.executeQuery("select * from availability");
                while (avails.next()){
                    this.getPlanner().getUser(avails.getString("Maintainer")).addSlotAvailability(Integer.valueOf(avails.getString("Week")), UserFactory.weekDay.valueOf(avails.getString("WeekDay")), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }


    public void saveMaintainersOnDatabase(LinkedList<User> maintainers) {
        for(User m : maintainers){
            if(m.getRole() == UserFactory.Role.MAINTAINER){
                try {
                    Connection conn = DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPwd());
                    stm.executeUpdate(m.getInsertQuery());
                    stm.getConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
