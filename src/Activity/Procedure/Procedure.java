/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Procedure;

import Activity.competence.Competences;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Francesco Femia
 */
public class Procedure {
    private String description;
    private Competences competences;
    private PDDocument document;
      
    public Procedure(String description, Competences c){
        String path=System.getProperty("user.dir");
        this.description = description;
        this.competences = c;
        
        if(new File(path+"\\smp_proc_" + description + ".pdf").length()==0){
            try {
                this.document = new PDDocument();
                PDPage blankPage = new PDPage();
                this.document.addPage(blankPage);
                PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
                
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.newLineAtOffset(25, 700);
                String text=this.toString().replace("\n", "").replace("\r", "");
                contentStream.showText(text);
                contentStream.endText();
                contentStream.close();
                
                
                this.document.save(path+"\\smp_proc_" + description + ".pdf");
                this.document.close();
                //Desktop.getDesktop().open(new File(path+"\\smp_proc_" + description + ".pdf"));
            } catch (IOException ex) {
                Logger.getLogger(Procedure.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*else
            try {
                //Desktop.getDesktop().open(new File(path+"\\smp_proc_" + description + ".pdf"));
        } catch (IOException ex) {
            Logger.getLogger(Procedure.class.getName()).log(Level.SEVERE, null, ex);*/
        
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
        return Objects.equals(this.description, other.description);
    }

    @Override
    public String toString() {
        return "[" + description + "]\n" + this.getCompetences().toString();
    }
    
}
