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
        lista.inserisciMateriale(m1);
        lista.inserisciMateriale(m2);
        lista.inserisciMateriale(m3);
        lista.inserisciMateriale(m4);
        
        //Verifica dell'inserimento dei materiali all'interno della struttura dati
        lista.stampaMateriali();
        
        //Rimozione dei materiali dalla lista
        lista.rimuoviMateriale(m4);
        lista.rimuoviMateriale(m1);
        
        //Verifica della rimozione dei materiali dalla lista
        lista.stampaMateriali();
        
        //Modifica di un materiale nella lista
        lista.modificaMateriale(m2, "Chiodi");
        
        //Verifica della modifica dei materiali dalla lista
        lista.stampaMateriali();
        
        //Verifica delle eccezioni (Inserimento: materiale già presente nella lista)
        //lista.inserisciMateriale(m2);
        
        //Verifica delle eccezioni (Rimozione: materiale non presente nella lista)
        //lista.rimuoviMateriale(m4);
        
        //Verifica delle eccezioni (Modifica: materiale non presente nella lista)
        //lista.modificaMateriale(m3, "Chiodi");
        
        //Verifica delle eccezioni (Modifica: materiale non presente in lista)
        //lista.modificaMateriale(m1, "Martello");
    }
    
}
