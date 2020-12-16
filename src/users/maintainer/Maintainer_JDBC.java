/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.maintainer;

import Activity.Activities;
import Activity.Activity;

import Activity.competence.Competence;
import Activity.competence.Competences;

import users.AbstractUserFactory;
import users.JDBC;
import users.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.LinkedList;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import users.planner.Planner_JDBC;

/**
 *
 * @author Grazia D'Amore
 */
public class Maintainer_JDBC implements JDBC{
    private Statement stm;
    private String jdbc_user = "postgres";
    private String jdbc_password = "polter01"; 
    private String username;
    private String password;
    
    public Maintainer_JDBC(String username, String password) {
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
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
         temp.append("INSERT INTO activity_competences (activity, competence) VALUES (").append(id).append(",").append("'").append(c.getName()).append("');");
        }
        try {
            
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
         temp.append("INSERT INTO maintainer_competences (maintainer, competence) VALUES (").append("'").append(name).append("',").append("'").append(c.getName()).append("');");
        }
        try {            
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateMaintainerOnDatabase(User employee, String name){
        createConnection();
        StringBuilder temp = new StringBuilder();
        temp.append("UPDATE maintainer SET username = ").append("'").append(employee.getUsername()).append("'")
           .append(", password_ = ").append("'").append(employee.getPassword()).append("'")
           .append(" WHERE username = ").append("'").append(name).append("'");
        
        try{
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        }catch (SQLException ex) {
           Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean isTableEmpty() {
        createConnection();
        ResultSet result ;
 
        try {
            result = stm.executeQuery("SELECT count(*) FROM maintainer" );
            while(result.next()){
                if(Integer.valueOf(result.getString("count")) == 0)
                    return false;
                else
                    return true;
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User selectUser(String username) {
        createConnection();
        ResultSet result ;
        User found = null;
        
        try {
            result = stm.executeQuery("SELECT * FROM maintainer WHERE username = "+ "'"+ username + "'");
            while(result.next()){
                if(result.getString("Username").equals(username))
                    found = new MaintainerFactory().createUser(result.getString("Username"),result.getString("Password"));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }
    
    public int selectCountActivityIDFromDatabase(Activity a){
        createConnection();
        ResultSet result ;
        int count = 0;
        
        try {
            result = stm.executeQuery("select count(maintainer) as Count from MAINTAINER_ACTIVITIES where activity=" + "'" + a.getActivityID() + "';");
        
            while(result.next()){
                count = Integer.valueOf(result.getString("Count"));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        return count;
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
    public Map<String,User> loadPlannersFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String,User> loadMaintainersFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Activities loadActivitiesFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadActivityCompetences(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadActivityMaterials(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeActivityFromDatabase(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveActivityOnDatabase(Activity ac) {
       createConnection();
       StringBuilder temp = new StringBuilder();
       try{
            temp.append("insert into MAINTAINER_ACTIVITIES (maintainer, activity) values(")
                .append("'").append(username).append("'").append(",")
                .append(ac.getActivityID()).append("),");
            
            if (temp.length() > 0){
                temp.deleteCharAt(temp.length()-1);
                temp.append(";");
            }
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        }catch (SQLException ex) {
           Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int loadCountFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveActivitiesOnDatabase(Activities acts) {
       createConnection();
       StringBuilder temp = new StringBuilder();
       try{
            for(Activity ac : acts.getActivities().values()) {
                temp.append("insert into MAINTAINER_ACTIVITIES (maintainer, activity) values(")
                        .append("'").append(username).append("'").append(",")
                        .append(ac.getActivityID()).append("),");
            }
            if (temp.length() > 0){
                temp.deleteCharAt(temp.length()-1);
                temp.append(";");
            }
            stm.executeUpdate(temp.toString());
            stm.getConnection().close();
        }catch (SQLException ex) {
           Logger.getLogger(Maintainer_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateActivityOnDatabase(Activity ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
