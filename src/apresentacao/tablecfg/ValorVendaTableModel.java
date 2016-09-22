/*
 * AbastecimentoTableModel.java
 *
 * Created on 29 de Novembro de 2006, 20:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package apresentacao.tablecfg;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ValorVenda;

/**
 *
 * @author aalano
 *
 * Esta classe permite a exibição dos dados de uma List<Aluno>
 * em um JTable.
 */
public class ValorVendaTableModel extends AbstractTableModel {

    private List<ValorVenda> valores;
    //private List<Setor> setoresFiltrados;
    private boolean ordenarPorNome = true;

    /**
     * Creates a new instance of TarefasTableModel
     *
     * @param valores
     */
    public ValorVendaTableModel(List<ValorVenda> valor) {
        this.valores = valor;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        ValorVenda valor = valores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (valor.getDatainserido()!= null) {
                    SimpleDateFormat formatDateToStr = new SimpleDateFormat("dd/MM/yyyy");
                    return formatDateToStr.format(valor.getDatainserido());
                } else {
                    return "";
                }
            case 1:
                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(valor.getValor());
            case 2:
                return valor.getModificado();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public ValorVenda getValoresVenda(int rowIndex) {
        return valores.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }
}
