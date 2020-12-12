/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.Map;
import progettogruppo7.Activities;
import progettogruppo7.Activity;
import progettogruppo7.Competence;
import progettogruppo7.Competences;

/**
 *
 * @author Rosanna
 */
public interface User {
    
    public String getUsername();
    
    public void setUsername(String username);
    
    public String getPassword();
    
    public void setPassword(String password);
    
    public AbstractUserFactory.Role getRole();
    
   // public void setRole(AbstractUserFactory.Role role);
    
    public String getInsertQuery();
                        
    
    // System Administrator methods //
   
    public User removeUser(User u);
    
    public User getUser(String username);
    
    public User addUser(String username, String password, AbstractUserFactory.Role role);
    
    public String printUsers();
    
    public void setUsers(SystemUsers users);
    
    public SystemUsers getUsers();
    
    // Maintainers methods //
    
    public Competences getSkills();

    public void setSkills(Competences skills);

    public Availability getAvailability();

    public void setAvailability(Availability availability);
    
    public void addSlotAvailability(int week,AbstractUserFactory.weekDay day, int timeSlot ,int avail);
    
    public int getSlotAvailability(int week,AbstractUserFactory.weekDay day, int slot);
    
    public void removeSlotAvailability(int week,AbstractUserFactory.weekDay day, int slot);
    
    public int getDayAvailability(int week, AbstractUserFactory.weekDay day);
            
    public void insertSkill(Competence c);
    
    public void removeSkill(Competence c);
    
    public boolean findSkill(String name);
     
    public int isQualified(Competences comp); 
    
    
    //Planner methods
    
    public Activities getActivities();

    public void setActivities(Activities activities);
    
    public Map<String,Maintainer> getMaintainers();
    
    public void setMaintainers(Map<String,Maintainer> maints);
 
}
