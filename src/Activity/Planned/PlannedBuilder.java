/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Planned;

import Activity.Activity;
import Activity.ActivityBuilder;
import Activity.Site;

import Activity.Procedure.Procedure;

import Activity.competence.Competences;

import Activity.material.Materials;

/**
 *
 * @author Grazia D'Amore
 */
public class PlannedBuilder extends ActivityBuilder {
    
    private Site site;
    private Typology typology;
    private int estimatedTime;
    private boolean interruptible;
    private String description;
    
    private int week;
    private boolean assigned;
    private Materials materials;
    private Competences competences;
    private Procedure procedure;
    
    

    
    public PlannedBuilder() {
        this.procedure = new Procedure("", new Competences());
        this.materials = new Materials();
        this.competences = new Competences();
        this.assigned = false;
        this.week = 0;
    }

    
    @Override
    public ActivityBuilder setWeek(int week){
        this.week = week ;
        return this;
    }
    
    @Override
    public ActivityBuilder setSite(String site, String department){
        Site s = new Site(site, department);
        this.site = s;
        return this;
    }
   
    @Override
    public ActivityBuilder setAssigned(boolean assigned){
        this.assigned = assigned;
        return this;
    }
    
    @Override
    public ActivityBuilder setInterruptible(boolean interr){
        this.interruptible = interr;
        return this;
    }
    
    @Override
    public ActivityBuilder setEstimatedTime(int time){
        this.estimatedTime = time;
        return this;
    }
    
    @Override
    public ActivityBuilder setDescription(String desc){
        this.description = desc;
        return this;
    }
    
    @Override
    public ActivityBuilder setCompetences(Competences comp){
        this.competences = comp;
        return this;
    }
    
    @Override
    public ActivityBuilder setMaterials(Materials mate){
        this.materials = mate;
        return this;
    }
    
    @Override
    public ActivityBuilder setTypology(Typology typo){
        this.typology = typo;
        return this;
    }
    
    @Override
    public ActivityBuilder setProcedures(Procedure procedure){
        this.procedure = procedure;
        return this;
    }
    
    public void reset(){
        this.procedure = new Procedure("", new Competences());
        this.materials = new Materials();
        this.competences = new Competences();
        this.assigned = false;
        this.week = 0;
        
        this.site = null;
        this.typology = null;
        this.estimatedTime = 0;
        this.interruptible = false;
        this.description = "";
    }
    
    @Override
    public Activity getActivity() {
        Planned p = new Planned(assigned,interruptible,estimatedTime, week, description, site, materials, typology, competences, procedure);       
        this.reset();
        return p;
    }
    
   
    
    
    
    
}
