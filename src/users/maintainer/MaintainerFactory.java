/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.maintainer;

import users.AbstractUserFactory;
import users.JDBC;
import users.User;



/**
 *
 * @author User
 */
public class MaintainerFactory extends AbstractUserFactory {
    
   
    
    @Override
    public JDBC createJDBCUser(String username, String password){
        return new Maintainer_JDBC(username,password);
    }
    
    @Override
    public User createUser(String username, String password){
        return new Maintainer(username,password);
    } 
    
}
