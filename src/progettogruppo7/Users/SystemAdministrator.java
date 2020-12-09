/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Activity;
import progettogruppo7.Competence;
import progettogruppo7.Competences;
import progettogruppo7.Exceptions.UsernameException;

/**
 *
 * @author User
 */
public class SystemAdministrator extends AbstractAdmin{
    
    //DESIGN PATTERN : SINGLETON
    private static SystemAdministrator single_instance = null;
    private SystemUsers users;
    private EmployeeFactory factory;

    private SystemAdministrator(String username, String password) {
        super(username, password,UserFactory.Role.SYSTEMADMIN);
        this.users = SystemUsers.SystemUsers();
        this.factory = new EmployeeFactory();
    }
    
            
    public static SystemAdministrator SystemAdministrator(String username, String password){  
        if (single_instance == null){ 
            single_instance = new SystemAdministrator(username, password); 
        } 
        return single_instance; 
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
    public AbstractUser createUser(String username, String password, UserFactory.Role role){
        AbstractUser user = factory.build(username, password, role);
        try {
            return users.addUser(user);
        } catch (UsernameException ex) {
            return null;
        }
    }
    
    
    @Override
    public AbstractUser removeUser(AbstractUser u){
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
    public AbstractUser getUser(String username){
        return users.getUser(username);
    }
    
    @Override
    public void setUsers(SystemUsers user){
        this.users = user;
    }
    
        @Override
    public String getInsertQuery() {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into SystemAdmin(Username,Password_)");
        temp.append("values(");
        temp.append("'").append(username).append("',");
        temp.append("'").append(password).append("'");
        temp.append(");");
     return temp.toString();
    }
        
    /*public AbstractUser changeUsername(AbstractUser u, String username){
        AbstractUser found = getUser(u);
        if ( found != null){
            found.setUsername(username);
        }
        return found;
    }
    
    public AbstractUser changePassword(AbstractUser u, String password){
       AbstractUser found = getUser(u);
        if ( found != null){
            found.setPassword(password);
        }
        return found;
    }*/

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
    public boolean findSkill(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int isQualified(Activity act) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
