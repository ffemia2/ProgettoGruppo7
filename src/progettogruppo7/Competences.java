package progettogruppo7;

import java.util.HashSet;
import java.util.Iterator;
import progettogruppo7.Exceptions.CompetenceException;

/* @author marco */
public class Competences implements Iterable<Competence> {
    
    private HashSet<Competence> competenze;

    public Competences() {
       competenze = new HashSet<>();
    }
    
    public void insertCompetence(Competence c){
        competenze.add(c);
    }
    
    public void removeCompetence(Competence c){
        competenze.remove(c);
    }
    
    public boolean verifyCompetence(Competence c){
        if(competenze.contains(c)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void modifyCompetence(Competence c, String s){
        Iterator<Competence> iterator = competenze.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getName().equals(s)){
                throw new CompetenceException("Competence Exception (MODIFY): competence already present");
            }
        }
        if(!competenze.contains(c)){
            throw new CompetenceException("Competence Exception (MODIFY): competence to be modified not found");
        }
        else{
            c.setName(s);
        }
    }
    
    public void printCompetences(){
        String s = "";
        for(Competence c: competenze){
            s = s + c.toString() + "\n";
        }
        System.out.println(s);
    }

    @Override
    public Iterator<Competence> iterator() {
        return competenze.iterator();
    }
}