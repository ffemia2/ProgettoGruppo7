import progettogruppo7.Material;
import progettogruppo7.Exceptions.MaterialException;
import progettogruppo7.Materials;

/* @author marco */
public class MaterialTest {

    public static void main(String[] args) throws MaterialException {
        
        //Creazione della struttura dati
        Materials lista = new Materials();
        
        //Creazione dei materiali
        Material m1 = new Material("Fil di ferro");
        Material m2 = new Material("Carta vetrata");
        Material m3 = new Material("Filo di stagno");
        Material m4 = new Material("Saldatore a stagno");
        
        //Inserimento dei materiali nella lista
        lista.insertMaterial(m1);
        lista.insertMaterial(m2);
        lista.insertMaterial(m3);
        lista.insertMaterial(m4);
        
        //Verifica dell'inserimento dei materiali all'interno della struttura dati
        lista.printMaterials();
        
        //Rimozione dei materiali dalla lista
        lista.removeMaterial(m4);
        lista.removeMaterial(m1);
        
        //Verifica della rimozione dei materiali dalla lista
        lista.printMaterials();
        
        //Modifica di un materiale nella lista
        lista.modifyMaterial(m2, "Chiodi");
        
        //Verifica della modifica dei materiali dalla lista
        lista.printMaterials();
        
        //Verifica delle eccezioni (Inserimento: materiale già presente nella lista)
        lista.insertMaterial(m2);
        
        //Verifica delle eccezioni (Rimozione: materiale non presente nella lista)
        lista.removeMaterial(m4);
        
        //Verifica delle eccezioni (Modifica: materiale non presente nella lista)
        lista.modifyMaterial(m3, "Chiodi");
        
        //Verifica delle eccezioni (Modifica: materiale non presente in lista)
        lista.modifyMaterial(m1, "Martello");
    }
    
}
