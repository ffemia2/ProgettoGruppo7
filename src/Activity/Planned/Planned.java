/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Planned;

import Activity.Activity;
import Activity.ActivityInterface;
import Activity.Procedure.Procedure;
import Activity.competence.Competences;
import Activity.material.Materials;
import Activity.Site;

/**
 *
 * @author Grazia D'Amore
 */
public class Planned extends Activity implements ActivityInterface {
    

    public Planned(boolean assigned, boolean interruptible, int estimatedTime, int week, String description, Site site, Materials materials, PlannedBuilder.Typology typology, Competences competences, Procedure procedure) {
        super(site, typology, description, week, estimatedTime, assigned, materials, competences,interruptible,procedure);
    }
    
    
}
