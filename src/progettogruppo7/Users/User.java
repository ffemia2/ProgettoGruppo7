/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.LinkedList;

/**
 *
 * @author Rosanna
 */
public interface User {

    public String getUsername();
    public void setUsername(String username);
    
    public String getPassword();
    public void setPassword(String password);
    
    public UserFactory.Role getRole();
    public void setRole(UserFactory.Role role);
    
    // System Administrator methods //
 
       
    public AbstractUser removeUser(AbstractUser u);
    
    public AbstractUser getUser(String username);
    
    public AbstractUser createUser(String username, String password, UserFactory.Role role);
    
    public String printUsers();
    
    public LinkedList<AbstractUser> getUsers();
}
