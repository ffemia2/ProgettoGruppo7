/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.Map;
import java.util.Objects;
import progettogruppo7.Activities;
import progettogruppo7.Competence;
import progettogruppo7.Competences;

/**
 *
 * @author Rosanna
 */
public class Maintainer implements User {
    private String username;
    private String password;
    private AbstractUserFactory.Role role;
    private Availability availability ;
    private Competences skills;
    // Activities
    
    public Maintainer(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AbstractUserFactory.Role.MAINTAINER;
        this.availability = new Availability();
        this.skills = new Competences();
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
    public Competences getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Competences skills) {
        this.skills = skills;
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    
    @Override
    public void addSlotAvailability(int week,AbstractUserFactory.weekDay day, int timeSlot ,int avail){
        availability.addAvailability(week, day, timeSlot, avail);
    }
    
    @Override
    public int getSlotAvailability(int week,AbstractUserFactory.weekDay day, int slot){
        return availability.getSlotAvailability(week, day, slot);
    }
    
    @Override
    public int getDayAvailability(int week, AbstractUserFactory.weekDay day){
        return availability.getDayAvailability(week, day);
    }
    
    @Override
    public void removeSlotAvailability(int week,AbstractUserFactory.weekDay day, int slot){
        availability.removeSlotAvailability(week, day, slot);
    }
        
    @Override
    public void insertSkill(Competence c){
        skills.insertCompetence(c);
    }
    
    @Override
    public void removeSkill(Competence c){
        skills.removeCompetence(c);
    }
    
    @Override
    public boolean findSkill(String name){
        return skills.verifyCompetence(name);
    }
     
    @Override
    public int isQualified(Competences comp){
        int count = 0;
        for (Competence c : comp){
            if (this.findSkill(c.getName()))
                count += 1;
        }
        return count;
    } 
    
    @Override
    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Maintainer(Username,Password_)");
        temp.append("values(");
        temp.append("'").append(this.getUsername()).append("',");
        temp.append("'").append(this.getPassword()).append("'");
        temp.append(");");
        
        temp.append(this.availability.getInsertQuery(this.getUsername()));
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
    public User getUser(String u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }  

    @Override
    public User addUser(String username, String password, AbstractUserFactory.Role role) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public String printUsers() {
        throw new UnsupportedOperationException("Not supported for this role."); //To change body of generated methods, choose Tools | Templates.
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
    public Activities getActivities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setActivities(Activities activities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Maintainer> getMaintainers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaintainers(Map<String, Maintainer> maints) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Maintainer other = (Maintainer) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return this.role == other.role;
    }  
    
}
