package progettogruppo7;

/* @author marco */
public class Material {
    
    private String nome;

    public Material(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
       
}