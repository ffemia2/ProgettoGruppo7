/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity;

import Activity.Procedure.Procedure;

import Activity.competence.Competences;

import Activity.material.Materials;

/**
 *
 * @author Grazia D'Amore
 */
public abstract class ActivityBuilder {
    
    public enum Typology {Electrical,Eletronic,Hydraulic,Mechanical};
    
    public ActivityBuilder setWeek(int week){return null;}
    public ActivityBuilder setSite(String site, String department){return null;}
    public ActivityBuilder setAssigned(boolean assigned){return null;}
    public ActivityBuilder setInterruptible(boolean interr){return null;}
    public ActivityBuilder setEstimatedTime(int time){return null;}
    public ActivityBuilder setDescription(String desc){return null;}
    public ActivityBuilder setCompetences(Competences comp){return null;}
    public ActivityBuilder setMaterials(Materials mate){return null;}
    public ActivityBuilder setTypology(Typology typo){return null;}
    public ActivityBuilder setProcedures(Procedure procedure){return null;}
    
    public abstract Activity getActivity();
}
