/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Activity;
import progettogruppo7.Procedure;
import progettogruppo7.Procedures;
import progettogruppo7.Users.AbstractUser;
import progettogruppo7.Users.Maintainer;

/**
 *
 * @author Grazia D'Amore
 */

@SuppressWarnings("unchecked")
public class Maintainer_JDBC extends JDBC{
    private Statement stm;
    private AbstractUser maintainer;
    private Procedures procedures;

    public Maintainer_JDBC(AbstractUser maintainer) {
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
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }  
        this.maintainer = maintainer;
        this.procedures = new Procedures();
    }

    
    public void saveAvailabiltyOnDatabase(){ 
        StringBuilder temp = new StringBuilder();
        temp.append(maintainer.getAvailability().getInsertQuery((Maintainer)maintainer)); 
        try {
            stm.executeUpdate(temp.toString());
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void loadProceduresFromDatabase(Maintainer maint) throws IOException { 
        try {
            ResultSet rs = stm.executeQuery("select * from ACTIVITY_PROCEDURES");
            while (rs.next()){
                String description = rs.getString("Procedure_");
                Procedure proc = new Procedure(description);
                this.procedures.insertInProcedureMap(proc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //in activity -> serve a selectProceduresByMaintFromDatabase()
    public LinkedList<Integer> selectActivitiesByMaintFromDatabase() throws IOException{ 
        LinkedList<Integer> activityIDs = new LinkedList<>();
        StringBuilder temp = new StringBuilder();
        temp.append("select activity from MAINTAINER_ACTIVITIES where maintainer=").append("'").append(this.maintainer.getUsername()).append("'").append(";");
        try {
            ResultSet rs=stm.executeQuery(temp.toString());
            while(rs.next()){
                Integer id = rs.getInt("Activity");
                activityIDs.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activityIDs;
    }
    
    public Procedures selectProceduresByMaintFromDatabase() throws IOException{
        StringBuilder temp = new StringBuilder();
        LinkedList<Integer> ids = this.selectActivitiesByMaintFromDatabase();
        Procedures procedures2 = new Procedures();
        for (int id : ids) {
            String sql = "select procedure_ from ACTIVITY_PROCEDURES where activity='" + id + "'";
            try {
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next()){
                    String description = rs.getString("Procedure_");
                    Procedure proc = new Procedure(description);
                    procedures2.insertInProcedureMap(proc);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return procedures2;
    }
    
    //in activity
    public Procedures selectProceduresByActivFromDatabase(Activity a) throws IOException{ 
        StringBuilder temp = new StringBuilder();
        temp.append("select procedure_ from ACTIVITY_PROCEDURES where activity=").append("'").append(a.getActivityID()).append("'").append(";");
        Procedures procedures2 = new Procedures();
        try {
            ResultSet rs=stm.executeQuery(temp.toString());
            while(rs.next()){
                String description = rs.getString("Procedure_");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return procedures2;
    }
    
    //per saveProceduresOnDatabase, se non esiste Procedure nella tabella Procedures_ sul DB
//    public void InsertInProcedureOnDatabase(Procedure proc) {
//        try {
//            String sql = "select * from procedure_ where description='" + proc.getDescription() + "'";
//            ResultSet rs = stm.executeQuery(sql);
//            if (rs.next());
//            else {
//                stm.executeUpdate("insert into procedure_ " + "values('" + proc.getDescription() + "')");
//            } 
//        } catch (SQLException ex) {
//            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void saveProceduresOnDatabase(Activity a) throws IOException{  
//        for (Map.Entry<String, Procedure> entry : this.procedures.getProcedureMap().entrySet()){
//            try {
//                String sql = "select procedure_ from ACTIVITY_PROCEDURES where activity='" + a.getActivityID() + "'";
//                ResultSet rs = stm.executeQuery(sql);
//                if (rs.next());
//                else {
//                    Procedure proc = new Procedure(entry.getValue().getDescription());
//                    InsertInProcedureOnDatabase(proc);
//                    stm.executeUpdate("insert into activity_procedures " + "values('" + entry.getValue().getDescription() + "')");
//                } 
//            } catch (SQLException ex) {
//                Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
}
