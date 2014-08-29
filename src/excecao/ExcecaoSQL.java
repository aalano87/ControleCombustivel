/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excecao;

import javax.swing.JOptionPane;

/**
 *
 * @author aalano
 */
public class ExcecaoSQL extends Exception {

    /**
     * Creates a new instance of
     * <code>ExcecaoSQL</code> without detail message.
     */
    public ExcecaoSQL() {
    }

    /**
     * Constructs an instance of
     * <code>ExcecaoSQL</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcecaoSQL(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of
     * <code>ExcecaoSQL</code> with the specified detail message and cause.
     * @param msg
     * @param cause 
     */
    public ExcecaoSQL(String msg, Throwable cause) {
        super(msg, cause);
        //JOptionPane.showMessageDialog(null, "Ocorreu uma falha o programa será finalizado!");
       // System.exit(0);
    }
}
