/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity;

import Activity.Procedure.Procedures;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Rosanna
 */
public class ActivityProcedures {
    private Map<Integer,Procedures> activProc;

    public ActivityProcedures() {
        this.activProc = new Hashtable<>();
    }    

    public Map<Integer, Procedures> getActivityProcedures() {
        return activProc;
    }

    public void setActivitiyProcedures(Map<Integer, Procedures> activProc) {
        this.activProc = activProc;
    }
    
    public void insertInActivProc(Activity a, Procedures procedures) {
        if (activProc.containsKey(a.getActivityID())) {
            throw new ActivityException("Activity and related procedures already existing"); 
        } else {
            activProc.put(a.getActivityID(), procedures);
        }
    }
    
    public void removeFromActivProc(int id) {
        if (activProc.containsKey(id)) {
            activProc.remove(id);
        } else
            throw new ActivityException("Activity and related procedures don't exist");
    }
    
    public void removeFromActivProc(Activity a) {
        if (activProc.containsKey(a.getActivityID())) {
            activProc.remove(a.getActivityID());
        } else
            throw new ActivityException("Activity and related procedures don't exist");
    }

    public Procedures getFromActivProc(Activity a) {
        if (activProc.containsKey(a.getActivityID())) {
            return activProc.get(a.getActivityID());
        } else
            throw new ActivityException("Activity and related procedures don't exist");
    }
    
    public Procedures getFromActivProc(int id) {
        if (activProc.containsKey(id)) {
            return activProc.get(id);
        } else
            throw new ActivityException("Activity and related procedures don't exist");
    }
    
    public void modifyInActivProcProcedures(Activity a, Procedures procedures) {
        if (activProc.containsKey(a.getActivityID())) {
            this.removeFromActivProc(a);
            this.insertInActivProc(a, procedures);
        } else
            throw new ActivityException("Activity doesn't exist");
    }
    
}
