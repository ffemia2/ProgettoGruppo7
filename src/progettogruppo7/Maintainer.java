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
    
    public Maintainer(String username, String password) {
        super(username, password, User.Role.MAINTAINER);
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Maintainer" + "\t" + super.toString();
    }

    @Override
    public AbstractUser removeUser(AbstractUser u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public AbstractUser getUser(AbstractUser u) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }  

    @Override
    public AbstractUser createUser(String username, String password, Role role) {
        throw new UnsupportedOperationException("Not supported for this role."); 
    }

    @Override
    public String getUsers() {
        throw new UnsupportedOperationException("Not supported for this role.");
    }
    
}