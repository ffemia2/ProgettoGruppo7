/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Procedure;
import progettogruppo7.Procedures;

/**
 *
 * @author Francesco Femia
 */
public class ProceduresTest {
    private Procedures p;
    
    public ProceduresTest() {
       p= new Procedures();
    }
    
    @Test
    public void testInsertProcedure(){
        Procedure proc=new Procedure("procedura");
        this.p.insertInProcedureList(proc);
        assertEquals(1, this.p.getProcedureList().size());
    }
    
    @Test
    public void testDeleteProcedure(){
        Procedure proc=new Procedure("procedura");
        this.p.insertInProcedureList(proc);
        this.p.deleteInProcedureList(proc);
        assertEquals(0, this.p.getProcedureList().size());
    }
    
    @Test
    public void testGetProcedure(){
        Procedure proc=new Procedure("procedura");
        this.p.insertInProcedureList(proc);
        Procedure getP=this.p.getFromProcedureList(proc);
        assertEquals(getP.getDescription(), proc.getDescription());
    }
    
    @Test(expected=RuntimeException.class)
    public void testGetExceptionProcedure(){
        Procedure proc=new Procedure("procedura");
        Procedure getP=this.p.getFromProcedureList(proc);
    }
    
    @Test(expected=RuntimeException.class)
    public void testDelExceptionProcedure(){
        Procedure proc=new Procedure("procedura");
        this.p.deleteInProcedureList(proc);
    }
    
}
