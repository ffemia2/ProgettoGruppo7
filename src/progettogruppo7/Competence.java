package progettogruppo7;

/* @author marco */
public class Competence {
    
    private String name;

    public Competence(String nome) {
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    @Override
    public String toString() {
        return "" + name ;
    }
 
}
