/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Activities;
import progettogruppo7.Activity;
import progettogruppo7.Competence;
import progettogruppo7.Material;
import progettogruppo7.Site;

/**
 *
 * @author Grazia D'Amore
 */
public class Planner_JDBC implements JDBC {
    private Statement stm;
    private String jdbc_user = "postgres";
    private String jdbc_password = "ciao98";
    private String username;
    private String password;
    
    public Planner_JDBC(String username, String password) {
       this.username = username; 
       this.password = password;
    }

    @Override
    public void createConnection() {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = jdbc_user; //postegres
        String pwd = jdbc_password; //ciao98

        
        try {
        Class.forName("org.postgresql.Driver");

        java.sql.Connection conn = DriverManager.getConnection(url, user, pwd);
        stm = conn.createStatement();

        }catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isTableEmpty() {
        createConnection();
        ResultSet result ;
 
        try {
            result = stm.executeQuery("SELECT count(*) FROM planner" );
            while(result.next()){
                if(Integer.valueOf(result.getString("count")) == 0)
                    return false;
                else
                    return true;
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User selectUser(String username) {
        createConnection();
        ResultSet result ;
        User found = null;
        
        try {
            result = stm.executeQuery("SELECT * FROM planner WHERE username = "+ "'"+ username + "'");
            while(result.next()){
                if(result.getString("Username").equals(username))
                    found = new MaintainerFactory().createUser(result.getString("Username"),result.getString("Password"));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }

    @Override
    public void loadUsersFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAdminOnDatabase(String username, String password,AbstractUserFactory.Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveUsersOnDatabase(LinkedList<User> users) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUserFromDatabase(String username, AbstractUserFactory.Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public  Map<String,Planner> loadPlannersFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String,Maintainer> loadMaintainersFromDatabase() {
        Map<String,Maintainer> found = new HashMap<>();
        try {
            createConnection(); 
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            
            while (maintainers.next()){
                User m = new MaintainerFactory().createUser(maintainers.getString("Username"),"");
                found.put(maintainers.getString("Username"), (Maintainer) m);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                found.get(avails.getString("Maintainer")).addSlotAvailability(Integer.valueOf(avails.getString("Week")), AbstractUserFactory.weekDay.valueOf(avails.getString("WeekDay")), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return found;
    }

    @Override
    public void savePlannersOnDatabase(LinkedList<User> planners) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveMaintainersOnDatabase(LinkedList<User> maintainers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadActivitiesFromDatabase() {
       Activities found = new Activities();
       try {
            createConnection(); 
            ResultSet activities = stm.executeQuery("SELECT * FROM planned_activities");

            while (activities.next()){
                int id = Integer.valueOf(activities.getString("ID_CODE"));
                int estimated = Integer.valueOf(activities.getString("ESTIMATED_TIME"));
                int week = Integer.valueOf(activities.getString("WEEK"));

                Activity activity = new Activity(id,new Site(activities.getString("FACTORY_SITE"),activities.getString("DEPARTMENT")),activities.getString("DESCRIPTION"),
                                    Activity.Type.valueOf(activities.getString("TYPE_").toUpperCase()),estimated,Boolean.valueOf(activities.getString("INTERRUPTIBLE")),week);   

                activity.setStatus(false);
                this.loadActivityCompetences(activity);
                this.loadActivityMaterials(activity);
                found.insertInActivities(activity); 
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void loadActivityCompetences(Activity ac) {
        try {
            createConnection();
            ResultSet competences = stm.executeQuery("SELECT competence FROM activity_competences WHERE activity = " + ac.getActivityID());
            while(competences.next()){
                ac.getCompetences().insertCompetence(new Competence(competences.getString("Competence")));
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @Override
    public void loadActivityMaterials(Activity ac) {
        try {
            createConnection();
            ResultSet materials = stm.executeQuery("SELECT material FROM activity_materials WHERE activity = " + ac.getActivityID());
            while(materials.next()){
                ac.getMaterials().insertMaterial(new Material(materials.getString("Material")));
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void removeActivityFromDatabase(Activity ac) {
       createConnection();
       StringBuilder temp = new StringBuilder();
       try {
            temp.append("DELETE FROM activity WHERE id_code = ").append("'").append(ac.getActivityID()).append("'");
            stm.executeQuery(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {}
    }

    @Override
    public void saveActivityOnDatabase(Activity ac) {
       createConnection();
       StringBuilder temp = new StringBuilder();
       try{
           temp.append("INSERT INTO activity(ID_CODE, WEEK,FACTORY_SITE,DEPARTMENT,STATUS,ESTIMATED_TIME,TYPE_,DESCRIPTION)\n" +
           "VALUES").append(ac.getActivityID()).append(ac.getWeek()).append("'").append(ac.getSite().getFactorySite()).append("'");
           stm.executeQuery(temp.toString());
           stm.getConnection().close();
       }catch (SQLException ex) {}
    }

    @Override
    public void updateAvailabiltyOnDatabase(Availability a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
