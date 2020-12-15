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
import progettogruppo7.Exceptions.UsernameException;

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

    private SystemAdmin(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AbstractUserFactory.Role.SYSTEMADMIN;
        this.users = SystemUsers.SystemUsers();
    }
    
            
    public static SystemAdmin SystemAdministrator(String username, String password){  
        if (single_instance == null){ 
            single_instance = new SystemAdmin(username, password); 
        } 
        return single_instance; 
    } 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    
    @Override
    public SystemUsers getUsers(){
       return this.users;
    }
    
    @Override
    public String printUsers(){
        String str = "--- MAINTAINERS --" + "\n";
        
        for (Maintainer m : users.getMaintainers().values())
            str += m.toString() + "\n";
        str += "--- PLANNERS --" + "\n";
        for (Planner p : users.getPlanners().values()){
            str += p.toString() + "\n";
        }
        return str;
    }
    
    @Override
    public User getUser(String username){
        return users.getUser(username);
    }
    
    @Override
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
        if ( found != null){
            found.setUsername(username);
        }
        return found;
    }
    
    public User changePassword(User u, String password){
       User found = getUser(u.getUsername());
        if ( found != null){
            found.setPassword(password);
        }
        return found;
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
