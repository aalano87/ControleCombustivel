/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;


import apresentacao.tablecfg.ProprietarioColumnModel;
import apresentacao.tablecfg.ProprietarioTableModel;
import apresentacao.tablecfg.VeiculoColumnModel;
import apresentacao.tablecfg.VeiculoTableModel;
import dao.GerenciadorProprietario;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import model.Proprietario;

/**
 *
 * @author aalano
 */
public class DialogRelatorioProprietario extends javax.swing.JDialog {

    /**
     * Creates new form DialogRelatorioVeiculos
     */
    GerenciadorProprietario gerenciador = new GerenciadorProprietario();

    /**
     * Creates new form DialogPesquisa
     */
    public DialogRelatorioProprietario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.configurarTabela();
    
    }

    private void configurarTabela() {
        try {
            this.setListaProprietarios(gerenciador.obterTodos());
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableProprietarios.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableProprietarios.getFontMetrics(tableProprietarios.getFont());
        tableProprietarios.setColumnModel(new ProprietarioColumnModel(fm));
        tableProprietarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setListaProprietarios(java.util.List<Proprietario> proprietarios) {
        this.tableProprietarios.setModel(new ProprietarioTableModel(proprietarios));
    }

    private void configurarTabelaProprietario() {
        try {
            this.setListaProprietarios(gerenciador.pesquisaProprietarioNome(tfPesquisa.getText().toUpperCase()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableProprietarios.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableProprietarios.getFontMetrics(tableProprietarios.getFont());
        tableProprietarios.setColumnModel(new VeiculoColumnModel(fm));
        tableProprietarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
//
//     private void configurarTabelaStatus() {
//        try {
//            this.setListaVeiculos(gv.pesquisaStatus(tfPesquisa.getText().toUpperCase()));
//        } catch (ExcecaoConexao ex) {
//            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
//            Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ExcecaoSQL ex) {
//            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
//            Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        tableProprietarios.setAutoCreateColumnsFromModel(false);
//        java.awt.FontMetrics fm = tableProprietarios.getFontMetrics(tableProprietarios.getFont());
//        tableProprietarios.setColumnModel(new VeiculoColumnModel(fm));
//        tableProprietarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    }
//    private void configurarTabelaAno() {
//        try {
//            this.setListaVeiculos(gv.pesquisaAno((int) spInicio.getValue(), (int) spFim.getValue()));
//        } catch (ExcecaoConexao ex) {
//            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
//            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ExcecaoSQL ex) {
//            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
//            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        tableAbasteciemntos.setAutoCreateColumnsFromModel(false);
//        java.awt.FontMetrics fm = tableAbasteciemntos.getFontMetrics(tableAbasteciemntos.getFont());
//        tableAbasteciemntos.setColumnModel(new VeiculoColumnModel(fm));
//        tableAbasteciemntos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btFechar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProprietarios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        rbProprietario = new javax.swing.JRadioButton();
        btPesquisar = new javax.swing.JButton();
        tfPesquisa = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });

        tableProprietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableProprietarios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFechar)
                .addGap(14, 14, 14))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btFechar)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lists de Proprietários", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 18))); // NOI18N

        buttonGroup1.add(rbProprietario);
        rbProprietario.setSelected(true);
        rbProprietario.setText("Proprietário");
        rbProprietario.setToolTipText("");
        rbProprietario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbProprietarioItemStateChanged(evt);
            }
        });

        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        tfPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfPesquisaFocusGained(evt);
            }
        });
        tfPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfPesquisaMouseReleased(evt);
            }
        });
        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btPesquisar)
                .addGap(29, 29, 29))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(rbProprietario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(rbProprietario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        try {
            if (tfPesquisa.getText().isEmpty()){
             this.configurarTabela();
            }else{
            if (rbProprietario.isSelected()) {
                this.configurarTabelaProprietario();
            }
           
            }
        } catch (NullPointerException ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(rootPane, "Campo vazio, digite algo para continuar");
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btFecharActionPerformed

    private void rbProprietarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbProprietarioItemStateChanged
        this.configurarTabela();
        if (rbProprietario.isSelected()) {
            tfPesquisa.setEditable(true);
        }
    }//GEN-LAST:event_rbProprietarioItemStateChanged

    private void tfPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPesquisaFocusGained
        tfPesquisa.setText(null);
    }//GEN-LAST:event_tfPesquisaFocusGained

    private void tfPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyPressed
        int key = evt.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            btPesquisarActionPerformed(null);
        }
    }//GEN-LAST:event_tfPesquisaKeyPressed

    private void tfPesquisaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPesquisaMouseReleased
       tfPesquisa.setText(null);
       this.configurarTabela();
    }//GEN-LAST:event_tfPesquisaMouseReleased

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
            java.util.logging.Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogRelatorioProprietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogRelatorioProprietario dialog = new DialogRelatorioProprietario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
               
                dialog.setVisible(true);
            }
        });
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rbProprietario;
    private javax.swing.JTable tableProprietarios;
    private javax.swing.JTextField tfPesquisa;
    // End of variables declaration//GEN-END:variables
}
