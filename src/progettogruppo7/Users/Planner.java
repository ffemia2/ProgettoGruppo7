/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import progettogruppo7.Activities;
import progettogruppo7.Competence;
import progettogruppo7.Competences;

/**
 *
 * @author Grazia D'Amore
 */
public class Planner implements User{
    private String username;
    private String password;
    private AbstractUserFactory.Role role;
    private Map<String,Maintainer> maintainers;
    private Activities activities;
  
    public Planner(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AbstractUserFactory.Role.PLANNER;
        this.maintainers = new HashMap<>();
        this.activities = new Activities();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public AbstractUserFactory.Role getRole() {
        return role;
    }  
    
    @Override
    public Activities getActivities() {
        return activities;
    }

    @Override
    public void setActivities(Activities activities) {
        this.activities = activities;
    }
    
    @Override
    public Map<String,Maintainer> getMaintainers(){
        return maintainers;
    }
    
    @Override
    public void setMaintainers(Map<String,Maintainer> maints){
        this.maintainers = maints;
    }
    
        @Override
    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Planner(Username,Password_)");
        temp.append("values(");
        temp.append("'").append(this.getUsername()).append("',");
        temp.append("'").append(this.getPassword()).append("'");
        temp.append(");");
        
        return temp.toString();
    }
    
    @Override
    public String toString() {
        return username + " " +password;
    }
    
    @Override
    public User removeUser(User u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public User addUser(String username, String password, AbstractUserFactory.Role role) {
        if(role == AbstractUserFactory.Role.MAINTAINER){
            password = "";
            User m = new MaintainerFactory().createUser(username,password);
            maintainers.put(username,(Maintainer) m);
            return m;
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        return maintainers.get(username);
    }

    @Override
    public String printUsers() {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public SystemUsers getUsers() {
        throw new UnsupportedOperationException("Not supported for this role."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public void setUsers(SystemUsers users) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Competences getSkills() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSkills(Competences skills) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Availability getAvailability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAvailability(Availability availability) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSlotAvailability(int week, AbstractUserFactory.weekDay day, int timeSlot, int avail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSlotAvailability(int week, AbstractUserFactory.weekDay day, int slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSlotAvailability(int week, AbstractUserFactory.weekDay day, int slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDayAvailability(int week, AbstractUserFactory.weekDay day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertSkill(Competence c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSkill(Competence c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean findSkill(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int isQualified(Competences comp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planner other = (Planner) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        return true;
    }

  
   
}
