/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

/**
 *
 * @author User
 */
public class Planner extends AbstractUser{
    
    public Planner(String username, String password) {
        super(username, password, User.Role.PLANNER);
    }
    

    @Override
    public AbstractUser removeUser(AbstractUser u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public AbstractUser getUser(AbstractUser u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public String toString() {
        return "Planner"+ "\t"+ super.toString();
    }

    @Override
    public AbstractUser createUser(String username, String password, Role role) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public String getUsers() {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }
   
    
   
}
