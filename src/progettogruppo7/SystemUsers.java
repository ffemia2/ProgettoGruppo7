/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

import java.util.LinkedList;

/**
 *
 * @author Rosanna
 */
public class SystemUsers {
    //DS<Planner>
    private LinkedList<Maintainer> listMaint = new LinkedList<Maintainer>();

    public LinkedList<Maintainer> getListMaint() {
        return listMaint;
    }

    public void setListMaint(LinkedList<Maintainer> listMaint) {
        this.listMaint = listMaint;
    }
    
}
