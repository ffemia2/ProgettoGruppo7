/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Unplanned;

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
public class Extra extends Activity implements ActivityInterface{

    public Extra(Site site, ExtraBuilder.Typology typology, String description, int week, int estimatedTime, boolean assigned, Materials materials, Competences competences, Procedure procedure) {
        super(site, typology, description, week, estimatedTime, assigned, materials, competences, false, procedure);
    }
    
}
