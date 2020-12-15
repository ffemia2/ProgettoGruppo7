/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity;

import Activity.competence.Competences;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Rosanna
 */
public class Activities {
    private Map<Integer,Activity> activities;

    public Activities() {
        this.activities = new Hashtable<>();
    }

    public Map<Integer, Activity> getActivities() {
        return activities;
    }
    
    public Activity insertInActivities(Activity a){
        if (activities.containsKey(a.getActivityID())) {
            throw new ActivityException("Activity already existing"); 
        } else {
            activities.put(a.getActivityID(), a);
            return activities.get(a.getActivityID());
        }
    }
    
    public void removeFromActivities(int id){
        if (activities.containsKey(id)) {
            activities.remove(id);
        } else
            throw new ActivityException("Activity doesn't exists");
    }

    public Activity getFromActivities(int id){
        if (activities.containsKey(id)) {
            return activities.get(id);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public LinkedList<Integer> getWeeks(){
        LinkedList<Integer> l=new LinkedList<>();
        for (Activity a: activities.values())
            l.add(a.getWeek());
        return l;
    }
    
    public void modifyInActivitiesDescr(int id, String description) {
        if (activities.containsKey(id)) {
            this.activities.get(id).setDescription(description);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesTime(int id, int estimated) {
        if (activities.containsKey(id)) {
            this.activities.get(id).setEstimatedTime(estimated);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesWeek(int id, int week) {
        if (activities.containsKey(id)) {
            this.activities.get(id).setWeek(week);
            
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesCompetences(int id, Competences comp) {
        if (activities.containsKey(id)) {
            this.activities.get(id).setCompetences(comp);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesSite(int id, Site site) {
        if (activities.containsKey(id)) {
            this.activities.get(id).setSite(site);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
}
