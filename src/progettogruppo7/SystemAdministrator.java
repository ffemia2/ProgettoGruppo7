/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class SystemAdministrator extends AbstractUser{
    
    //DESIGN PATTERN : SINGLETON
    private static SystemAdministrator single_instance = null;
    private SystemUsers users;
    private UserFactory factory;

    private SystemAdministrator(String username, String password) {
        super(username, password,User.Role.SYSTEMADMIN);
        this.users = SystemUsers.SystemUsers();
        this.factory = new UserFactory();
    }
    
            
    public static SystemAdministrator SystemAdministrator(String username, String password){  
        if (single_instance == null){ 
            single_instance = new SystemAdministrator(username, password); 
        } 
        return single_instance; 
    } 
    
    @Override
    public AbstractUser createUser(String username, String password, User.Role role){
        AbstractUser user = factory.build(username, password, role);
        return addUser(user);

    }
    
    private AbstractUser addUser(AbstractUser u){
            return users.addUser(u);   
    }
    
    @Override
    public AbstractUser removeUser(AbstractUser u){
        return users.removeUser(u);
    }
    
    @Override
    public String getUsers(){
        String str = "--- MAINTAINERS --" + "\n";
        for (Maintainer m : users.getMaintainers().values())
            str += m.toString() + "\n";
        str += "--- PLANNERS --" + "\n";
         for (Planner p : users.getPlanners().values())
            str += p.toString() + "\n";
         return str;
    }
    
    @Override
    public AbstractUser getUser(AbstractUser u){
        return users.getUser(u);
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
