/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

/**
 *
 * @author User
 */
public abstract class AbstractAdmin extends AbstractUser {

    public AbstractAdmin(String username, String password, UserFactory.Role role) {
        super(username, password, role);
    }
  
    
}
