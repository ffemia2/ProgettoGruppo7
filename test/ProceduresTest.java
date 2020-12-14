/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Competences;
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
    public void testSizeInsertProcedure() throws IOException{
        Procedure proc=new Procedure("procedura", new Competences());
        this.p.insertInProcedureMap(proc);
        assertEquals(1, this.p.getProcedureMap().size());
    }
    
    @Test
    public void testDescriptionInsertProcedure() throws IOException{
        Procedure proc=new Procedure("procedura", new Competences());
        this.p.insertInProcedureMap(proc);
        assertEquals(proc.getDescription(), this.p.getFromProcedureMap(proc).getDescription());
    }
    
    @Test
    public void testDeleteProcedure() throws IOException{
        Procedure proc=new Procedure("procedura", new Competences());
        this.p.insertInProcedureMap(proc);
        this.p.deleteInProcedureMap(proc);
        assertEquals(0, this.p.getProcedureMap().size());
    }
    
    @Test
    public void testGetProcedure() throws IOException{
        Procedure proc=new Procedure("procedura", new Competences());
        this.p.insertInProcedureMap(proc);
        Procedure getP=this.p.getFromProcedureMap(proc);
        assertEquals(getP.getDescription(), proc.getDescription());
    }
    
    @Test(expected=RuntimeException.class)
    public void testGetExceptionProcedure() throws IOException{
        Procedure proc=new Procedure("procedura", new Competences());
        Procedure getP=this.p.getFromProcedureMap(proc);
    }
    
    @Test(expected=RuntimeException.class)
    public void testDelExceptionProcedure() throws IOException{
        Procedure proc=new Procedure("procedura", new Competences());
        this.p.deleteInProcedureMap(proc);
    }
    
    @Test
    public void testModifyProcedure() throws IOException{
        String description ="manutenzione";
        Procedure proc=new Procedure("procedura", new Competences());
        this.p.insertInProcedureMap(proc);
        this.p.mofifyFromProcedureMap(proc, description);
        assertEquals(this.p.getFromProcedureMap(proc).getDescription(), description);
    }
}
