/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import apresentacao.tablecfg.EntradaColumnModel;
import apresentacao.tablecfg.EntradaTableModel;
import dao.GerenciadorEntradaCombustivel;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.EntradaCombustivel;

/**
 *
 * @author aalano
 */
public class DialogRelatorioEntradas extends javax.swing.JDialog {

    /**
     * Creates new form DialogRelatorioVeiculos
     */
    GerenciadorEntradaCombustivel gerenciador = new GerenciadorEntradaCombustivel();

    /**
     * Creates new form DialogPesquisa
     */
    public DialogRelatorioEntradas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.configurarTabela();
        totalLitrosComprados();
        rbFornecedorItemStateChanged(null);
    }

    private void configurarTabela() {
        try {
            this.setListaEntradas(gerenciador.obterTodos());
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableEntradas.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableEntradas.getFontMetrics(tableEntradas.getFont());
        tableEntradas.setColumnModel(new EntradaColumnModel(fm));
        tableEntradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setListaEntradas(java.util.List<EntradaCombustivel> entradas) {
        this.tableEntradas.setModel(new EntradaTableModel(entradas));
    }

    private void configurarTabelaFornecedor() {
        try {
            this.setListaEntradas(gerenciador.pesquisaFornecedor(tfPesquisa.getText().toUpperCase()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableEntradas.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableEntradas.getFontMetrics(tableEntradas.getFont());
        tableEntradas.setColumnModel(new EntradaColumnModel(fm));
        tableEntradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarTabelaComprador() {
        try {
            this.setListaEntradas(gerenciador.pesquisaComprador(tfPesquisa.getText().toUpperCase()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableEntradas.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableEntradas.getFontMetrics(tableEntradas.getFont());
        tableEntradas.setColumnModel(new EntradaColumnModel(fm));
        tableEntradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarTabelaData() {
        try {
            this.setListaEntradas(gerenciador.pesquisaData(jDateChooser1.getDate(), jDateChooser2.getDate()));
        } catch (ExcecaoConexao ex) {
            JOptionPane.showMessageDialog(this, "Falha ao estabelecer a conexão com o banco de dados");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            JOptionPane.showMessageDialog(this, "Erro na recuperação dos dados.");
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableEntradas.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tableEntradas.getFontMetrics(tableEntradas.getFont());
        tableEntradas.setColumnModel(new EntradaColumnModel(fm));
        tableEntradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        tableEntradas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        rbFornecedor = new javax.swing.JRadioButton();
        rbData = new javax.swing.JRadioButton();
        btPesquisar = new javax.swing.JButton();
        tfPesquisa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        tfTotalLitros = new javax.swing.JTextField();
        rbComprador = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();

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

        tableEntradas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableEntradas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFechar)
                .addGap(14, 14, 14))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btFechar)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relatório de Entradas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 18))); // NOI18N

        buttonGroup1.add(rbFornecedor);
        rbFornecedor.setSelected(true);
        rbFornecedor.setText("Fornecedor");
        rbFornecedor.setToolTipText("");
        rbFornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbFornecedorItemStateChanged(evt);
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfPesquisaMouseClicked(evt);
            }
        });
        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyPressed(evt);
            }
        });

        jLabel2.setText("Até");

        jLabel1.setText("Total Litros Comprados:");

        tfTotalLitros.setEditable(false);
        tfTotalLitros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTotalLitros.setText("jTextField2");

        buttonGroup1.add(rbComprador);
        rbComprador.setText("Comprador");
        rbComprador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbCompradorItemStateChanged(evt);
            }
        });

        jLabel3.setText("Valor total:");

        tfTotal.setEditable(false);
        tfTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTotal.setText("jTextField2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(rbFornecedor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbComprador)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTotalLitros, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btPesquisar)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(rbData, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFornecedor)
                    .addComponent(rbData)
                    .addComponent(rbComprador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfTotalLitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tfTotalLitros.setBorder(null);
        tfTotal.setBorder(null);

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
            if (rbFornecedor.isSelected()) {
                this.configurarTabelaFornecedor();
                totalLitrosComprados();
            }
            if (rbData.isSelected()) {
                if (jDateChooser1.getDate().getTime() > jDateChooser2.getDate().getTime()) {
                    JOptionPane.showMessageDialog(null, "Data início deve ser menor ou igual a data final");
                    jDateChooser1.setDate(null);
                    jDateChooser2.setDate(null);
                } else {
                    this.configurarTabelaData();
                    totalLitrosComprados();
                }
            }
            if (rbComprador.isSelected()) {
                this.configurarTabelaComprador();
                totalLitrosComprados();
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
            totalLitrosComprados();
        }
    }//GEN-LAST:event_rbDataItemStateChanged

    private void rbFornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbFornecedorItemStateChanged
        this.configurarTabela();
        if (rbFornecedor.isSelected()) {
            tfPesquisa.setEnabled(true);
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
        }
        totalLitrosComprados();
        tfPesquisa.setText(null);
    }//GEN-LAST:event_rbFornecedorItemStateChanged

    private void tfPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPesquisaFocusGained
        tfPesquisa.setText(null);
    }//GEN-LAST:event_tfPesquisaFocusGained

    private void tfPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyPressed
        int key = evt.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            btPesquisarActionPerformed(null);
        }
    }//GEN-LAST:event_tfPesquisaKeyPressed

    private void rbCompradorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbCompradorItemStateChanged
        this.configurarTabela();
        if (rbComprador.isSelected()) {
            tfPesquisa.setEnabled(true);
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
        }
        totalLitrosComprados();
        tfPesquisa.setText(null);
    }//GEN-LAST:event_rbCompradorItemStateChanged

    private void tfPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPesquisaMouseClicked
        tfPesquisa.setText(null);
    }//GEN-LAST:event_tfPesquisaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogRelatorioEntradas dialog = new DialogRelatorioEntradas(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton rbComprador;
    private javax.swing.JRadioButton rbData;
    private javax.swing.JRadioButton rbFornecedor;
    private javax.swing.JTable tableEntradas;
    private javax.swing.JTextField tfPesquisa;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfTotalLitros;
    // End of variables declaration//GEN-END:variables

    private void totalLitrosComprados() {
        try {
            if (rbFornecedor.isSelected()) {
                if (tfPesquisa.getText().isEmpty()) {
                    tfTotalLitros.setText(String.valueOf(gerenciador.totalLitros()));
                    tfTotal.setText(NumberFormat.getCurrencyInstance().format(gerenciador.valorTotal()));
                } else {
                    tfTotalLitros.setText(String.valueOf(gerenciador.totalLitrosFornecedor(tfPesquisa.getText())));
                    tfTotal.setText(NumberFormat.getCurrencyInstance().format(gerenciador.valorFornecedor(tfPesquisa.getText())));
                }
            }
            if (rbData.isSelected()) {
                if (jDateChooser1.getDate() == null && jDateChooser2.getDate() == null) {
                    tfTotalLitros.setText(String.valueOf(gerenciador.totalLitros()));
                    tfTotal.setText(NumberFormat.getCurrencyInstance().format(gerenciador.valorTotal()));
                } else {
                    tfTotalLitros.setText(String.valueOf(gerenciador.totalLitrosData(jDateChooser1.getDate(), jDateChooser2.getDate())));
                    tfTotal.setText(NumberFormat.getCurrencyInstance().format(gerenciador.valorTotalData(jDateChooser1.getDate(), jDateChooser2.getDate())));
                }
            }
            if (rbComprador.isSelected()) {
                tfTotalLitros.setText(String.valueOf(gerenciador.totalLitrosComprador(tfPesquisa.getText())));
                tfTotal.setText(NumberFormat.getCurrencyInstance().format(gerenciador.valorComprador(tfPesquisa.getText())));
            }
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DialogRelatorioEntradas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

}
