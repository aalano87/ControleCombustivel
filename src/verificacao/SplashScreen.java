/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author aalano
 */
public class SplashScreen extends JWindow {

    private final int duration;

    public SplashScreen(int d) {
        duration = d;
    }

// Este é um método simples para mostrar uma tela de apresentção
// no centro da tela durante a quantidade de tempo passada no construtor
    public void showSplash() {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Configura a posição e o tamanho da janela
        int width = 490;
        int height = 115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Constrói o splash screen
        JLabel label = new JLabel("CONTROLE DE COMBUSTÍVEL", JLabel.CENTER);//new JLabel(new ImageIcon("img/sair.png"));
        JLabel copyrt = new JLabel("Copyright 2015, Alano`s Systems", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        label.setFont(new Font("Sans-Serif", Font.BOLD,30));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color oraRed = new Color(0, 90, 0, 255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));
        // Torna visível
        setVisible(true);

        // Espera ate que os recursos estejam carregados
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }
        setVisible(false);
    }

    public void showSplashAndExit() {
        showSplash();
       // System.exit(0);        
    }

   // public static void main(String[] args) {        
    // Mostra uma imagem com o título da aplicação 
    public static void chamarSplash() {
        SplashScreen splash = new SplashScreen(1500);
        splash.showSplashAndExit();
    }
}
