/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Users.AbstractUser;
import progettogruppo7.Users.Availability;
import progettogruppo7.Users.Maintainer;
import progettogruppo7.Users.UserFactory;

/**
 *
 * @author Grazia D'Amore
 */
public class Maintainer_JDBC {
    private Statement stm;
    private AbstractUser maintainer;

    public Maintainer_JDBC(AbstractUser maintainer) {
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
        this.maintainer = maintainer;
    }

    
    public void saveAvailabiltyOnDatabase(){ 
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Availability(Maintainer,Week,WeekDay,TimeSlot,Avail)");
        temp.append("values(");
        temp.append("'").append(maintainer.getUsername()).append("',");
        temp.append(maintainer.getAvailability().getInsertQuery((Maintainer)maintainer)); 
        try {
            stm.executeUpdate(temp.toString());
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
