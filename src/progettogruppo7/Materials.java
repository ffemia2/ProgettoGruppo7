package progettogruppo7;

import java.util.Iterator;
import java.util.LinkedList;
import progettogruppo7.Exceptions.MaterialException;

/* @author marco */
public class Materials {
    
    private LinkedList<Material> listaMateriali;
    
    public Materials(){  
        listaMateriali = new LinkedList<Material>();
                }
    
    public void inserisciMateriale(Material m) throws MaterialException{
        if(listaMateriali.contains(m)){
            throw new MaterialException(1);
        }
        else{
            listaMateriali.add(m);
        }
    }
    
    public void rimuoviMateriale(Material m)throws MaterialException{
        if(!listaMateriali.contains(m)){
            throw new MaterialException(2);
        }
        else{
        Iterator<Material> iterator = listaMateriali.iterator();
        
        while (iterator.hasNext()){
            if (iterator.next().getNome().equals(m.getNome())){
                iterator.remove();
                }
            }
        }
    }
    
    public void modificaMateriale(Material m, String newName) throws MaterialException{
        Iterator<Material> iterator = listaMateriali.iterator();
        
        while(iterator.hasNext()){
            if(iterator.next().getNome().equals(newName)){
                throw new MaterialException(3);
            }
        }
        
        if(!listaMateriali.contains(m)){
            throw new MaterialException(2);
        }
        else{
           m.setNome(newName);
        }
    }
    
    public void stampaMateriali(){
        String s = "";
        for(Material m: listaMateriali){
            s = s + m.toString() + "\n";
        }
        System.out.println("Materiali:" + "\n" + s);
    }
    
}
