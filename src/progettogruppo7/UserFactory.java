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
public class UserFactory {
     
     public User build(String username, String password, User.Role role) {
        User u = selectUser(role);
        u.setUsername(username);
        u.setPassword(password);
        return u;
    }
    
    protected User selectUser(User.Role role) {
        if (role == User.Role.MAINTAINER) {
        return new Maintainer();
        }
        else if (role == User.Role.PLANNER) {
            //return new Planner();
            return null;
        }
        else if (role == User.Role.SYSTEMADMIN) {
            //return new SystemAdmin();
            return null;
        }
        return null;
    }
    
}
