package progettogruppo7.Exceptions;

/* @author marco */
public class MaterialException extends Exception {

    public MaterialException(int parameter){
        if(parameter == 1){
            System.out.println("Material Exception (inserimento): Materiale già presente nella lista");
        }
        else if (parameter == 2){
            System.out.println("Material Exception (rimozione o modifica): Materiale non presente nella lista");
        }
        else if (parameter == 3){
            System.out.println("Material Exception (modifica): Materiale già presente nella lista");
        }
    }

}
