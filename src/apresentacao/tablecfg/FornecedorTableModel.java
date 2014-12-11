/*
 * AbastecimentoTableModel.java
 *
 * Created on 29 de Novembro de 2006, 20:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package apresentacao.tablecfg;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Fornecedor;

/**
 *
 * @author aalano
 *
 * Esta classe permite a exibição dos dados de uma List<Aluno>
 * em um JTable.
 */
public class FornecedorTableModel extends AbstractTableModel {

    private List<Fornecedor> fornecedores;
    //private List<Setor> setoresFiltrados;
    private boolean ordenarPorNome = true;

    /**
     * Creates a new instance of TarefasTableModel
     *
     * @param fornecedor
     */
    public FornecedorTableModel(List<Fornecedor> fornecedor) {
        this.fornecedores = fornecedor;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor fornecedor = fornecedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fornecedor.getIdFornecedor();
            case 1:
                return fornecedor.getNome();
            case 2:
                return fornecedor.getCnpj();
            case 3:
                return fornecedor.getModificado();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Fornecedor getValoresUsuario(int rowIndex) {
        return fornecedores.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }
}
