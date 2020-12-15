/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity.Procedure;

/**
 *
 * @author Francesco Femia
 */
public class ProcedureDoesntExistException extends RuntimeException {

    /**
     * Creates a new instance of <code>NewException</code> without detail
     * message.
     */
    public ProcedureDoesntExistException() {
    }

    /**
     * Constructs an instance of <code>NewException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ProcedureDoesntExistException(String msg) {
        super(msg);
    }
}
