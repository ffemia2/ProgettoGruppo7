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
public class AdminFactory extends UserFactory{

    @Override
    protected AbstractUser selectUser(Role role) {
       if (role == Role.SYSTEMADMIN)
           return SystemAdministrator.SystemAdministrator("","");
       else
           return null;
    }
    
}
