/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import progettogruppo7.Exceptions.ActivityException;

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

    public Activity getFromActivities(Activity a){
        if (activities.containsKey(a.getActivityID())) {
            return activities.get(a.getActivityID());
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
    
    public void modifyInActivitiesDescr(Activity a, String description) {
        if (activities.containsKey(a.getActivityID())) {
            this.activities.get(a.getActivityID()).setDescription(description);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesTime(Activity a, int estimated) {
        if (activities.containsKey(a.getActivityID())) {
            this.activities.get(a.getActivityID()).setEstimatedTime(estimated);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesWeek(Activity a, int week) {
        if (activities.containsKey(a.getActivityID())) {
            this.activities.get(a.getActivityID()).setWeek(week);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesCompetences(Activity a, Competences comp) {
        if (activities.containsKey(a.getActivityID())) {
            this.activities.get(a.getActivityID()).setCompetences(comp);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
    public void modifyInActivitiesSite(Activity a, Site site) {
        if (activities.containsKey(a.getActivityID())) {
            this.activities.get(a.getActivityID()).setSite(site);
        } else
            throw new ActivityException("Activity doesn't exists");
    }
    
}
