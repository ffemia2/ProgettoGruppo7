package Activity.competence;

import java.util.HashSet;
import java.util.Iterator;

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
    
    public boolean verifyCompetence(String s){
        Competence c = new Competence(s);
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
    
    public int size(){
        return competenze.size();
    }

    @Override
    public String toString() {
        String s = "";
        for(Competence c: competenze){
            s = s +" - "+ c.toString() + "\r";
        }
        return s;
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

    
    public String getInsertQuery(String m){
        StringBuilder temp = new StringBuilder(); 
        String table;
        try{
            int id = Integer.parseInt(m);
            table = "Activity_competences(activity, competence) VALUES(" + m;
         }catch(NumberFormatException e){ 
            table = "Maintainer_competences(maintainer, competence) VALUES('" + m + "'";
        }
        for(Competence c : competenze){
            temp.append("INSERT INTO competence(nome) VALUES (").append("'").append(c.getName()).append("');");
            temp.append("INSERT INTO ").append(table).append("'").append(c.getName()).append("');");
        }
        return temp.toString();
    }
}
