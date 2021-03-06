/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.maintainer;

import Activity.Activities;

import Activity.competence.Competence;
import Activity.competence.Competences;

import users.User;
import users.AbstractUserFactory;

import java.util.Map;
import java.util.Objects;


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
    private Activities activities;
    
    public Maintainer(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AbstractUserFactory.Role.MAINTAINER;
        this.availability = new Availability();
        this.skills = new Competences();
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
    public Activities getActivities() {
        return activities;
    }

    
    @Override
    public void setActivities(Activities activities) {
        this.activities = activities;
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
    
    
    
    public Availability getAvailability() {
        return availability;
    }

   
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    
    
    
    public void addSlotAvailability(int week, String day , int timeSlot ,int avail){
        availability.addAvailability(week, Availability.week.valueOf(day), timeSlot, avail);
    }
    
   
    public int getSlotAvailability(int week, String day, int slot){
        return availability.getSlotAvailability(week, Availability.week.valueOf(day), slot);
    }
    
    
    public int getDayAvailability(int week, String day){
        return availability.getDayAvailability(week,Availability.week.valueOf(day));
    }
    
    
    public void removeSlotAvailability(int week, String day, int slot){
        availability.removeSlotAvailability(week,Availability.week.valueOf(day), slot);
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
        
        temp.append(this.availability.getInsertQuery(username));
        temp.append(this.skills.getInsertQuery(username));
        
    return temp.toString();
    }
    
    @Override
    public String toString() {
        return username + " " + password;
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
    public Map<String, User> getMaintainers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaintainers(Map<String, User> maints) {
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
