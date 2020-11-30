/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Exceptions.UsernameException;

/**
 *
 * @author User
 */
public class SystemAdministrator extends AbstractAdmin{
    
    //DESIGN PATTERN : SINGLETON
    private static SystemAdministrator single_instance = null;
    private SystemUsers users;
    private UserFactory factory;

    private SystemAdministrator(String username, String password) {
        super(username, password,UserFactory.Role.SYSTEMADMIN);
        this.users = SystemUsers.SystemUsers();
        this.factory = new EmployeeFactory();
        //this.factory = new UserFactory();
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
    public LinkedList<AbstractUser> getUsers(){
        LinkedList<AbstractUser> list = new LinkedList();
        for (Maintainer m : users.getMaintainers().values())
            list.add(m);
        for (Planner p : users.getPlanners().values()){
            list.add(p);
        }
        return list;
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
    
}
