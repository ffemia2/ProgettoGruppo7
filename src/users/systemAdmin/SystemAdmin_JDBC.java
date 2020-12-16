/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.systemAdmin;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import users.JDBC;
import users.User;
import users.AbstractUserFactory;
import users.maintainer.*;
import users.planner.*;

import Activity.Activities;
import Activity.Activity;
import Activity.ActivityBuilder;
import Activity.Unplanned.UnplannedBuilder;
import Activity.Unplanned.ExtraBuilder;
import Activity.Planned.PlannedBuilder;
        

import Activity.competence.Competence;
import Activity.competence.Competences;
import Activity.material.Material;

/**
 *
 * @author Grazia D'Amore
 */
public class SystemAdmin_JDBC implements JDBC{
    Statement stm;
    private String jdbc_user = "postgres";
    private String jdbc_password = "polter01";
    private String username;
    private String password;
    
    public SystemAdmin_JDBC(String username, String password) {
       this.username = username; 
       this.password = password;
    }

    @Override
    public void createConnection() {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = jdbc_user; //postgres
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
            result = stm.executeQuery("SELECT count(*) FROM system_admin" );
            while(result.next()){
                int vir = Integer.valueOf(result.getString("count"));
                if(vir == 0)
                    return true;
                else
                    return false;
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
            result = stm.executeQuery("SELECT * FROM system_admin WHERE username = "+ "'"+ username + "'");
            while(result.next()){
                if(result.getString("Username").equals(username))
                    found = new SystemAdminFactory().createUser(result.getString("Username"),result.getString("Password_"));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }

    @Override
    public void loadUsersFromDatabase() {
        this.loadPlannersFromDatabase();
        this.loadMaintainersFromDatabase();
    }
    //TODO delete old admin and insert new one??
    
    @Override
    public void saveAdminOnDatabase(String username, String password,AbstractUserFactory.Role role) {
        StringBuilder temp =  new StringBuilder();
        temp.append("insert into System_Admin(Username,Password_)");
        temp.append("values(");
        temp.append("'").append(username).append("',");
        temp.append("'").append(password).append("'");
        temp.append(");");
        if(role.equals(AbstractUserFactory.Role.SYSTEMADMIN)){
            try {
                createConnection();
                stm.executeUpdate(temp.toString());
                stm.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void saveUsersOnDatabase(LinkedList<User> users) {
        this.savePlannersOnDatabase(users);
        this.saveMaintainersOnDatabase(users);
    }

    @Override
    public void removeUserFromDatabase(String username, AbstractUserFactory.Role role) {
        createConnection();
        StringBuilder temp = new StringBuilder();
        switch (role) {
            case SYSTEMADMIN:
                try {
                    temp.append("DELETE FROM system_admin WHERE username = ").append("'").append(username).append("'");
                    stm.executeQuery(temp.toString());
                    stm.getConnection().close();
                } catch (SQLException ex) {}
                break;
            case PLANNER:
                try {
                    temp.append("DELETE FROM planner WHERE username = ").append("'").append(username).append("'");
                    stm.executeQuery(temp.toString());
                    stm.getConnection().close();
                } catch (SQLException ex) {}
                break;
            case MAINTAINER:
                try {
                    temp.append("DELETE FROM maintainer WHERE username = ").append("'").append(username).append("'");
                    stm.executeQuery(temp.toString());
                    stm.getConnection().close();
                } catch (SQLException ex) {}
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String,User> loadPlannersFromDatabase() {
        Map<String,User> found = new HashMap<>();
         try {
            createConnection();
            ResultSet planners = stm.executeQuery("select * from planner");
            while (planners.next()){
                User p = new PlannerFactory().createUser(planners.getString("Username"),planners.getString("Password_"));
                found.put(planners.getString("Username"), (Planner) p);
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return found;
    }

    @Override
    public Map<String, User> loadMaintainersFromDatabase() {
        Map<String,User> found = new HashMap<>();
        try {
            createConnection(); 
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            
            while (maintainers.next()){
                User m = new MaintainerFactory().createUser(maintainers.getString("Username"),maintainers.getString("Password_"));
                found.put(maintainers.getString("Username"), (Maintainer) m);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                ((Maintainer)found.get(avails.getString("Maintainer"))).addSlotAvailability(Integer.valueOf(avails.getString("Week")), avails.getString("WeekDay"), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
            }
            
            ResultSet skills = stm.executeQuery("SELECT * FROM Maintainer_Competences");
            while (skills.next()){
                ((Maintainer)found.get(skills.getString("Maintainer"))).insertSkill(new Competence(skills.getString("Competence")));
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return found;    
    }

    @Override
    public void savePlannersOnDatabase(LinkedList<User> planners) {
        for(User m : planners){
            if (m.getRole() == AbstractUserFactory.Role.PLANNER){
                try {
                    createConnection();
                    stm.executeUpdate(m.getInsertQuery());
                    stm.getConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void saveMaintainersOnDatabase(LinkedList<User> maintainers) {
        for(User m : maintainers){
            if(m.getRole() == AbstractUserFactory.Role.MAINTAINER){
                try {
                    createConnection();
                    stm.executeUpdate(m.getInsertQuery());
                    stm.getConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Activities loadActivitiesFromDatabase() {
        Activities found = new Activities();
       
       try {
            createConnection(); 
            ResultSet activities = stm.executeQuery("SELECT * FROM activity");

            while (activities.next()){
                int id = Integer.valueOf(activities.getString("ID_CODE"));
                ActivityBuilder builder = this.selectActivity(activities.getString("TYPE_"));                
                Activity activity = builder.setSite(activities.getString("FACTORY_SITE"), activities.getString("DEPARTMENT"))
                                           .setEstimatedTime(Integer.valueOf(activities.getString("ESTIMATED_TIME")))
                                           .setAssigned(false)
                                           .setDescription(activities.getString("DESCRIPTION"))
                                           .setTypology(ActivityBuilder.Typology.valueOf(activities.getString("TYPOLOGY")))
                                           .setWeek(Integer.valueOf(activities.getString("WEEK")))
                                           .getActivity();
                activity.setActivityID(id);
                if(activities.getString("TYPE_")=="Planned")
                    activity.setInterruptible(Boolean.valueOf(activities.getString("INTERRUPTIBLE")));
                this.loadActivityCompetences(activity);
                this.loadActivityMaterials(activity);
                found.insertInActivities(activity); 
            }
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return found;
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
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

     private ActivityBuilder selectActivity(String type){
        if(type.equals("Planned"))
            return new PlannedBuilder();
        if(type.equals("Unplanned"))
            return new UnplannedBuilder();
        if(type.equals("Extra"))
            return new ExtraBuilder();
    
        return null;
    }
    
    @Override
    public void removeActivityFromDatabase(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveActivityOnDatabase(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int loadCountFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveActivitiesOnDatabase(Activities ac) {
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
                Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
         temp.append("INSERT INTO maintainer_competences (maintainer, competence) VALUES (").append("'").append(name).append("',").append("'").append(c.getName()).append("');");
        }
        try {
            
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateActivityOnDatabase(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
