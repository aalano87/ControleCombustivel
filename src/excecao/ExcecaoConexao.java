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
public class ExcecaoConexao extends Exception {

    /**
     * Creates a new instance of <code>ExcecaoConexao</code> without detail
     * message.
     */
    public ExcecaoConexao() {
    }

    /**
     * Constructs an instance of <code>ExcecaoConexao</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcecaoConexao(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>ExcecaoConexao</code> whit the specified
     * detail message and cause.
     *
     * @param msg
     * @param cause
     */
    public ExcecaoConexao(String msg, Throwable cause) {
        super(msg, cause);
        JOptionPane.showMessageDialog(null, "Ocorreu uma falha o programa será finalizado!");
        System.exit(0);
    }
}
