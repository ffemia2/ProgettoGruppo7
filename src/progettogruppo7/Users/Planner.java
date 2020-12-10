/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import JDBC.Planner_JDBC;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Activity;
import progettogruppo7.Competence;
import progettogruppo7.Competences;

/**
 *
 * @author Grazia D'Amore
 */
public class Planner extends AbstractEmployee implements Serializable{
    private  Map<String,Maintainer> maintainers = new HashMap<>();
    
    public Planner(String username, String password) {
        
        super(username, password, UserFactory.Role.PLANNER);
        new Planner_JDBC(this).loadMaintainersFromDatabase();
    }
    
    
    public Map<String,Maintainer> getMaintainers(){
        return maintainers;
    }
    
    public void setMaintainers(Map<String,Maintainer> maints){
        this.maintainers = maints;
    }
    
        @Override
    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Planner(Username,Password_)");
        temp.append("values(");
        temp.append("'").append(username).append("',");
        temp.append("'").append(password).append("'");
        temp.append(");");
        
        return temp.toString();
    }
    
    @Override
    public AbstractUser removeUser(AbstractUser u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

   
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public AbstractUser createUser(String username, String password, UserFactory.Role role) {
        if(role == UserFactory.Role.MAINTAINER){
            password = "";
            Maintainer m = new Maintainer(username,password);
            maintainers.put(username,m);
            return m;
        }
        return null;
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
    public AbstractUser getUser(String username) {
        return maintainers.get(username);
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
    public void addSlotAvailability(int week, UserFactory.weekDay day, int timeSlot, int avail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSlotAvailability(int week, UserFactory.weekDay day, int slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSlotAvailability(int week, UserFactory.weekDay day, int slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDayAvailability(int week, UserFactory.weekDay day) {
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
    public int isQualified(Activity act) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
   
}
