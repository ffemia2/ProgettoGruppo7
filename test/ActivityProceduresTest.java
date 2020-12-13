/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Activity;
import progettogruppo7.ActivityProcedures;
import progettogruppo7.Exceptions.ActivProcException;
import progettogruppo7.Planned;
import progettogruppo7.Procedure;
import progettogruppo7.Procedures;
import progettogruppo7.Site;
import static progettogruppo7.Typology.*;

/**
 *
 * @author Rosanna
 */
public class ActivityProceduresTest {
    
    @Test
    public void testInsertActivProc() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a1 = new Planned(1, site1, "Replacement of robot", 120, true, 51, Electrical);
        Activity a3 = new Planned(3, site1, "Clean oil system", 480, true, 46, Hydraulic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        ActivityProcedures ac= new ActivityProcedures();
        ac.insertInActivProc(a3, procedures);
        assertEquals(2, ac.getFromActivProc(a3).getProcedureMap().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcInsertActivProc() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        ActivityProcedures ac = new ActivityProcedures();
        ac.insertInActivProc(a2, procedures);
        ac.insertInActivProc(a2, procedures);
    }
    
    @Test //removeFromActivProc(int id)
    public void testRemoveFromActivProcInt() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        ActivityProcedures ac = new ActivityProcedures();
        ac.insertInActivProc(a2, procedures);
        ac.removeFromActivProc(2);
        assertEquals(0, ac.getActivityProcedures().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcRemoveActivities() {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        ActivityProcedures ac = new ActivityProcedures();
        ac.removeFromActivProc(2);
    }
    
    @Test
    public void testRemoveFromActivProcActiv() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        ActivityProcedures ac = new ActivityProcedures();
        ac.insertInActivProc(a2, procedures);
        ac.removeFromActivProc(a2);
        assertEquals(0, ac.getActivityProcedures().size());
    }
    
    @Test 
    public void testGetFromActivProcActiv() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        ActivityProcedures ac = new ActivityProcedures();
        ac.insertInActivProc(a2, procedures);
        Procedures proc2 = ac.getFromActivProc(a2);
        assertEquals(2, proc2.getProcedureMap().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcGetFromActivProcActiv() {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        ActivityProcedures ac = new ActivityProcedures();
        ac.getFromActivProc(a2);
    }
    
    @Test 
    public void testModifyInActivProcProcedures() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedure p3 = new Procedure("Checking for leaks and changing gaskets");
        Procedure p4 = new Procedure("Check mechanical seal, couplings");
        Procedure p5 = new Procedure("Change pump");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        Procedures procedures2 = new Procedures();
        procedures2.insertInProcedureMap(p3);
        procedures2.insertInProcedureMap(p4);
        procedures2.insertInProcedureMap(p5);
        ActivityProcedures ac = new ActivityProcedures();
        ac.insertInActivProc(a2, procedures);
        ac.modifyInActivProcProcedures(a2, procedures2);
        assertEquals(3, ac.getFromActivProc(a2).getProcedureMap().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcModifyInActivProcProcedures() throws IOException {
        Site site1 = new Site("Fisciano", "Molding");
        Activity a2 = new Planned(2, site1, "Checking gas implant", 360, false, 48, Eletronic);
        Procedure p1 = new Procedure("Condensate purging");
        Procedure p2 = new Procedure("Changing filter media");
        Procedures procedures = new Procedures();
        procedures.insertInProcedureMap(p1);
        procedures.insertInProcedureMap(p2);
        ActivityProcedures ac = new ActivityProcedures();
        ac.modifyInActivProcProcedures(a2, procedures);
    }
}
