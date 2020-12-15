package Activity.competence;


import java.util.Objects;

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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Competence other = (Competence) obj;
        
        return Objects.equals(this.name, other.name);
    }
 
}
