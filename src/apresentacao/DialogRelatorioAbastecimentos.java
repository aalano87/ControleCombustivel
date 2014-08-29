/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import apresentacao.tablecfg.AbastecimentoColumnModel;
import apresentacao.tablecfg.AbastecimentoTableModel;
import dao.GerenciadorAbastecimento;
import dao.GerenciadorVeiculo;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.Abastecimento;
import model.Veiculo;

/**
 *
 * @author aalano
 */
public class DialogRelatorioAbastecimentos extends javax.swing.JDialog {

    /**
     * Creates new form DialogRelatorioVeiculos
     */
    GerenciadorAbastecimento ga = new GerenciadorAbastecimento();

    /**
     * Creates new form DialogPesquisa
     */
    public DialogRelatorioAbastecimentos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.configurarTabela();
        totalLitrosAbastecidos();
        rbProprietarioItemStateChanged(null);
    }

    private void configurarTabela() {
        try {
            this.setListaAbastecimentos(ga.obterTodos());
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableAbasteciemntos.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableAbasteciemntos.getFontMetrics(tableAbasteciemntos.getFont());
        tableAbasteciemntos.setColumnModel(new AbastecimentoColumnModel(fm));
        tableAbasteciemntos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setListaAbastecimentos(java.util.List<Abastecimento> abastecimentos) {
        this.tableAbasteciemntos.setModel(new AbastecimentoTableModel(abastecimentos));
    }

    private void configurarTabelaProprietario() {
        try {
            this.setListaAbastecimentos(ga.pesquisaProprietario(tfPesquisa.getText().toUpperCase()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableAbasteciemntos.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableAbasteciemntos.getFontMetrics(tableAbasteciemntos.getFont());
        tableAbasteciemntos.setColumnModel(new AbastecimentoColumnModel(fm));
        tableAbasteciemntos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarTabelaPlaca() {
        try {
            this.setListaAbastecimentos(ga.pesquisaPlaca(tfPesquisa.getText().toUpperCase()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableAbasteciemntos.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableAbasteciemntos.getFontMetrics(tableAbasteciemntos.getFont());
        tableAbasteciemntos.setColumnModel(new AbastecimentoColumnModel(fm));
        tableAbasteciemntos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarTabelaData() {
        try {
            this.setListaAbastecimentos(ga.pesquisaData(jDateChooser1.getDate(), jDateChooser2.getDate()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableAbasteciemntos.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableAbasteciemntos.getFontMetrics(tableAbasteciemntos.getFont());
        tableAbasteciemntos.setColumnModel(new AbastecimentoColumnModel(fm));
        tableAbasteciemntos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

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
        tableAbasteciemntos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        rbProprietario = new javax.swing.JRadioButton();
        rbData = new javax.swing.JRadioButton();
        btPesquisar = new javax.swing.JButton();
        tfPesquisa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        tfTotalLitros = new javax.swing.JTextField();
        rbPlaca = new javax.swing.JRadioButton();
        tfMedia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

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

        tableAbasteciemntos.setModel(new javax.swing.table.DefaultTableModel(
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
        tableAbasteciemntos.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tableAbasteciemntos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFechar)
                .addGap(14, 14, 14))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btFechar)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(rbProprietario);
        rbProprietario.setSelected(true);
        rbProprietario.setText("Proprietário");
        rbProprietario.setToolTipText("");
        rbProprietario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbProprietarioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbData);
        rbData.setText("Data");
        rbData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbDataItemStateChanged(evt);
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

        jLabel2.setText("Até");

        jLabel1.setText("Total Litros Abastecidos:");

        tfTotalLitros.setEditable(false);
        tfTotalLitros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTotalLitros.setText("jTextField2");
        tfTotalLitros.setOpaque(false);

        buttonGroup1.add(rbPlaca);
        rbPlaca.setText("Placa");
        rbPlaca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbPlacaItemStateChanged(evt);
            }
        });

        tfMedia.setEditable(false);
        tfMedia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setText("Média Km/Litros:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTotalLitros, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(rbProprietario)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbPlaca))
                        .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 443, Short.MAX_VALUE)
                                .addComponent(btPesquisar)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(rbData, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btPesquisar)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbProprietario)
                            .addComponent(rbPlaca)
                            .addComponent(rbData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfTotalLitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tfTotalLitros.setBorder(null);
        tfMedia.setBorder(null);

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
            if (rbProprietario.isSelected()) {
                this.configurarTabelaProprietario();
                totalLitrosAbastecidos();
            }
            if (rbPlaca.isSelected()) {
                this.configurarTabelaPlaca();
                totalLitrosAbastecidos();
                mediaConsumo();
            }
            if (rbData.isSelected()) {
                if (jDateChooser1.getDate().getTime() > jDateChooser2.getDate().getTime()) {
                    JOptionPane.showMessageDialog(null, "Data início deve ser menor ou igual a data final");
                    jDateChooser1.setDate(null);
                    jDateChooser2.setDate(null);
                } else {
                    this.configurarTabelaData();
                    totalLitrosAbastecidos();
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

    private void rbDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbDataItemStateChanged
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        if (rbData.isSelected()) {
            jDateChooser1.setEnabled(true);
            jDateChooser2.setEnabled(true);
            tfPesquisa.setEnabled(false);
            totalLitrosAbastecidos();
        }
    }//GEN-LAST:event_rbDataItemStateChanged

    private void rbProprietarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbProprietarioItemStateChanged
        this.configurarTabela();
        if (rbProprietario.isSelected()) {
            tfPesquisa.setEnabled(true);
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
        }
        totalLitrosAbastecidos();
        tfPesquisa.setText(null);
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

    private void rbPlacaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbPlacaItemStateChanged
        this.configurarTabela();
        if (rbPlaca.isSelected()) {
            tfPesquisa.setEnabled(true);
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
        }
        totalLitrosAbastecidos();
        tfPesquisa.setText(null);
        tfMedia.setText(null);
    }//GEN-LAST:event_rbPlacaItemStateChanged

    private void tfPesquisaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPesquisaMouseReleased
        tfPesquisa.setText(null);
        tfMedia.setText(null);
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
            java.util.logging.Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogRelatorioAbastecimentos dialog = new DialogRelatorioAbastecimentos(new javax.swing.JFrame(), true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rbData;
    private javax.swing.JRadioButton rbPlaca;
    private javax.swing.JRadioButton rbProprietario;
    private javax.swing.JTable tableAbasteciemntos;
    private javax.swing.JTextField tfMedia;
    private javax.swing.JTextField tfPesquisa;
    private javax.swing.JTextField tfTotalLitros;
    // End of variables declaration//GEN-END:variables

    private void totalLitrosAbastecidos() {
        try {
            if (tfPesquisa.getText().isEmpty()) {
                tfTotalLitros.setText(String.valueOf(ga.totalLitros()));
            } else if (rbProprietario.isSelected()) {
                tfTotalLitros.setText(String.valueOf(ga.totalLitrosProprietario(tfPesquisa.getText())));
            } else if (rbPlaca.isSelected()) {
                tfTotalLitros.setText(String.valueOf(ga.totalLitrosPlaca(tfPesquisa.getText())));
            }
            if (rbData.isSelected() && jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {
                tfTotalLitros.setText(String.valueOf(ga.totalLitrosData(jDateChooser1.getDate(), jDateChooser2.getDate())));
            }
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mediaConsumo() {
        GerenciadorVeiculo gv = new GerenciadorVeiculo();
        try {
            Veiculo aux = gv.obterVeiculo(tfPesquisa.getText());
            NumberFormat nf = new DecimalFormat("0.00");
            if (aux.getTipo().equals("Máquina")) {
                tfMedia.setText(nf.format(ga.MediaHS(tfPesquisa.getText())));
            } else if (aux.getTipo().equals("Caminhão")) {
                tfMedia.setText(nf.format(ga.MediaKM(tfPesquisa.getText())));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(DialogRelatorioAbastecimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
