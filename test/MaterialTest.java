import org.junit.Test;
import static org.junit.Assert.*;
import Activity.material.Material;
import Activity.material.Materials;

/* @author marco */
public class MaterialTest {
    
    private Materials elenco;
    
    public MaterialTest() {
        elenco = new Materials();
    }
    
    @Test
    public void testInsertMaterial(){
        Material m = new Material("Filo di stagno");
        elenco.insertMaterial(m);
        assertTrue(elenco.verifyMaterial(m.getName()));
    }
    
    @Test
    public void testRemoveMaterial(){
        Material m = new Material("Saldatore a stagno");
        elenco.insertMaterial(m);
        elenco.removeMaterial(m);
        assertFalse(elenco.verifyMaterial(m.getName()));
    }
    
    @Test
    public void testModifyMaterial(){
        Material m = new Material("Amperometro");
        elenco.insertMaterial(m);
        elenco.modifyMaterial(m, "Guanti");
        assertEquals("Guanti", m.getName());
    }
    
    @Test(expected = RuntimeException.class)
    public void testMaterialException(){
        Material m1 = new Material("Filo di stagno");
        Material m2 = new Material("Martello");
        elenco.insertMaterial(m1);
        elenco.insertMaterial(m2);
        elenco.modifyMaterial(m2, "Filo di stagno");
    }
}
