/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.systemAdmin;

import Activity.Activities;
import Activity.competence.Competence;
import Activity.competence.Competences;
import java.util.Map;
import java.util.Objects;
import users.AbstractUserFactory;
import users.User;
import users.maintainer.MaintainerFactory;
import users.planner.PlannerFactory;

/**
 *
 * @author Grazia D'Amore
 */
public class SystemAdmin implements User{
    
    //DESIGN PATTERN : SINGLETON
    private static SystemAdmin single_instance = null;
    private String username;
    private String password;
    private AbstractUserFactory.Role role;
    private SystemUsers users;
    private Activities activities;
    
    
    private SystemAdmin(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AbstractUserFactory.Role.SYSTEMADMIN;
        this.users = SystemUsers.SystemUsers();
        this.activities = new Activities();
    }
    
            
    public static SystemAdmin SystemAdministrator(String username, String password){  
        if (single_instance == null){ 
            single_instance = new SystemAdmin(username, password); 
        } 
        return single_instance; 
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

    public void setRole(AbstractUserFactory.Role role) {
        this.role = role;
    }

    /**
    * Returns the user created if correctly inserted in the System Users.
    * This method creates a User with the specified parameters. 
    * The user is inserted in the system users if the specified
    * username is not already present, it returns null otherwise.
    * 
    * @param  username  string that specify the user's name
    * @param  password  string that specify the user's password
    * @param  role      enum of Role that specify the user's role
    * @return      the AbstractUser created
    */
    @Override
    public User addUser(String username, String password, AbstractUserFactory.Role role){
        AbstractUserFactory factory = null;
        if (role == AbstractUserFactory.Role.PLANNER)
            factory = new PlannerFactory();
        else if (role == AbstractUserFactory.Role.MAINTAINER)
            factory = new MaintainerFactory();
        User user = factory.createUser(username, password);
       // JDBC jdbc = factory.createJDBCUser(username, password);
        try {
            return users.addUser(user);
        } catch (UsernameException ex) {
            return null;
        }
    }
 
    @Override
    public User removeUser(User u){
        return users.removeUser(u);
    }
    
    public SystemUsers getUsers(){
       return this.users;
    }
    
    @Override
    public User getUser(String username){
        return users.getUser(username);
    }
    
    public void setUsers(SystemUsers user){
        this.users = user;
    }
    
        @Override
    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into System_Admin(Username,Password_)");
        temp.append("values(");
        temp.append("'").append(this.getUsername()).append("',");
        temp.append("'").append(this.getPassword()).append("'");
        temp.append(");");
     return temp.toString();
    }
        
    public User changeUsername(User u, String username){
        User found = getUser(u.getUsername());
        User oldUser;
        if(u.getRole()==AbstractUserFactory.Role.MAINTAINER)
            oldUser= new MaintainerFactory().createUser(u.getUsername(), u.getPassword());
        else
            oldUser= new PlannerFactory().createUser(u.getUsername(), u.getPassword());
        
        if ( found != null){
            try {
                
                found.setUsername(username);
                users.addUser(found);
            } catch (UsernameException ex) {
                return null;
            } 
            users.removeUser(oldUser);
        }
        return found;
    }
    
    public User changePassword(User u, String password){
       return users.changePassword(u, password);
    }

    @Override
    public Competences getSkills() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSkills(Competences skills) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

/*
    @Override
    public void addSlotAvailability(int week, AbstractUserFactory.days day, int timeSlot, int avail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSlotAvailability(int week, AbstractUserFactory.days day, int slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSlotAvailability(int week, AbstractUserFactory.days day, int slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDayAvailability(int week, AbstractUserFactory.days day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

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
    public Activities getActivities() {
        return activities;
    }

    @Override
    public void setActivities(Activities activities) {
       this.activities = activities;
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
        final SystemAdmin other = (SystemAdmin) obj;
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

    @Override
    public String toString() {
        return  username + " " + password;
    }

    
}
