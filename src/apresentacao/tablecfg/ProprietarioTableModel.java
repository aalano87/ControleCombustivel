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
import model.Proprietario;

/**
 *
 * @author aalano
 *
 * Esta classe permite a exibição dos dados de uma List<Aluno>
 * em um JTable.
 */
public class ProprietarioTableModel extends AbstractTableModel {

    private List<Proprietario> proprietarios;
    //private List<Setor> setoresFiltrados;
    private boolean ordenarPorNome = true;

    /**
     * Creates a new instance of TarefasTableModel
     *
     * @param proprietario
     */
    public ProprietarioTableModel(List<Proprietario> proprietario) {
        this.proprietarios = proprietario;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proprietario proprietario = proprietarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return proprietario.getId();
            case 1:
                return proprietario.getNome();
            case 2:
                return proprietario.getDocumento();
            case 3:
                return proprietario.getModificado();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return proprietarios.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Proprietario getValoresProprietario(int rowIndex) {
        return proprietarios.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }
}
