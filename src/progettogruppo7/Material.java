package progettogruppo7;

/* @author marco */
public class Material {
    
    private String name;

    public Material(String nome) {
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
        return name;
    }
       
}