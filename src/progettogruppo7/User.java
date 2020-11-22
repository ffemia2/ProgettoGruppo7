/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

/**
 *
 * @author Rosanna
 */
public interface User {

    public enum Role {MAINTAINER, SYSTEMADMIN, PLANNER};
    
    public String getUsername();
    public void setUsername(String username);
    
    public String getPassword();
    public void setPassword(String password);
    
    public Role getRole();
    public void setRole(Role role);
}
