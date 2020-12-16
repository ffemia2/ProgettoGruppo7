/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.planner;

import Activity.Activities;
import Activity.Activity;
import Activity.ActivityBuilder;

import Activity.Planned.PlannedBuilder;

import Activity.Unplanned.UnplannedBuilder;
import Activity.Unplanned.ExtraBuilder;

import Activity.competence.Competence;
import Activity.competence.Competences;

import Activity.material.Material;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import users.AbstractUserFactory;
import users.JDBC;
import users.User;
import users.maintainer.Maintainer;
import users.maintainer.MaintainerFactory;

/**
 *
 * @author Grazia D'Amore
 */
public class Planner_JDBC implements JDBC {
    private Statement stm;
    private String jdbc_user = "postgres";
    private String jdbc_password = "polter01";
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
                return Integer.valueOf(result.getString("count")) != 0;
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
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
                    found = new PlannerFactory().createUser(result.getString("Username"),result.getString("Password_"));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }

    @Override
    public Map<String,User> loadMaintainersFromDatabase() {
        Map<String,User> found = new HashMap<>();
        try {
            createConnection(); 
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            
            while (maintainers.next()){
                User m = new MaintainerFactory().createUser(maintainers.getString("Username"),"");
                found.put(maintainers.getString("Username"), (Maintainer) m);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                ((Maintainer)found.get(avails.getString("Maintainer"))).addSlotAvailability(Integer.valueOf(avails.getString("Week")),avails.getString("WeekDay"), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
            }
            
            ResultSet skills = stm.executeQuery("SELECT * FROM Maintainer_Competences");
            while (skills.next()){
                ((Maintainer)found.get(skills.getString("Maintainer"))).insertSkill(new Competence(skills.getString("Competence")));
            }
            
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return found;
    }

    @Override
    public Activities loadActivitiesFromDatabase() {
       Activities found = new Activities();
       
       try {
            createConnection(); 
            ResultSet activities = stm.executeQuery("SELECT * FROM planner_activities");

            while (activities.next()){
                int id = Integer.valueOf(activities.getString("ID_CODE"));
                int estimated = Integer.valueOf(activities.getString("ESTIMATED_TIME"));
                int week = Integer.valueOf(activities.getString("WEEK"));
                ActivityBuilder builder = this.selectActivity(activities.getString("TYPE_"));
                
                Activity activity = builder.setSite(activities.getString("FACTORY_SITE"), activities.getString("DEPARTMENT"))
                                           .setEstimatedTime(estimated)
                                           .setAssigned(false)
                                           .setDescription(activities.getString("DESCRIPTION"))
                                           .setInterruptible(Boolean.valueOf(activities.getString("INTERRUPTIBLE")))
                                           .setTypology(ActivityBuilder.Typology.valueOf(activities.getString("TYPOLOGY")))
                                           .setWeek(week)
                                           .getActivity();
                activity.setActivityID(id);
                this.loadActivityCompetences(activity);
                this.loadActivityMaterials(activity);
                found.insertInActivities(activity); 
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return found;
    }
    
    private ActivityBuilder selectActivity(String type){
        if(type.equals("Planned"))
            return new PlannedBuilder();
        if(type.equals("Unplanned"))
            return new UnplannedBuilder();
        if(type.equals("Planned"))
            return new ExtraBuilder();
    
    return null;
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
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveActivityOnDatabase(Activity ac) {
       createConnection();
       StringBuilder temp = new StringBuilder();
       try{
           temp.append("INSERT INTO activity(ID_CODE, WEEK, FACTORY_SITE, DEPARTMENT, TYPOLOGY, STATUS, ESTIMATED_TIME, TYPE_, DESCRIPTION, INTERRUPTIBLE)\n" +
                "VALUES")
                        .append(ac.getActivityID()).append(",")
                        .append(ac.getWeek()).append(",")
                        .append("'").append(ac.getSite().getFactorySite()).append("'").append(",")
                        .append("'").append(ac.getSite().getDepartment()).append("'").append(",")
                        .append("'").append(ac.getTypology()).append("'").append(",")
                        .append(ac.isAssigned()).append(",")
                        .append(ac.getEstimatedTime()).append(",")
                        .append("'").append(ac.getClass().getSimpleName()).append("'").append(",")
                        .append("'").append(ac.getDescription()).append("'").append(",")
                        .append(ac.isInterruptible()).append("; ");
                   
           stm.executeUpdate(temp.toString());
           stm.getConnection().close();
       }catch (SQLException ex) {
           Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @Override
    public void updateActivityOnDatabase(Activity ac){
        createConnection();
        StringBuilder temp = new StringBuilder();
        temp.append("UPDATE activity SET week = ").append(ac.getWeek())
           .append(", factory_site = ").append("'").append(ac.getSite().getFactorySite()).append("'")
           .append(", department = ").append("'").append(ac.getSite().getDepartment()).append("'")
           .append(", description = ").append("'").append(ac.getDescription()).append("'")
           .append(" WHERE id_code = ").append(ac.getActivityID());
        
        try{
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        }catch (SQLException ex) {
           Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStatusOnDatabase(Activity ac, boolean assigned){
        createConnection();
        StringBuilder temp = new StringBuilder(); 
        temp.append("UPDATE Activity SET status = ").append(assigned).append(" WHERE id_code = ").append(ac.getActivityID());
        try{
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        }catch (SQLException ex) {
           Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void saveActivitiesOnDatabase(Activities acts) {
       createConnection();
       StringBuilder temp = new StringBuilder();
       try{
           temp.append("INSERT INTO activity(ID_CODE, WEEK, FACTORY_SITE, DEPARTMENT, TYPOLOGY ,STATUS, ESTIMATED_TIME, TYPE_, DESCRIPTION, INTERRUPTIBLE)\n" +
                "VALUES ");
            for(Activity ac : acts.getActivities().values()) {
                temp.append(" (").append(ac.getActivityID()).append(",")
                .append(ac.getWeek()).append(",")
                .append("'").append(ac.getSite().getFactorySite()).append("'").append(",")
                .append("'").append(ac.getSite().getDepartment()).append("'").append(",")
                .append("'").append(ac.getTypology()).append("'").append(",")
                .append(ac.isAssigned()).append(",")
                .append(ac.getEstimatedTime()).append(",")
                .append("'").append(ac.getClass().getSimpleName()).append("'").append(",")
                .append("'").append(ac.getDescription()).append("'").append(",")
                .append(ac.isInterruptible()).append("),");
            }
            if (acts.getActivities().size() > 0){
                temp.deleteCharAt(temp.length()-1);
                temp.append(";");
                stm.executeUpdate(temp.toString());
            }
            
            stm.getConnection().close();
        }catch (SQLException ex) {
           Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    @Override
    public int loadCountFromDatabase(){
        createConnection();
        int count = 0;
        StringBuilder temp = new StringBuilder();
        try{
            ResultSet result = stm.executeQuery("SELECT ID_CODE FROM activity\n" + "ORDER BY id_code DESC \n" + "LIMIT 1");
            while(result.next()){
                count = Integer.valueOf(result.getString("ID_CODE"));
            }
            stm.getConnection().close();
        }catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
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
    public  Map<String,User> loadPlannersFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void updateCompetencesOnDatabase(Competences com, String id) {
        createConnection();
        StringBuilder temp = new StringBuilder();
        for (Competence c : com){
         try {
                ResultSet r = stm.executeQuery("select count(nome) as Count from COMPETENCE where nome = "+"'"+c.getName()+"'");
                while (r.next()){
                    if (r.getInt("Count") == 0){
                        temp.append("INSERT INTO competence (nome) values (").append("'").append(c.getName()).append("');");
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
         temp.append("INSERT INTO activity_competences (activity, competence) VALUES (").append(id).append(",").append("'").append(c.getName()).append("');");
        }
        try {
            
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void updateSkillsOnDatabase(Competences com, String name) {
        createConnection();
        StringBuilder temp = new StringBuilder();
        for (Competence c : com){
         try {
                ResultSet r = stm.executeQuery("select count(nome) as Count from COMPETENCE where nome = "+"'"+c.getName()+"'");
                while (r.next()){
                    if (r.getInt("Count") == 0){
                        temp.append("INSERT INTO competence (nome) values (").append("'").append(c.getName()).append("');");
                    }
                }
             
            } catch (SQLException ex) {
                Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
         temp.append("INSERT INTO maintainer_competences (maintainer, competence) VALUES (").append("'").append(name).append("',").append("'").append(c.getName()).append("');");
        }
        try {
            
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Planner_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
