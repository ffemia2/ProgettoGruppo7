/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7;

/**
 *
 * @author Francesco Femia
 */
public class ProgettoGruppo7 {

    public static void main(String[] args) {
        Procedures p=new Procedures();
        p.insertInProcedureMap(new Procedure("manutenzione"));
        p.deleteInProcedureMap(new Procedure("manutenzione"));  
    }
          
}
