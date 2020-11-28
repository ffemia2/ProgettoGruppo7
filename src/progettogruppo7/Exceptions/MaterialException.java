package progettogruppo7.Exceptions;

/* @author marco */
public class MaterialException extends Exception {

    public MaterialException(int parameter){
        if(parameter == 1){
            System.out.println("Material Exception (INSERT): material already present");
        }
        else if (parameter == 2){
            System.out.println("Material Exception (REMOVE or MODIFY): material not present");
        }
        else if (parameter == 3){
            System.out.println("Material Exception (MODIFY): material already present");
        }
    }

}
