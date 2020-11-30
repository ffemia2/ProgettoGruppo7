/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Planner extends AbstractUser{
    
    public Planner(String username, String password) {
        super(username, password, UserFactory.Role.PLANNER);
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
        throw new UnsupportedOperationException("Not supported for this role."); 
    }


    @Override
    public String printUsers() {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public LinkedList<AbstractUser> getUsers() {
        throw new UnsupportedOperationException("Not supported for this role."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractUser getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
    
   
}
