/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Procedure;

import java.util.HashMap;


/**
 *
 * @author Francesco Femia
 */
public class Procedures {
    private HashMap<String, Procedure> procedureMap;

    public Procedures() {
        this.procedureMap = new HashMap<>();
    }

    public HashMap<String, Procedure> getProcedureMap() {
        return procedureMap;
    }

    public void insertInProcedureMap(Procedure p) {
        this.procedureMap.put(p.getDescription(), p);
    }
    
    public void deleteInProcedureMap(Procedure p) {
        if(this.procedureMap.keySet().contains(p.getDescription()))
            this.procedureMap.remove(p.getDescription());
        else
            throw new ProcedureDoesntExistException("Procedure not in list");
    }
    
    public Procedure getFromProcedureMap(Procedure p) {
        if(this.procedureMap.keySet().contains(p.getDescription()))
            return this.procedureMap.get(p.getDescription());
        else
            throw new ProcedureDoesntExistException("Procedure not in list");
    }
    public void modifyFromProcedureMap(Procedure p, String description) {
        Procedure proc;
        if(this.procedureMap.keySet().contains(p.getDescription())){
            proc = this.procedureMap.get(p.getDescription());
            this.deleteInProcedureMap(p);
            proc.setDescription(description);
            this.procedureMap.put(description, proc);
        }
        else
            throw new ProcedureDoesntExistException("Procedure not in list");
    }
}
