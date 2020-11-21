/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

import java.util.LinkedList;

/**
 *
 * @author Francesco Femia
 */
public class Procedures {
    private LinkedList<Procedure> procedureList;

    public Procedures() {
        this.procedureList = new LinkedList<>();
    }

    public LinkedList<Procedure> getProcedureList() {
        return procedureList;
    }

    public void insertInProcedureList(Procedure p) {
        this.procedureList.add(p);
    }
    
}
