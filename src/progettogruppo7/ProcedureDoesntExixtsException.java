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
public class ProcedureDoesntExixtsException extends RuntimeException {

    /**
     * Creates a new instance of <code>NewException</code> without detail
     * message.
     */
    public ProcedureDoesntExixtsException() {
    }

    /**
     * Constructs an instance of <code>NewException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ProcedureDoesntExixtsException(String msg) {
        super(msg);
    }
}
