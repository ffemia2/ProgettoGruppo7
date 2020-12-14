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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francesco Femia
 */
public class ActivityTypeJDBC extends JDBC{
    private int activityID;
    private String type;
    private Statement stm;
    
    public ActivityTypeJDBC(int activityID, String type) {
        this.activityID = activityID;
        this.type = type;
    }

    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into activity_typologies(activity, typology)");
        temp.append("values(");
        temp.append("'").append(activityID).append("',");
        temp.append("'").append(type).append("')");
        return temp.toString();
    }

    public String load(int id) {
        {
            try {
                Connection conn = DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPwd());
                Statement stm= conn.createStatement();
                String query= "select typology from ACTIVITY_TYPOLOGIES where Activity="+String.valueOf(id);
                ResultSet rst=stm.executeQuery(query);
                if(rst.next())
                    return rst.getString("Typology");
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ActivityTypeJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return null;
    }


    public void save() {
        {
            try {
                Connection conn = DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPwd());
                Statement stm= conn.createStatement();
                String query= this.getInsertQuery();
                stm.executeUpdate(query);
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ActivityTypeJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
