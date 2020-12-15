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
import progettogruppo7.Activity;

/**
 *
 * @author Grazia D'Amore
 */
public class SystemAdmin_JDBC implements JDBC{
    Statement stm;
    private String jdbc_user = "postgres";
    private String jdbc_password = "ciao98";
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
            result = stm.executeQuery("SELECT * FROM system_admin WHERE username = "+ "'"+ username + "'");
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
    public Map<String,Planner> loadPlannersFromDatabase() {
        Map<String,Planner> found = new HashMap<>();
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
    public Map<String, Maintainer> loadMaintainersFromDatabase() {
        Map<String,Maintainer> found = new HashMap<>();
        try {
            createConnection(); 
            ResultSet maintainers = stm.executeQuery("select * from maintainer");
            
            while (maintainers.next()){
                User m = new MaintainerFactory().createUser(maintainers.getString("Username"),maintainers.getString("Password_"));
                found.put(maintainers.getString("Username"), (Maintainer) m);
            }
            ResultSet avails = stm.executeQuery("select * from availability");
            while (avails.next()){
                found.get(avails.getString("Maintainer")).addSlotAvailability(Integer.valueOf(avails.getString("Week")), AbstractUserFactory.weekDay.valueOf(avails.getString("WeekDay")), Integer.valueOf(avails.getString("TimeSlot")), Integer.valueOf(avails.getString("Avail")));
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

    @Override
    public void updateAvailabiltyOnDatabase(Availability a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
