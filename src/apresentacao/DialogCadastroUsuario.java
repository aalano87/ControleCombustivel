/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import dao.GerenciadorUsuario;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import model.Usuario;
import verificacao.EncriptaSenha;
import verificacao.Redimensionar;
import static verificacao.Redimensionar.redimensionarTela;
import verificacao.ValidarCPF;

/**
 *
 * @author aalano
 */
public class DialogCadastroUsuario extends javax.swing.JDialog {

    private Usuario obj = new Usuario();
    private GerenciadorUsuario gerenciador = new GerenciadorUsuario();

    /**
     * Creates new form CadastroVeiculo
     */
    public DialogCadastroUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            tfCodigo.setText(String.valueOf(gerenciador.lastID()));
        } catch (SQLException ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        limparCampos();
        ImageIcon icone = criarImageIcon("/icon/logo2.jpg", "");
        lbLogo.setIcon(icone);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        tfNome = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfSenha = new javax.swing.JPasswordField();
        tfCpf = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Código:");

        tfCodigo.setEditable(false);

        jLabel4.setText("Nome:");

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btSalvar);

        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btAtualizar);

        btRemover.setText("Remover");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });
        jPanel1.add(btRemover);

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btCancelar);

        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btFechar);

        jLabel2.setText("Cpf:");

        jLabel3.setText("Senha:");

        tfSenha.setText("jPasswordField1");

        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCpfFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btPesquisar))
                            .addComponent(tfNome)
                            .addComponent(tfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(tfCpf)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Cadastro de Usuário");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/relatorio.jpg"))); // NOI18N
        jMenu1.setText("Relatório");
        jMenu1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jMenuItem1.setText("Usuários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (verificaCampos()) {
            JOptionPane.showMessageDialog(this, "Campos vazios preencha para prosseguir");
        } else {
            obj = this.getDados();
            if (verificaCPF(obj.getCpf()) == false) {
                try {
                    gerenciador.inserir(obj);
                    this.limparCampos();
                } catch (ExcecaoConexao ex) {
                    JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados.\n"
                            + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcecaoSQL ex) {
                    JOptionPane.showMessageDialog(this, "Falha durante a gravação do registro.\n"
                            + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(rootPane, "Dados salvos com sucesso!");
                btSalvar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário já cadastrado.");
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        tfCodigo.setText(null);
        try {
            obj = gerenciador.obterUsuario(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite um número para pesquisar")));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados.\n"
                    + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Falha na pesquisa.\n"
                    + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Digite um número para pesquisar.");
        }
        try {
            this.setDados(obj);
            btAtualizar.setEnabled(true);
        } catch (NullPointerException ex) {
            return;
        }
        btSalvar.setEnabled(false);
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        if (verificaCampos()) {
            JOptionPane.showMessageDialog(this, "Campos vazios preencha para prosseguir");
        } else {
            obj = this.getDados();
            System.out.println(obj.getSenha());
            try {
                gerenciador.atualizar(obj);
                this.limparCampos();
            } catch (ExcecaoConexao ex) {
                JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados.\n"
                        + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExcecaoSQL ex) {
                JOptionPane.showMessageDialog(this, "Falha durante a atualização dos dados no Banco de Dados.\n"
                        + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Dados Atualizados com sucesso!");
            btSalvar.setEnabled(true);
        }
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btFecharActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        obj = this.getDados();
        try {
            if (JOptionPane.showConfirmDialog(this,
                    "Remover o usuário " + obj.getNome(),
                    "Cadastro de Usuário", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == 0) {
                gerenciador.remover(obj);
                this.limparCampos();
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
            }
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados.\n"
                    + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Falha na remoção deste registro.\n"
                    + "Msg.: " + ex.getMessage(), "Cadastro de Usuário", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRemoverActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DialogRelatorioUsuario ru = new DialogRelatorioUsuario(null, true);
        ru.setSize(Redimensionar.redimensionarTela());
        ru.setLocationRelativeTo(null);
        ru.dispose();
        ru.setUndecorated(true);
        ru.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tfCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCpfFocusLost
        String cpf = tfCpf.getText().replace(".", "");
        cpf = cpf.replace("-", "");
        if (ValidarCPF.validaCPF(cpf)) {
            btSalvar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "CPF inválido verifique!!!");
            tfCpf.requestFocus();
        }

    }//GEN-LAST:event_tfCpfFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCadastroUsuario dialog = new DialogCadastroUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setSize(redimensionarTela());
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        try {
            tfCodigo.setText(String.valueOf(gerenciador.lastID()));
        } catch (SQLException ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfNome.setText(null);
        tfSenha.setText(null);
        tfCpf.setText(null);
        btSalvar.setEnabled(false);
        btAtualizar.setEnabled(false);
    }

    private Usuario getDados() {
        if (obj == null) {
            obj = new Usuario();
        }
        obj.setNome(tfNome.getText());
        char ConverterSenha[] = tfSenha.getPassword();
        String SenhaDigitada = new String(ConverterSenha);
        String cpf = tfCpf.getText().replace(".", "");
        cpf = cpf.replace("-", "");
        obj.setCpf(cpf);
        obj.setSenha(EncriptaSenha.encripta(SenhaDigitada));
        return obj;
    }

    private void setDados(Usuario u) {
        this.tfCodigo.setText(String.valueOf(u.getIdusuario()));
        this.tfNome.setText(u.getNome());
        this.tfCpf.setText(u.getCpf());
        System.out.println(u.getCpf());
        this.tfSenha.setText(u.getSenha());
    }

    private boolean verificaCPF(String cpf) {
        try {
            if (gerenciador.ValorExistente(cpf) == false) {
                return false;
            }
        } catch (ExcecaoSQL | ExcecaoConexao | SQLException ex) {
            Logger.getLogger(DialogCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ImageIcon criarImageIcon(String caminho, String descricao) {
        java.net.URL imgURL = getClass().getResource(caminho);
        if (imgURL != null) {
            return new ImageIcon(imgURL, descricao);
        } else {
            System.err.println("Não foi possível carregar o arquivo de imagem: " + caminho);
            return null;
        }
    }

    private boolean verificaCampos() {
        if (tfCpf.getText().isEmpty() || tfSenha.getText().isEmpty() || tfNome.getText().isEmpty()) {
            return true;
        }
        return false;
    }
    
    
    protected JRootPane createRootPane(){
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent){
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", actionListener);
        
        return rootPane;
    }

}
