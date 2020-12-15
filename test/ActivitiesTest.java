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

import Activity.Site;
import Activity.Activities;
import Activity.Activity;

import Activity.Planned.PlannedBuilder;

import Activity.competence.Competence;
import Activity.competence.Competences;

/**
 *
 * @author Rosanna
 */
public class ActivitiesTest {
    
    @Test
    public void testInsertActivities() {
        String factory = "Fisciano";
        String department = "Molding";

        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
         
        Activity a2 = new PlannedBuilder().setSite(factory, department)
                .setDescription( "Checking gas implant")
                .setEstimatedTime(360)
                .setWeek(48)
                .setTypology(PlannedBuilder.Typology.Eletronic)
                .getActivity();
                
        Activity a3 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Clean oil system")
                .setEstimatedTime(480)
                .setWeek(46)
                .setTypology(PlannedBuilder.Typology.Hydraulic)
                .getActivity();
        
        Activities activ = new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        activ.insertInActivities(a3);
        assertEquals(3, activ.getActivities().size());
    }
    
    @Test(expected=RuntimeException.class) 
    public void testExcInsertActivities() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setInterruptible(true)
                .setWeek(51)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a1);
    }
    
    @Test
    public void testRemoveActivities() {
        String factory = "Fisciano";
        String department = "Molding";
 
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
         
        Activity a2 = new PlannedBuilder().setSite(factory, department)
                .setDescription( "Checking gas implant")
                .setEstimatedTime(360)
                .setWeek(48)
                .setTypology(PlannedBuilder.Typology.Eletronic)
                .getActivity();
                
        Activity a3 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Clean oil system")
                .setEstimatedTime(480)
                .setWeek(46)
                .setTypology(PlannedBuilder.Typology.Hydraulic)
                .getActivity();
        
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        activ.insertInActivities(a3);
        
        activ.removeFromActivities(a3.getActivityID());
        assertEquals(2, activ.getActivities().size());
    }
        
    @Test(expected=RuntimeException.class) 
    public void testExcRemoveActivities() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        
        Activities activ =new Activities();
        activ.removeFromActivities(a1.getActivityID());
    }
    
    @Test
    public void testGetActivities() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
         
        Activity a2 = new PlannedBuilder().setSite(factory, department)
                .setDescription( "Checking gas implant")
                .setEstimatedTime(360)
                .setWeek(48)
                .setTypology(PlannedBuilder.Typology.Eletronic)
                .getActivity();
                
        Activity a3 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Clean oil system")
                .setEstimatedTime(480)
                .setWeek(46)
                .setTypology(PlannedBuilder.Typology.Hydraulic)
                .getActivity();
        
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        activ.insertInActivities(a3);
        
        Activity a4 = activ.getFromActivities(a3.getActivityID());
        assertEquals(a4.getActivityID(), a3.getActivityID());
    }
    
    @Test (expected=RuntimeException.class)
    public void testExcGetActivities() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
         
        Activity a2 = new PlannedBuilder().setSite(factory, department)
                .setDescription( "Checking gas implant")
                .setEstimatedTime(360)
                .setWeek(48)
                .setTypology(PlannedBuilder.Typology.Eletronic)
                .getActivity();
                
        Activity a3 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Clean oil system")
                .setEstimatedTime(480)
                .setWeek(46)
                .setTypology(PlannedBuilder.Typology.Hydraulic)
                .getActivity();
        
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.insertInActivities(a2);
        Activity a4 = activ.getFromActivities(a3.getActivityID());
    }
    
    @Test
    public void testModifyActivitiesDescr() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        
        String descr = "Checking gas implant";
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesDescr(a1.getActivityID(), descr);
        assertEquals(descr, activ.getFromActivities(a1.getActivityID()).getDescription());
    }
    
    @Test
    public void testModifyActivitiesTime() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        
        int time = 800;
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesTime(a1.getActivityID(), time);
        assertEquals(time, activ.getFromActivities(a1.getActivityID()).getEstimatedTime());
    }
    
    @Test
    public void testModifyActivitiesWeek() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        
        int week = 20;
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesWeek(a1.getActivityID(), week);
        assertEquals(week, activ.getFromActivities(a1.getActivityID()).getWeek());
    }
    
    @Test
    public void testExcModifyActivitiesWeek() {
        String factory = "Fisciano";
        String department = "Molding";
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        int week = 0;
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesWeek(a1.getActivityID(), week);
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        assertEquals(date.get(weekFields.weekOfWeekBasedYear()), activ.getFromActivities(a1.getActivityID()).getWeek());
    }
    
    @Test
    public void testExcModifyActivitiesComp() {
        String factory = "Fisciano";
        String department = "Molding";
        
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
        
         Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
         
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesCompetences(a1.getActivityID(), compets2);
        assertEquals(compets2, activ.getFromActivities(a1.getActivityID()).getCompetences());
    }
    
    @Test
    public void testExcModifyActivitiesSite() {
        String factory = "Fisciano";
        String department = "Molding";
        Site site = new Site("Nusco", "Carpentry");
        
        Activity a1 = new PlannedBuilder().setSite(factory, department)
                .setDescription("Replacement of robot")
                .setEstimatedTime(120)
                .setWeek(24)
                .setInterruptible(true)
                .setTypology(PlannedBuilder.Typology.Electrical)
                .getActivity();
        
        Activities activ =new Activities();
        activ.insertInActivities(a1);
        activ.modifyInActivitiesSite(a1.getActivityID(), site);
        assertEquals(site, activ.getFromActivities(a1.getActivityID()).getSite());
    }
    
}
