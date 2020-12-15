/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.systemAdmin;
import users.User;
import users.JDBC;
import users.*;

/**
 *
 * @author Grazia D'Amore
 */
public class SystemAdminFactory extends AbstractUserFactory{

    public JDBC createJDBCUser(String username, String password){
        return new SystemAdmin_JDBC(username,password);
    }
    
    public User createUser(String username, String password){
        return SystemAdmin.SystemAdministrator(username, password);
    }
    
    /*
    @Override
    protected AbstractUser selectUser(Role role) {
       if (role == Role.SYSTEMADMIN)
           return SystemAdministrator.SystemAdministrator("","");
       else
           return null;
    }
    */
}
