/*
 * AbastecimentoTableModel.java
 *
 * Created on 29 de Novembro de 2006, 20:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package apresentacao.tablecfg;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.EntradaCombustivel;

/**
 *
 * @author aalano
 *
 * Esta classe permite a exibição dos dados de uma List<Aluno>
 * em um JTable.
 */
public class EntradaTableModel extends AbstractTableModel {

    private List<EntradaCombustivel> entradaCombustivels;
    //private List<Setor> setoresFiltrados;
    private boolean ordenarPorNome = true;

    /**
     * Creates a new instance of TarefasTableModel
     *
     * @param entradaCombustivel
     */
    public EntradaTableModel(List<EntradaCombustivel> entradaCombustivel) {
        this.entradaCombustivels = entradaCombustivel;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EntradaCombustivel entradaCombustivel = entradaCombustivels.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entradaCombustivel.getId();
            case 1:
                if (entradaCombustivel.getData() != null) {
                    SimpleDateFormat formatDateToStr = new SimpleDateFormat("dd/MM/yyyy");
                    return formatDateToStr.format(entradaCombustivel.getData());
                } else {
                    return "";
                }
            case 2:
                return entradaCombustivel.getFornecedor();
            case 3:
                return entradaCombustivel.getNf();
            case 4:
                return entradaCombustivel.getProprietario().getNome();
            case 5:
                return entradaCombustivel.getQtdeLitros();
            case 6:
                return NumberFormat.getCurrencyInstance().format(entradaCombustivel.getValorUnitario());
            case 7:
                return NumberFormat.getCurrencyInstance().format(entradaCombustivel.getValorUnitario() * entradaCombustivel.getQtdeLitros());

        }
        return null;
    }

    @Override
    public int getRowCount() {
        return entradaCombustivels.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    public EntradaCombustivel getValoresEntradaCombustivel(int rowIndex) {
        return entradaCombustivels.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }
}
