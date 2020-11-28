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
    
    public void insertMaterial(Material m) throws MaterialException{
        if(listaMateriali.contains(m)){
            throw new MaterialException(1);
        }
        else{
            listaMateriali.add(m);
        }
    }
    
    public void removeMaterial(Material m)throws MaterialException{
        if(!listaMateriali.contains(m)){
            throw new MaterialException(2);
        }
        else{
        Iterator<Material> iterator = listaMateriali.iterator();
        
        while (iterator.hasNext()){
            if (iterator.next().getName().equals(m.getName())){
                iterator.remove();
                }
            }
        }
    }
    
    public void modifyMaterial(Material m, String newName) throws MaterialException{
        Iterator<Material> iterator = listaMateriali.iterator();
        
        while(iterator.hasNext()){
            if(iterator.next().getName().equals(newName)){
                throw new MaterialException(3);
            }
        }
        
        if(!listaMateriali.contains(m)){
            throw new MaterialException(2);
        }
        else{
           m.setName(newName);
        }
    }
    
    public void printMaterials(){
        String s = "";
        for(Material m: listaMateriali){
            s = s + m.toString() + "\n";
        }
        System.out.println("Materiali:" + "\n" + s);
    }
    
}
