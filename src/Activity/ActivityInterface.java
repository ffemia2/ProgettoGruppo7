/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity;

import Activity.Procedure.Procedure;
import Activity.material.Materials;
import Activity.competence.Competences;

/**
 *
 * @author Grazia D'Amore
 */
public interface ActivityInterface {   
     
    public void setMaterials(Materials m) ;

    public void setTypology( ActivityBuilder.Typology t);
    
    public void setSite(Site s);

    public void setAssigned(boolean assign);
    
    public void setCompetences(Competences c);
    
    public void setDescription(String d);

    public void setEstimatedTime(int time);

    public void setInterruptible(boolean interr);
    
    public void setWeek(int week);

    public void setProcedure(Procedure procedure);


    
}
