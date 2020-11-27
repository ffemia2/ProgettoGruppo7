/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 *
 * @author Rosanna
 */
public abstract class Activity {
    private int activityID;
    //site
    //typology
    //competences
    private String description;
    private int estimatedTime;
    private boolean interruptible;
    private int week; 

    
    public Activity(int activityID, String description, int estimatedTime, boolean interruptible, int week) {
        //TODO: al costruttore mancano parametri
        this.activityID = activityID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.interruptible = interruptible;
        //se il valore di week non ha significato assegno il valore della settimana corrente
        if (week > 0 && week < 53) {
            this.week = week;
        } else {
            LocalDate date = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            week = date.get(weekFields.weekOfWeekBasedYear());
        }
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isInterruptible() {
        return interruptible;
    }

    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        if (week > 0 && week < 53) {
            this.week = week;
        } else {
            LocalDate date = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            week = date.get(weekFields.weekOfWeekBasedYear());
            this.week = week;
        }
    }

    @Override
    public String toString() {
        return "activityID=" + activityID + ", description=" + description + ", estimatedTime=" + estimatedTime + ", interruptible=" + interruptible + ", week=" + week;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.activityID;
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
        if (this.activityID != other.activityID) {
            return false;
        }
        return true;
    }
    
}
