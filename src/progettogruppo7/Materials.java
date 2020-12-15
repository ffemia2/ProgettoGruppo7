package progettogruppo7;

import java.util.HashSet;
import java.util.Iterator;
import progettogruppo7.Exceptions.MaterialException;

/* @author marco */
public class Materials {
    
    private HashSet<Material> listaMateriali;
    
    public Materials(){  
        listaMateriali = new HashSet<>();
           }
    
    public void insertMaterial(Material m){
        listaMateriali.add(m);
    }
    
    public void removeMaterial(Material m){
        listaMateriali.remove(m);
    }
    
    public boolean verifyMaterial(String s){
        Material m = new Material(s);
        if(listaMateriali.contains(m)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void modifyMaterial(Material m, String newName){
        Iterator<Material> iterator = listaMateriali.iterator();
        
        while(iterator.hasNext()){
            if(iterator.next().getName().equals(newName)){
                throw new MaterialException("Material Exception (MODIFY): material already present");
            }
        }
        
        if(!listaMateriali.contains(m)){
            throw new MaterialException("Material Exception (MODIFY): material to be modified not found");
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
