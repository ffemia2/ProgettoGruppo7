/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;


/**
 *
 * @author Rosanna
 */
public abstract class AbstractUserFactory { 
    public enum Role {MAINTAINER, SYSTEMADMIN, PLANNER};
    
    public abstract JDBC createJDBCUser(String username, String password);
    public abstract User createUser(String username, String password);
}
