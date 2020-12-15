/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Exceptions.UsernameException;

import progettogruppo7.Users.*;

/**
 *
 * @author User
 */
public class SystemAdministratorTest { 
    private AbstractUserFactory pfactory = new PlannerFactory();
    private AbstractUserFactory mfactory = new MaintainerFactory();
    private User admin = new SystemAdminFactory().createUser("Adam", "Kadmon");    
    
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
        admin = new SystemAdminFactory().createUser("Adam", "Kadmon");
        assertNotNull(admin);
    }

    /**
     * Test of createUser and addUser methods, of class SystemAdministrator.
     */
    @Test
    public void testCreateUser() {
        User planner = pfactory.createUser("primo", "test");
        User maintainer = mfactory.createUser("secondo", "test");
        assertEquals(planner, admin.addUser("primo", "test", AbstractUserFactory.Role.PLANNER));
        assertEquals(maintainer, admin.addUser("secondo", "test", AbstractUserFactory.Role.MAINTAINER));
        assertNull(admin.addUser("secondo", "exception", AbstractUserFactory.Role.MAINTAINER));
    }
    
   
    /**
     * Test of removeUser method, of class SystemAdministrator.
     */
    @Test
    public void testRemoveUser() {
        User planner = admin.addUser("quinto", "test", AbstractUserFactory.Role.PLANNER);
        User maintainer = admin.addUser("sesto", "test", AbstractUserFactory.Role.MAINTAINER);
        User none = pfactory.createUser("signor", "nessuno");
        assertEquals(planner,admin.removeUser(planner));
        assertEquals(maintainer,admin.removeUser(maintainer));
        assertNull(admin.removeUser(none));
    }

    /**
     * Test of getUser method, of class SystemAdministrator.
     */
    @Test 
    public void testGetUser() {
        admin.addUser("settimo", "test", AbstractUserFactory.Role.PLANNER);
        admin.addUser("ottavo", "test", AbstractUserFactory.Role.MAINTAINER);
        //System.out.println(admin.getUsers());
        assertNull(admin.getUser("signor"));
    }
    
    /**
     * Test of getUsers method, of class SystemAdministrator.
     */
    @Test 
    public void testGetUsers() {
        Map<String,User> planners = new HashMap<>();
        Map<String,User> maintainers = new HashMap<>();
        
        planners.put("settimo",admin.addUser("settimo", "test", AbstractUserFactory.Role.PLANNER));
        maintainers.put("ottavo",admin.addUser("ottavo", "test", AbstractUserFactory.Role.MAINTAINER));
        
        assertNotNull("Received Map is null;", admin.getUsers());
        assertNotNull("Provided Map is null;", planners);
        assertNotNull("Provided Map is null;", maintainers);
        assertEquals("Size mismatch for maps;",maintainers.size(), admin.getUsers().getMaintainers().size());
        assertEquals("Size mismatch for maps;", planners.size(), admin.getUsers().getPlanners().size());
        assertTrue("Missing keys in received map;", admin.getUsers().getMaintainers().keySet().containsAll(maintainers.keySet()));
        assertTrue("Missing keys in received map;", admin.getUsers().getPlanners().keySet().containsAll(planners.keySet()));
        
        maintainers.keySet().stream().forEach((key) -> {
        assertEquals("Value mismatch for key '" + key + "';", maintainers.get(key), admin.getUsers().getMaintainers().get(key));
        });

        planners.keySet().stream().forEach((key) -> {
        assertEquals("Value mismatch for key '" + key + "';", planners.get(key), admin.getUsers().getPlanners().get(key));
        });
            
       
    }
    
    
}
