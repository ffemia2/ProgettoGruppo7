/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import progettogruppo7.UserFactory;
import progettogruppo7.User;
import progettogruppo7.AbstractUser;

/**
 *
 * @author User
 */
public class SystemAdministratorTest {
    private UserFactory fact = new UserFactory(); 
    private AbstractUser admin = fact.build("Adam", "Kadmon", User.Role.SYSTEMADMIN);    
    
    public SystemAdministratorTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of SystemAdministrator method, of class SystemAdministrator.
     */
    @Test
    public void testSystemAdministrator() {
        admin = fact.build("Adam", "Kadmon", User.Role.SYSTEMADMIN);
        assertNotNull(admin);
    }

    /**
     * Test of createUser and addUser methods, of class SystemAdministrator.
     */
    @Test
    public void testCreateUser() {
        AbstractUser planner = fact.build("primo", "test", User.Role.PLANNER);
        AbstractUser maintainer = fact.build("secondo", "test", User.Role.MAINTAINER);
        assertEquals(planner, admin.createUser("primo", "test", User.Role.PLANNER));
        assertEquals(maintainer, admin.createUser("secondo", "test", User.Role.MAINTAINER));
    }
    
    /**
     * Test of Username Exception of addUser method, of class SystemAdministrator.
     */
    @Test (expected = Exception.class)
    public void testCreateUserException() {
        Exception e = null;
        
        admin.createUser("terzo", "test", User.Role.PLANNER);
        admin.createUser("quarto", "test", User.Role.MAINTAINER);

        admin.createUser("terzo", "exception", User.Role.MAINTAINER);

    }
  
    /**
     * Test of removeUser method, of class SystemAdministrator.
     */
    @Test
    public void testRemoveUser() {
        AbstractUser planner = admin.createUser("quinto", "test", User.Role.PLANNER);
        AbstractUser maintainer = admin.createUser("sesto", "test", User.Role.MAINTAINER);
        AbstractUser none = fact.build("signor", "nessuno", User.Role.PLANNER);
        assertEquals(planner,admin.removeUser(planner));
        assertEquals(maintainer,admin.removeUser(maintainer));
        assertNull(admin.removeUser(none));
    }

    /**
     * Test of getUser method, of class SystemAdministrator.
     */
    @Test
    public void testGetUser() {
        admin.createUser("settimo", "test", User.Role.PLANNER);
        admin.createUser("ottavo", "test", User.Role.MAINTAINER);
        //System.out.println(admin.getUsers());
        AbstractUser none = fact.build("signor", "nessuno", User.Role.PLANNER);
        assertNull(admin.getUser(none));
    }
    
    
}
