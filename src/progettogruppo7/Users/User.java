/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

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
    
    public UserFactory.Role getRole();
    
    public void setRole(UserFactory.Role role);
    
    public String getInsertQuery();
                        
    
    // System Administrator methods //
   
    public AbstractUser removeUser(AbstractUser u);
    
    public AbstractUser getUser(String username);
    
    public AbstractUser createUser(String username, String password, UserFactory.Role role);
    
    public String printUsers();
    
    public void setUsers(SystemUsers users);
    
    public SystemUsers getUsers();
    
    // Maintainers methods //
    
    public Competences getSkills();

    public void setSkills(Competences skills);

    public Availability getAvailability();

    public void setAvailability(Availability availability);
    
    public void addSlotAvailability(int week,UserFactory.weekDay day, int timeSlot ,int avail);
    
    public int getSlotAvailability(int week,UserFactory.weekDay day, int slot);
    
    public void removeSlotAvailability(int week,UserFactory.weekDay day, int slot);
    
    public int getDayAvailability(int week, UserFactory.weekDay day);
            
    public void insertSkill(Competence c);
    
    public void removeSkill(Competence c);
    
    public boolean findSkill(String name);
     
    public int isQualified(Activity act); 
}
