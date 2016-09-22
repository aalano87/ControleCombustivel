/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import conexao.Conexao;
import dao.GerenciadorAbastecimento;
import dao.GerenciadorUsuario;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import sessao.Sessao;
import verificacao.EncriptaSenha;
import verificacao.SplashScreen;

public class FrmRealizarLogin extends javax.swing.JFrame {

    private GerenciadorUsuario gu = new GerenciadorUsuario();
    private GerenciadorAbastecimento ga = new GerenciadorAbastecimento();
        private final double versaoAtual = 2.14;

    /**
     * Creates new form FrmRealizarLogin
     */
    public FrmRealizarLogin() {
        setUndecorated(false);
        if (validarVersao()) {
            initComponents();
            testarConexao();
            autenticar();
        } else {
            int x;
            x = JOptionPane.showConfirmDialog(null, "Nova versão disponível! \n"
                    + "Clique em SIM para ir a página de download ou NÃO para encerrar.", "Aviso", JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                hiperlink();
                System.exit(0);
            } else if (x == 1) {
                System.exit(0);
            }
        }
    }
    

    private void hiperlink() {
        Desktop desk = java.awt.Desktop.getDesktop();
        try {
            desk.browse(new java.net.URI("https://drive.google.com/open?id=0BzJOQVfcuMYoXzFXNWRHRWZEZ00"));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Usuário:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Senha:");

        tfLogin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLoginKeyReleased(evt);
            }
        });

        tfSenha.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSenhaKeyReleased(evt);
            }
        });

        btLogin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btLogin.setText("Login");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        btFechar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btFechar.setText("Fechar");
        btFechar.setToolTipText("");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfSenha)
                            .addComponent(tfLogin)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 127, Short.MAX_VALUE)
                        .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btFechar, btLogin});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btFechar, btLogin});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        if (autenticar()) {
            FrmTelaPrincipal telaPrincipal = new FrmTelaPrincipal();
            telaPrincipal.setVisible(true);
            telaPrincipal.setSize(this.redimensionarTela());
            telaPrincipal.setLocationRelativeTo(null);
            ga.atualizarMes();
            telaPrincipal.carregarID();
            telaPrincipal.carregarDadosVeiculoCombo();
            telaPrincipal.setExtendedState(MAXIMIZED_BOTH);
            telaPrincipal.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Nome e/ou senha incorretos!");
        }

    }//GEN-LAST:event_btLoginActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btFecharActionPerformed

    private void tfSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSenhaKeyReleased
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tfSenha.selectAll();
            btLoginActionPerformed(null);
        }
    }//GEN-LAST:event_tfSenhaKeyReleased

    private void tfLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLoginKeyReleased
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tfSenha.selectAll();
            btLoginActionPerformed(null);
        }
    }//GEN-LAST:event_tfLoginKeyReleased

    private Dimension redimensionarTela() {
        return (new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.88)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmRealizarLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRealizarLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRealizarLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRealizarLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        FrmRealizarLogin login = new FrmRealizarLogin();
        login.dispose();
        login.setLocationRelativeTo(null);
        SplashScreen.chamarSplash();
        login.setVisible(true);

    }

    
    
    private boolean autenticar() {
        boolean autenticado = false;
        ArrayList<Usuario> a = new ArrayList();
        char ConverterSenha[] = tfSenha.getPassword();
        String SenhaDigitada = new String(ConverterSenha);
        
        System.out.println(EncriptaSenha.encripta(SenhaDigitada));
        
        Usuario user = null;
        try {
            a = gu.obterTodos();
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(FrmRealizarLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(FrmRealizarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int x = 0; x < a.size(); x++) {
            if (a.get(x).getNome().equalsIgnoreCase(tfLogin.getText())
                    && a.get(x).getSenha().equalsIgnoreCase(EncriptaSenha.encripta(SenhaDigitada))) {
                autenticado = true;
                user = a.get(x);
            }
        }
        Sessao sessao = Sessao.getInstance();
        sessao.setUsuario(user);
        return autenticado;
    }

    private void testarConexao() {
        try {
            conexao.Conexao.getConnection();
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(FrmRealizarLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(FrmRealizarLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Servidor não encontrado");
            System.exit(0);
        }
    }

    public double verificaVersao() {
        try {
            String sql = "SELECT VERSAO AS VERSION FROM VERSAO";
            PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            double versao = rs.getDouble("VERSION");
            rs.close();
            stmt.close();
            return versao;
        } catch (ExcecaoConexao | ExcecaoSQL | SQLException ex) {
            Logger.getLogger(FrmRealizarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public final boolean validarVersao() {
        boolean validado = false;
        if (versaoAtual == verificaVersao()) {
            validado = true;
        }
        return validado;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables
}
