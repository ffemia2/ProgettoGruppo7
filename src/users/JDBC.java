/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import Activity.Activities;
import Activity.Activity;
import Activity.competence.Competences;
import java.util.LinkedList;
import java.util.Map;



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
    
    public Map<String,User> loadPlannersFromDatabase();
    
    public Map<String,User> loadMaintainersFromDatabase();
    
    public void savePlannersOnDatabase(LinkedList<User> planners);
    
    public void saveMaintainersOnDatabase(LinkedList<User> maintainers);    
    
    public Activities loadActivitiesFromDatabase();
    
    public void loadActivityCompetences(Activity ac);
    
    public void loadActivityMaterials(Activity ac);
    
    public void saveActivityOnDatabase(Activity ac);
    
    public void saveActivitiesOnDatabase(Activities ac);
    
    public void updateActivityOnDatabase(Activity ac);
    
    public void removeActivityFromDatabase(Activity ac);

    public int loadCountFromDatabase();
    
     public void updateSkillsOnDatabase(Competences com, String name);
     
     public void updateCompetencesOnDatabase(Competences com, String id);
    
    //public void updateAvailabiltyOnDatabase(Availability a);
}
