/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacao;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author aalano
 */
public class Redimensionar {

    public static Dimension redimensionarTela() {
        if (System.getProperties().contains("Mac OS X")) {
            return (new Dimension(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    (int) ((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) * 0.895))); //MacOS   
        } else {
            return (new Dimension(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    (int) ((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) * 1))); //Windows
        }
    }
}
