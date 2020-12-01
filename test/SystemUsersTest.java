/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Users.*;


/**
 *
 * @author User
 */
public class SystemUsersTest {
    private SystemUsers users;
    private AbstractUser admin;
    private AdminFactory admFact;
    
    public SystemUsersTest() {
        this.users = SystemUsers.SystemUsers();
        this.admFact = new AdminFactory();     
        this.admin = SystemAdministrator.SystemAdministrator("Adam Kadmon", "trello");
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
        AbstractUser planner1 = admin.createUser("batman", "1234", UserFactory.Role.PLANNER);
        AbstractUser planner2 = admin.createUser("superman", "1234", UserFactory.Role.PLANNER);
        AbstractUser planner3 = admin.createUser("spiderman", "1234", UserFactory.Role.PLANNER);
        AbstractUser maintainer1 = admin.createUser("batwoman", "1234", UserFactory.Role.MAINTAINER);
        AbstractUser maintainer2 = admin.createUser("wonderwoman", "1234", UserFactory.Role.MAINTAINER);
        AbstractUser maintainer3 = admin.createUser("catwoman", "1234", UserFactory.Role.MAINTAINER);
        
        assertNotNull(planner1);
        assertNotNull(planner2);
        assertNotNull(planner3);
        assertNotNull(maintainer1);
        assertNotNull(maintainer2);
        assertNotNull(maintainer3);

    }

    
    /**
     * Test of save method, of class SystemUsers.
     */
    @Test
    public void testSave() {
        
        System.out.println("save");
        String filename = "users.txt";
        users.save(filename);
        
    }

    

}
