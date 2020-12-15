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
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Activity;

/**
 *
 * @author Grazia D'Amore
 */
public class Maintainer_JDBC implements JDBC{
    private Statement stm;
    private String jdbc_user = "postgres";
    private String jdbc_password = "ciao98"; 
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

    @Override
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
            result = stm.executeQuery("SELECT * FROM maintainer WHERE username = "+ "'"+ username + "'");
            while(result.next()){
                if(result.getString("Username").equals(username))
                    found = new MaintainerFactory().createUser(result.getString("Username"),result.getString("Password"));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(SystemAdmin_JDBC.class.getName()).log(Level.SEVERE, null, ex);
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
    public Map<String,Planner> loadPlannersFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String,Maintainer> loadMaintainersFromDatabase() {
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
    public void loadActivitiesFromDatabase() {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
