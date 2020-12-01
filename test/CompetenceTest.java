import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettogruppo7.Competence;
import progettogruppo7.Competences;

/* @author marco */
public class CompetenceTest {
    
    private Competences elenco;
    
    public CompetenceTest() {
        elenco = new Competences();
    }
    
    @Test
    public void testInsertCompetence(){
        Competence c = new Competence("Saldatura");
        elenco.insertCompetence(c);
        assertTrue(elenco.verifyCompetence(c));
    }

    @Test
    public void testRemoveCompetence(){
        Competence c = new Competence("Saldatura");
        elenco.insertCompetence(c);
        elenco.removeCompetence(c);
        assertFalse(elenco.verifyCompetence(c));
    }
    
    @Test
    public void testModifyCompetence(){
        Competence c = new Competence("Saldatura");
        elenco.insertCompetence(c);
        elenco.modifyCompetence(c, "Installazione");
        assertEquals("Installazione", c.getName());
    }
    
    @Test(expected=RuntimeException.class)
    public void testCompetenceException(){
        Competence c = new Competence("Saldatura");
        Competence c1 = new Competence("Installazione");
        elenco.insertCompetence(c);
        elenco.insertCompetence(c1);
        elenco.modifyCompetence(c1, "Saldatura");
    }
}