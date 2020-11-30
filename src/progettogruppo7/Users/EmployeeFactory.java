/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import progettogruppo7.Users.AbstractUser;
import progettogruppo7.Users.Maintainer;
import progettogruppo7.Users.UserFactory;
import progettogruppo7.Users.Planner;



/**
 *
 * @author Grazia D'Amore
 */
public class EmployeeFactory extends UserFactory {

    @Override
    protected AbstractUser selectUser(Role role) {
        if (role == Role.MAINTAINER)
            return new Maintainer("", "");
        else
            return new Planner("","");
    }
        
    
    
}
