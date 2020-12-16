/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity;

import Activity.Procedure.Procedure;

import Activity.material.Materials;

import Activity.competence.Competences;

import java.time.LocalDate;

import java.time.temporal.WeekFields;

import java.util.Locale;

import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author Rosanna
 */
public abstract class Activity implements ActivityInterface {
   
    // required
    private Site site;
    private ActivityBuilder.Typology typology;
    private String description;
    private int week;
    private int estimatedTime;
    //optional
    private boolean assigned;
    private int activityID;
    private Procedure procedure;
    private Materials materials;
    private Competences competences;
    private boolean interruptible;
    //static
    private static final AtomicInteger count = new AtomicInteger();
    
    
    public Activity(Site site,  ActivityBuilder.Typology typology, String description, int week, int estimatedTime, boolean assigned, Materials materials, Competences competences, boolean interruptible, Procedure procedure) {
        this.activityID = count.incrementAndGet();
        this.site = site;
        this.typology = typology;
        this.estimatedTime = estimatedTime;
        this.description = description;
        
         if (week > 0 && week < 53) {
            this.week = week;
        } else {
            LocalDate date = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            this.week = date.get(weekFields.weekOfWeekBasedYear());
        }
        this.interruptible = interruptible;
        this.assigned = assigned;
        this.materials = materials;
        this.competences = competences;
        this.procedure = procedure;
    }

    
    
    public Site getSite() {
        return site;
    }

    public  ActivityBuilder.Typology getTypology() {
        return typology;
    }

    public String getDescription() {
        return description;
    }

    public int getWeek() {
        return week;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public int getActivityID() {
        return activityID;
    }

    public Materials getMaterials() {
        return materials;
    }

    public Competences getCompetences() {
        return competences;
    }

    public boolean isInterruptible() {
        return interruptible;
    }
    
    public static void setInitialCount(int count){
        Activity.count.getAndSet(count);
    }
    
    public void setActivityID(int activityID) {
        count.decrementAndGet();
        this.activityID = activityID;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    @Override
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
    
    @Override
    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public void setTypology( ActivityBuilder.Typology typology) {
        this.typology = typology;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setWeek(int week) {
         if (week > 0 && week < 53) {
            this.week = week;
        } else {
            LocalDate date = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            this.week = date.get(weekFields.weekOfWeekBasedYear());
        }
    }

    @Override
    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    @Override
    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    @Override
    public void setCompetences(Competences competences) {
        this.competences = competences;
    }

    @Override
    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Activity other = (Activity) obj;
        
        return this.activityID == other.activityID;
    }

    @Override
    public String toString() {
        return this.activityID + " - " + site.toString() + "  " + typology + " " + week + " " + estimatedTime  + " min";
    }
    
    
    
    
    


   
    
    
 
    
    

    

    
    
    
}
