/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Exceptions;

/**
 *
 * @author User
 */
public class UsernameException extends RuntimeException {

    public UsernameException() {
    }
    
    /**
     * Constructs an instance of <code>NewException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UsernameException(String msg) {
        super(msg);
    }
}
