/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.planner;
import users.AbstractUserFactory;
import users.JDBC;
import users.User;

/**
 *
 * @author Grazia D'Amore
 */
public class PlannerFactory extends AbstractUserFactory {

    @Override
    public JDBC createJDBCUser(String username, String password){
        return new Planner_JDBC(username,password);
    }
    
    @Override
    public User createUser(String username, String password){
        return new Planner(username,password);
    }
 /*   
    @Override
    protected AbstractUser selectUser(Role role) {
        if (role == Role.MAINTAINER)
            return new Maintainer("", "");
        else
            return new Planner("","");
    }*/
        
    
    
}
