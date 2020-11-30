/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;


/**
 *
 * @author Rosanna
 */
public abstract class UserFactory {
      
    public enum Role {MAINTAINER, SYSTEMADMIN, PLANNER};
    
    public UserFactory() {
    }
     
    public static AbstractUser make(String username, String password, Role role){
        UserFactory factory = null;
        
        if (Role.PLANNER.equals(role) || Role.MAINTAINER.equals(role))
            factory = new EmployeeFactory();
        else
            factory = new AdminFactory();
        
        return factory.build(username, password, role);  
    }
    
    public AbstractUser build(String username, String password, Role role){
        AbstractUser user = selectUser(role);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
    
    protected abstract AbstractUser selectUser(Role role);
    
}
