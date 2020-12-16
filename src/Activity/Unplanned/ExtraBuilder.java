/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Unplanned;


import Activity.Activity;
import Activity.ActivityBuilder;
import Activity.Procedure.Procedure;
import Activity.competence.Competences;
import Activity.Site;
import Activity.material.Materials;

/**
 *
 * @author Grazia D'Amore
 */
public class ExtraBuilder extends ActivityBuilder {
    
    private Site site;
    private Typology typology;
    private int estimatedTime;
    private String description;
    
    private int week;
    private boolean assigned;
    private Materials materials;
    private Competences competences;
    private Procedure procedure;

    public ExtraBuilder() {
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
    
    public void reset(){
        this.procedure = new Procedure("", new Competences());
        this.materials = new Materials();
        this.competences = new Competences();
        this.assigned = false;
        this.week = 0;
        
        this.site = null;
        this.typology = null;
        this.estimatedTime = 0;
        this.description = "";
    }
    
    @Override
    public Activity getActivity(){
        Extra e = new Extra(site, typology, description, week, estimatedTime, assigned, materials, competences, procedure);
        this.reset();
        return e;
    }
    
}
