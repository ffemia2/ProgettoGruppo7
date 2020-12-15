/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.maintainer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Grazia D'Amore
 */
public class Availability {
    public enum week {MON, TUE, WED, THU, FRI, SAT, SUN}; 
    private Map<Integer,WeekDay> availability ;

    public Availability() {
        this.availability = new HashMap<Integer,WeekDay>();  
        for (int i=0; i<52; i++){
                availability.put(i,new WeekDay());
        }
    }
    
    public week[] getEnum(){
        return week.values();
    }
    
    public int addAvailability(int week, week day ,int slot, int avail){ 
        return availability.get(week).addSlotAvail(day, slot, avail);
    }
    
    public void removeSlotAvailability(int week, week day, int slot){
        availability.get(week).removeSlotAvail(day, slot);
    }
    
    public int getSlotAvailability(int week, week day, int slot){
        return availability.get(week).getSlotAvail(day, slot);
    }
    
    public int getDayAvailability(int week, week day){
        return availability.get(week).computeDayAvail(day);
    }

    public Map<Integer, WeekDay> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<Integer, WeekDay> availability) {
        this.availability = availability;
    }

    public String getUpdateQuery(String username) {
        StringBuilder temp = new StringBuilder();
       
        for (int i = 0; i < this.availability.size(); i++ ){
            for (int j = 0; j < availability.get(i).days.size(); j++){                          
                Map<week,TimeSlots> time = availability.get(i).getdays();
                //TimeSlots te = time.get(AbstractUserFactory.days.values()[j]);
                int len = time.get(week.values()[j]).timeSlots.length;
                
                for (int k = 0; k < len; k++){
                    temp.append("UPDATE availability SET Avail = ");
                    temp.append(availability.get(i).getdays().get(week.values()[j]).timeSlots[k]);
                    temp.append("WHERE ");
                    temp.append("MAINTAINER = ").append("'").append(username).append("'");
                    temp.append(" AND WEEK = ").append(i).append("");
                    temp.append(" AND Weekday = ").append("'").append(week.values()[j]).append("'");
                    temp.append(" AND TIMESLOT = ").append(k).append(";");
                }
            }
        }

        return temp.toString();
    }
    
    public String getInsertQuery(String username) {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Availability(Maintainer,Week,Weekday,TimeSlot,Avail)");
        temp.append("values");
        for (int i = 0; i < this.availability.size(); i++ ){
            for (int j = 0; j < availability.get(i).days.size(); j++){                          
                Map<week,TimeSlots> time = availability.get(i).getdays();
                TimeSlots te = time.get(week.values()[j]);
                
                int len = te.timeSlots.length;
                for (int k = 0; k < len; k++){
                    
                    temp.append("('").append(username).append("',");
                    temp.append("").append(i).append(",");
                    temp.append("'").append(week.values()[j]).append("',");
                    temp.append("").append(k).append(",");
                    temp.append("").append(availability.get(i).getdays().get(week.values()[j]).timeSlots[k]).append("");
                    temp.append("),");
                }
            }
        }
        if (temp.length() > 0){
            temp.deleteCharAt(temp.length()-1);
            temp.append(";");
        }
        return temp.toString();
    }
    
    

    private static class TimeSlots {
        private int[] timeSlots ;
        
        public TimeSlots() {
            this.timeSlots = new int[]{60,60,60,60,60,60,60,60};
        }
        
        public int addAvail(int slot, int avail){
            if (slot > 7)
                return 0;
            timeSlots[slot] = avail;
            return timeSlots[slot];
        }
        
        public void removeAvail(int slot){
            timeSlots[slot] = 0;
        }
        
        public int getAvail(int slot){
            return timeSlots[slot];
        }

        public int[] getTimeSlots() {
            return timeSlots;
        }

        public void setTimeSlots(int[] timeSlots) {
            this.timeSlots = timeSlots;
        }
        
    }

    public static class WeekDay {
        
        private Map<week,TimeSlots> days;
        
        public WeekDay() {
            this.days = new HashMap<>();
            
            for (week day : week.values()){
                days.put(day, new TimeSlots());
            }
        }
        
        public int addSlotAvail(week day,int slot,int avail){
            return days.get(day).addAvail(slot, avail);
        }
        
        public void removeSlotAvail(week day, int slot){
            days.get(day).removeAvail(slot);
        }
        
        public int getSlotAvail(week day, int slot){
            return days.get(day).getAvail(slot);
        }
        
        public int computeDayAvail(week day){
            float avail = 0;
            float total = 0;
            int result;
            for (int slot: days.get(day).timeSlots){
                avail += slot;
                total += 60;
            }
            result = (int) ((avail/total)*100);
            return result;
        }

        public Map<week, TimeSlots> getdays() {
            return days;
        }

        public void setdays(Map<week, TimeSlots> days) {
            this.days = days;
        }
                
    }
    
    
}
