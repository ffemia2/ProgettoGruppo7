/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Users.*;


/**
 *
 * @author Grazia D'Amore
 */
public class SystemUsersTest {
    private SystemUsers users;
    private User admin;
    private SystemAdminFactory admFact;
    
    public SystemUsersTest() {
        this.users = SystemUsers.SystemUsers();
        this.admFact = new SystemAdminFactory();     
        this.admin = SystemAdmin.SystemAdministrator("Adam Kadmon", "trello");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addUser method, of class SystemUsers.
     */
    @Test
    public void testAddUser() throws Exception {
        
        System.out.println("addUser");
        User planner1 = admin.addUser("batman", "1234", AbstractUserFactory.Role.PLANNER);
        User planner2 = admin.addUser("superman", "1234", AbstractUserFactory.Role.PLANNER);
        User planner3 = admin.addUser("spiderman", "1234", AbstractUserFactory.Role.PLANNER);
        User maintainer1 = admin.addUser("batwoman", "1234", AbstractUserFactory.Role.MAINTAINER);
        User maintainer2 = admin.addUser("wonderwoman", "1234", AbstractUserFactory.Role.MAINTAINER);
        User maintainer3 = admin.addUser("catwoman", "1234", AbstractUserFactory.Role.MAINTAINER);
        
        assertNotNull(planner1);
        assertNotNull(planner2);
        assertNotNull(planner3);
        assertNotNull(maintainer1);
        assertNotNull(maintainer2);
        assertNotNull(maintainer3);

    }    

}
