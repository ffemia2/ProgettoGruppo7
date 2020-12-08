/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import JDBC.SystemAdmin_JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import progettogruppo7.Activity;

/**
 *
 * @author User
 */
public class Availability {
    
    private Map<Integer,WeekDay> availability ;

    public Availability() {
        this.availability = new HashMap<Integer,WeekDay>();  
        for (int i=0; i<52; i++){
                availability.put(i,new WeekDay());
        }
    }
    
    public int addAvailability(int week, UserFactory.weekDay day ,int slot, int avail){ 
        return availability.get(week).addSlotAvail(day, slot, avail);
    }
    
    public void removeSlotAvailability(int week, UserFactory.weekDay day, int slot){
        availability.get(week).removeSlotAvail(day, slot);
    }
    
    public int getSlotAvailability(int week, UserFactory.weekDay day, int slot){
        return availability.get(week).getSlotAvail(day, slot);
    }
    
    public int getDayAvailability(int week, UserFactory.weekDay day){
        return availability.get(week).computeDayAvail(day);
    }

    public Map<Integer, WeekDay> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<Integer, WeekDay> availability) {
        this.availability = availability;
    }

    public String getInsertQuery(Maintainer m) {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Availability(Maintainer,Week,WeekDay,TimeSlot,Avail)");
        temp.append("values");
        for (int i = 0; i < this.availability.size(); i++ ){
            for (int j = 0; j < availability.get(i).weekDays.size(); j++){
                UserFactory.weekDay[] week = UserFactory.weekDay.values();
                          
                Map<UserFactory.weekDay,TimeSlots> time = availability.get(i).getWeekDays();
                TimeSlots te = time.get(UserFactory.weekDay.values()[j]);
                
                int len = te.timeSlots.length;
                for (int k = 0; k < len; k++){
                    
                    temp.append("('").append(m.getUsername()).append("',");
                    temp.append("").append(i).append(",");
                    temp.append("'").append(UserFactory.weekDay.values()[j]).append("',");
                    temp.append("").append(k).append(",");
                    temp.append("").append(availability.get(i).getWeekDays().get(UserFactory.weekDay.values()[j]).timeSlots[k]).append("");
                    temp.append("),");
                }
            }
        }
        if (temp.length() > 0)
            temp.deleteCharAt(temp.length()-1);
            temp.append(";");
        return temp.toString();
    }
    
    public String getUpdateQuery(Maintainer m) {
        StringBuilder temp = new StringBuilder();
        
        for (int i = 0; i < this.availability.size(); i++ ){
            for (int j = 0; j < availability.get(i).weekDays.size(); j++){
                UserFactory.weekDay[] week = UserFactory.weekDay.values();
                          
                Map<UserFactory.weekDay,TimeSlots> time = availability.get(i).getWeekDays();
                TimeSlots te = time.get(UserFactory.weekDay.values()[j]);
                
                int len = te.timeSlots.length;
                for (int k = 0; k < len; k++){
                    temp.append("update Availability set avail =");
                    temp.append("").append(availability.get(i).getWeekDays().get(UserFactory.weekDay.values()[j]).timeSlots[k]).append("");
                    temp.append("where maintainer=").append("'"+m.getUsername()+"'");
                    temp.append(" and ").append("week=").append(i);
                    temp.append("and weekday=").append("'"+UserFactory.weekDay.values()[j]+"'");
                    temp.append(" and timeslot=").append(k).append(";");
                }
            }
        }
        return temp.toString();
    }
    public String getinsertActivityQuery(Maintainer m, Activity ac) {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into MAINTAINER_ACTIVITIES (maintainer, activity) values(").append("'"+m.getUsername()+"'").append(",").append(ac.getActivityID()).append(");");
        return temp.toString();
    }
    
    public String getActivitiesQuery(Maintainer m) {
        StringBuilder temp = new StringBuilder();
        temp.append("select activity from MAINTAINER_ACTIVITIES where maintainer=").append("'").append(m.getUsername()).append("'").append(";");
        return temp.toString(); 
    }
    
    public String getCountACtivityIDQuery(Activity a) {
        StringBuilder temp = new StringBuilder();
        temp.append("select count(maintainer) as Count from MAINTAINER_ACTIVITIES where activity=").append("'").append(a.getActivityID()).append("'").append(";");
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
        private Map<UserFactory.weekDay,TimeSlots> weekDays;
        
        public WeekDay() {
            this.weekDays = new HashMap<UserFactory.weekDay,TimeSlots>();
            for (UserFactory.weekDay day : UserFactory.weekDay.values()){
                weekDays.put(day, new TimeSlots());
            }
        }
        
        public int addSlotAvail(UserFactory.weekDay day,int slot,int avail){
            return weekDays.get(day).addAvail(slot, avail);
        }
        
        public void removeSlotAvail(UserFactory.weekDay day, int slot){
            weekDays.get(day).removeAvail(slot);
        }
        
        public int getSlotAvail(UserFactory.weekDay day, int slot){
            return weekDays.get(day).getAvail(slot);
        }
        
        public int computeDayAvail(UserFactory.weekDay day){
            float avail = 0;
            float total = 0;
            int result;
            for (int slot: weekDays.get(day).timeSlots){
                avail += slot;
                total += 60;
            }
            result = (int) ((avail/total)*100);
            return result;
        }

        public Map<UserFactory.weekDay, TimeSlots> getWeekDays() {
            return weekDays;
        }

        public void setWeekDays(Map<UserFactory.weekDay, TimeSlots> weekDays) {
            this.weekDays = weekDays;
        }
                
    }
    
    
}
