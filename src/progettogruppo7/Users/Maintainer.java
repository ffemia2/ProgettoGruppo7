/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import progettogruppo7.Activities;
import progettogruppo7.Activity;

import progettogruppo7.Competence;
import progettogruppo7.Competences;

/**
 *
 * @author Rosanna
 */
public class Maintainer extends AbstractEmployee {
    
    private Availability availability ;
    private Competences skills;
    private Activities activities;

    
    public Maintainer(String username, String password) {
        super(username, password, UserFactory.Role.MAINTAINER);
        this.availability = new Availability();
        this.skills = new Competences();
        this.activities=new Activities();

    }

    public Activities getActivities() {
        return activities;
    }



    public void addInActivities(Activity a) {
        activities.insertInActivities(a);
    }
    
    public Competences getSkills() {
        return skills;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }
    

    public void setSkills(Competences skills) {
        this.skills = skills;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    
    public void addSlotAvailability(int week,UserFactory.weekDay day, int timeSlot ,int avail){
        availability.addAvailability(week, day, timeSlot, avail);
    }
    
    public int getSlotAvailability(int week,UserFactory.weekDay day, int slot){
        return availability.getSlotAvailability(week, day, slot);
    }
    
    public int getDayAvailability(int week, UserFactory.weekDay day){
        return availability.getDayAvailability(week, day);
    }
    
    public void removeSlotAvailability(int week,UserFactory.weekDay day, int slot){
        availability.removeSlotAvailability(week, day, slot);
    }
        
    public void insertSkill(Competence c){
        skills.insertCompetence(c);
    }
    
    public void removeSkill(Competence c){
        skills.removeCompetence(c);
    }
    
    public boolean findSkill(String s){
        return skills.verifyCompetence(s);
    }
     
    public int isQualified(Activity act){
        int count = 0;
        for (Competence c : act.getCompetences()){
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
        temp.append("'").append(username).append("',");
        temp.append("'").append(password).append("'");
        temp.append(");");
        
        temp.append(this.availability.getInsertQuery(this));
    return temp.toString();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public AbstractUser removeUser(AbstractUser u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public AbstractUser getUser(String u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }  

    @Override
    public AbstractUser createUser(String username, String password, UserFactory.Role role) {
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
    
    
    
}
