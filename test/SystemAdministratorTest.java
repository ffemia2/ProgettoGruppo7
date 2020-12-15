/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.AbstractUserFactory;
import users.User;
import users.maintainer.MaintainerFactory;
import users.planner.PlannerFactory;
import users.systemAdmin.SystemAdmin;
import users.systemAdmin.SystemAdminFactory;



/**
 *
 * @author Grazia D'Amore
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
        
        assertNotNull("Received Map is null;", ((SystemAdmin)admin).getUsers());
        assertNotNull("Provided Map is null;", planners);
        assertNotNull("Provided Map is null;", maintainers);
        assertEquals("Size mismatch for maps;",maintainers.size(), ((SystemAdmin)admin).getUsers().getMaintainers().size());
        assertEquals("Size mismatch for maps;", planners.size(), ((SystemAdmin)admin).getUsers().getPlanners().size());
        assertTrue("Missing keys in received map;", ((SystemAdmin)admin).getUsers().getMaintainers().keySet().containsAll(maintainers.keySet()));
        assertTrue("Missing keys in received map;", ((SystemAdmin)admin).getUsers().getPlanners().keySet().containsAll(planners.keySet()));
        
        maintainers.keySet().stream().forEach((key) -> {
        assertEquals("Value mismatch for key '" + key + "';", maintainers.get(key), ((SystemAdmin)admin).getUsers().getMaintainers().get(key));
        });

        planners.keySet().stream().forEach((key) -> {
        assertEquals("Value mismatch for key '" + key + "';", planners.get(key), ((SystemAdmin)admin).getUsers().getPlanners().get(key));
        });
            
       
    }
    
    
}
