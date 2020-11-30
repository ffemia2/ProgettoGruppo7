/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import org.junit.*;
import static org.junit.Assert.*;
import progettogruppo7.Activities;
import progettogruppo7.Activity;
import progettogruppo7.Competence;
import progettogruppo7.Competences;
import progettogruppo7.Planned;

/**
 *
 * @author Rosanna
 */
public class ActivitiesTest {
    
    @Test
    public void testInsertActivities() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        Activity a2 = new Planned(2, "Checking gas implant", 360, false, 48);
        Activity a3 = new Planned(3, "Clean oil system", 480, true, 46);
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        activ.insertInActivities(a3);
        assertEquals(3, activ.getActivities().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcInsertActivities() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a1);
    }
    
    @Test
    public void testRemoveActivities() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        Activity a2 = new Planned(2, "Checking gas implant", 360, false, 48);
        Activity a3 = new Planned(3, "Clean oil system", 480, true, 46);
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        activ.insertInActivities(a3);
        activ.removeFromActivities(a3);
        assertEquals(2, activ.getActivities().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcRemoveActivities() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        Activities activ =new Activities();
        activ.removeFromActivities(a1);
    }
    
    @Test
    public void testGetActivities() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        Activity a2 = new Planned(2, "Checking gas implant", 360, false, 48);
        Activity a3 = new Planned(3, "Clean oil system", 480, true, 46);
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        activ.insertInActivities(a3);
        Activity a4 = activ.getFromActivities(a3);
        assertEquals(a4.getActivityID(), a3.getActivityID());
    }
    
    @Test (expected=RuntimeException.class)
    public void testExcGetActivities() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        Activity a2 = new Planned(2, "Checking gas implant", 360, false, 48);
        Activity a3 = new Planned(3, "Clean oil system", 480, true, 46);
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        Activity a4 = activ.getFromActivities(a3);
    }
    
    @Test
    public void testModifyActivitiesDescr() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        String descr = "Checking gas implant";
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesDescr(a1, descr);
        assertEquals(descr, activ.getFromActivities(a1).getDescription());
    }
    
    @Test
    public void testModifyActivitiesTime() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        int time = 800;
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesTime(a1, time);
        assertEquals(time, activ.getFromActivities(a1).getEstimatedTime());
    }
    
    @Test
    public void testModifyActivitiesWeek() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        int week = 20;
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesWeek(a1, week);
        assertEquals(week, activ.getFromActivities(a1).getWeek());
    }
    
    @Test
    public void testExcModifyActivitiesWeek() {
        Activity a1 = new Planned(1, "Replacement of robot", 120, true, 51);
        int week = 0;
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesWeek(a1, week);
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        assertEquals(date.get(weekFields.weekOfWeekBasedYear()), activ.getFromActivities(a1).getWeek());
    }
    
    @Test
    public void testExcModifyActivitiesComp() {
        Competence comp1 = new Competence("Doti analitiche e problem solving");
        Competence comp2 = new Competence("Doti comunicative");
        Competence comp3 = new Competence("Rapidità d'intervento");
        Competence comp4 = new Competence("Affidabilità e flessibilità");
        Competences compets = new Competences();
        compets.insertCompetence(comp1);
        compets.insertCompetence(comp2);
        compets.insertCompetence(comp3);
        Competences compets2 = new Competences();
        compets2.insertCompetence(comp4);
        Activity a1 = new Planned(1, compets, "Replacement of robot", 120, true, 51);
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesCompetences(a1, compets2);
        assertEquals(compets2, activ.getFromActivities(a1).getCompetences());
    }
    
}
