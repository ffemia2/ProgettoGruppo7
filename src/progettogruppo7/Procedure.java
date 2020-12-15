/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author Francesco Femia
 */
public class Procedure {
    private String description;
    private Competences competences;
    private PDDocument document;
    private String filename = "C:\\Users\\Rosanna\\Documents\\NetBeansProjects\\ProgettoGruppo7\\smp_proc_";
      
    public Procedure(String description) throws IOException {
        this.description = description;
        this.competences = new Competences();
        this.document = new PDDocument();
        PDPage blankPage = new PDPage();
        document.addPage(blankPage);
        document.save(this.filename + description + ".pdf");
        document.close();
    }

    public Procedure(String description, String filename) throws IOException {
        this.description = description;
        this.competences = new Competences();
        File SMP = new File(filename);
        document = PDDocument.load(SMP);
        document.close();   
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Competences getCompetences() {
        return competences;
    }

    public void setCompetences(Competences competences) {
        this.competences = competences;
    }
  
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.description);
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
        final Procedure other = (Procedure) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + description + "]\n" + this.getCompetences().toString();
    }
    
}
