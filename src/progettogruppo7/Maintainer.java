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
public class Maintainer extends AbstractUser {
    private int availability;
    // Competences
    // Activities
    
    public Maintainer() {
        this("","",User.Role.MAINTAINER,0);
    }
    
    public Maintainer(String username, String password, Role role) {
        this(username, password, User.Role.MAINTAINER, 0);
    } 
    
    public Maintainer(String username, String password, Role role, int availability) {
        super(username, password, User.Role.MAINTAINER);
        this.availability = availability;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Maintainer{" + "availability=" + availability + '}';
    }
    
}
