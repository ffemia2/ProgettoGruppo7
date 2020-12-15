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
import progettogruppo7.Activity;
import progettogruppo7.Users.Maintainer;
import progettogruppo7.Users.User;

/**
 *
 * @author Grazia D'Amore
 */
public class Maintainer_JDBC extends JDBC{
    private Statement stm;
    private User maintainer;

    public Maintainer_JDBC(User maintainer) {
    {
        String url = super.getUrl();
        String user = super.getUser();
        String pwd = super.getPwd();

        try {
            java.sql.Connection conn = null;
            Class.forName("org.postgresql.Driver");
             
            conn = DriverManager.getConnection(url, user, pwd);
            this.stm = conn.createStatement();
            
            //conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }  
        this.maintainer = maintainer;
    }

    
    public void updateAvailabiltyOnDatabase(Availability a) {
        StringBuilder temp = new StringBuilder();
        temp.append(a.getUpdateQuery(username)); 
        try {
            createConnection();
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateAvailabiltyOnDatabase(){ 
        StringBuilder temp = new StringBuilder();
        temp.append(maintainer.getAvailability().getUpdateQuery((Maintainer)maintainer)); 
        try {
            stm.executeUpdate(temp.toString());
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateActivitiesOnDatabase(Activity a){ 
        StringBuilder temp = new StringBuilder();
        temp.append(maintainer.getAvailability().getinsertActivityQuery((Maintainer)maintainer,a)); 
        try {
            stm.executeUpdate(temp.toString());
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LinkedList<Integer> selectActivitiesFromDatabase(){ 
        StringBuilder temp = new StringBuilder();
        temp.append(maintainer.getAvailability().getActivitiesQuery((Maintainer)maintainer)); 
        LinkedList<Integer> l=new LinkedList();
        try {
            ResultSet rts=stm.executeQuery(temp.toString());
            while(rts.next()){
                l.add(rts.getInt("activity"));
                //((Maintainer)maintainer).addInActivities(new Planned(rts.getInt("ID_CODE"),new Site(rts.getString("site_of"),rts.getString("site_de")),rts.getString("description"),rts.getInt("estimated_time"),rts.getBoolean("interruptible"), rts.getInt("week")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return l;
    }
    public int selectCountActivityIDFromDatabase(Activity a){ 
        StringBuilder temp = new StringBuilder();
        temp.append(maintainer.getAvailability().getCountACtivityIDQuery(a)); 
        int count=0;
        try {
            ResultSet rts=stm.executeQuery(temp.toString());
            if(rts.next())
                count=rts.getInt("Count");
                //((Maintainer)maintainer).addInActivities(new Planned(rts.getInt("ID_CODE"),new Site(rts.getString("site_of"),rts.getString("site_de")),rts.getString("description"),rts.getInt("estimated_time"),rts.getBoolean("interruptible"), rts.getInt("week")));
            
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
}
