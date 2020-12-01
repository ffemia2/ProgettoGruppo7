/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

/**
 *
 * @author Grazia D'Amore
 */
public abstract class AbstractEmployee extends AbstractUser {
    
    public AbstractEmployee(String username, String password, UserFactory.Role role) {
        super(username, password, role);
    }
    
}
