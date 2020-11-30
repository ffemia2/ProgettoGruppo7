/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import progettogruppo7.Users.*;

/**
 *
 * @author User
 */
public class SystemAdministratorTest {
    private EmployeeFactory employee = new EmployeeFactory(); 
    private AdminFactory fact = new AdminFactory();
    private AbstractUser admin = fact.build("Adam", "Kadmon", UserFactory.Role.SYSTEMADMIN);    
    
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
        admin = fact.build("Adam", "Kadmon", UserFactory.Role.SYSTEMADMIN);
        assertNotNull(admin);
    }

    /**
     * Test of createUser and addUser methods, of class SystemAdministrator.
     */
    @Test
    public void testCreateUser() {
        AbstractUser planner = employee.build("primo", "test", UserFactory.Role.PLANNER);
        AbstractUser maintainer = employee.build("secondo", "test", UserFactory.Role.MAINTAINER);
        assertEquals(planner, admin.createUser("primo", "test", UserFactory.Role.PLANNER));
        assertEquals(maintainer, admin.createUser("secondo", "test", UserFactory.Role.MAINTAINER));
        assertNull(admin.createUser("secondo", "exception", UserFactory.Role.MAINTAINER));
    }
    
   
    /**
     * Test of removeUser method, of class SystemAdministrator.
     */
    @Test
    public void testRemoveUser() {
        AbstractUser planner = admin.createUser("quinto", "test", UserFactory.Role.PLANNER);
        AbstractUser maintainer = admin.createUser("sesto", "test", UserFactory.Role.MAINTAINER);
        AbstractUser none = employee.build("signor", "nessuno", UserFactory.Role.PLANNER);
        assertEquals(planner,admin.removeUser(planner));
        assertEquals(maintainer,admin.removeUser(maintainer));
        assertNull(admin.removeUser(none));
    }

    /**
     * Test of getUser method, of class SystemAdministrator.
     */
    @Test 
    public void testGetUser() {
        admin.createUser("settimo", "test", UserFactory.Role.PLANNER);
        admin.createUser("ottavo", "test", UserFactory.Role.MAINTAINER);
        //System.out.println(admin.getUsers());
        assertNull(admin.getUser("signor"));
    }
    
    /**
     * Test of getUsers method, of class SystemAdministrator.
     */
    @Test 
    public void testGetUsers() {
        LinkedList<AbstractUser> list = new LinkedList();
        list.add(admin.createUser("ottavo", "test", UserFactory.Role.MAINTAINER));
        list.add(admin.createUser("settimo", "test", UserFactory.Role.PLANNER));
        assertEquals(list,admin.getUsers());
    }
    
    
}
