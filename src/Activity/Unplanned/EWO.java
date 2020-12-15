/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Unplanned;

import Activity.Procedure.Procedure;
import Activity.Site;
import Activity.competence.Competences;
import Activity.material.Materials;

/**
 *
 * @author Grazia D'Amore
 */
public class EWO extends Unplanned{
    
    public EWO(Site site, UnplannedBuilder.Typology typology, String description, int week, int estimatedTime, boolean assigned, Materials materials, Competences competences, Procedure procedure) {
        super(site, typology, description, week, estimatedTime, assigned, materials, competences, procedure);
    }
    
    
}
