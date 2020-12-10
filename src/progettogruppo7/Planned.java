/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

/**
 *
 * @author Rosanna
 */
public class Planned extends Activity {

    public Planned(int activityID, Site site, String description, int estimatedTime, boolean interruptible, int week, Typology type) {
        super(activityID, site, description, estimatedTime, interruptible, week, type);
    }

    public Planned(int activityID, Site site, Competences competences, String description, int estimatedTime, boolean interruptible, int week) {
        super(activityID, site, competences, description, estimatedTime, interruptible, week);
    }
    
}
