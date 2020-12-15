/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

//import JDBC.Message;

import java.sql.Statement;
import java.util.LinkedList;
import java.util.Map;
import progettogruppo7.Activity;


/**
 *
 * @author Grazia D'Amore
 */
public interface JDBC {
    
    public void createConnection();
    
    public boolean isTableEmpty();
        
    public User selectUser(String username);
    
    public void loadUsersFromDatabase();
    
    public void saveAdminOnDatabase(String username, String password,AbstractUserFactory.Role role);
    
    public void saveUsersOnDatabase(LinkedList<User> users);
    
    public void removeUserFromDatabase(String username, AbstractUserFactory.Role role);
    
    public Map<String,Planner> loadPlannersFromDatabase();
    
    public Map<String,Maintainer> loadMaintainersFromDatabase();
    
    public void savePlannersOnDatabase(LinkedList<User> planners);
    
    public void saveMaintainersOnDatabase(LinkedList<User> maintainers);    
    
    public void loadActivitiesFromDatabase();
    
    public void loadActivityCompetences(Activity ac);
    
    public void loadActivityMaterials(Activity ac);
    
    public void removeActivityFromDatabase(Activity ac);
   
    public void saveActivityOnDatabase(Activity ac);
    
    public void updateAvailabiltyOnDatabase(Availability a);
}
