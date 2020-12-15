/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;
import java.util.Calendar;


/**
 *
 * @author Rosanna
 */
public abstract class AbstractUserFactory {
      
    public enum Role {MAINTAINER, SYSTEMADMIN, PLANNER};
    public enum weekDay {MON, TUE, WED, THU, FRI, SAT, SUN};
    
    public abstract JDBC createJDBCUser(String username, String password);
    public abstract User createUser(String username, String password);
}
