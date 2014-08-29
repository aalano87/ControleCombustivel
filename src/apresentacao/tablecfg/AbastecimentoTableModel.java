/*
 * AbastecimentoTableModel.java
 *
 * Created on 29 de Novembro de 2006, 20:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package apresentacao.tablecfg;


import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Abastecimento;


/**
 *
 * @author aalano
 *
 *  Esta classe permite a exibição dos dados de uma List<Aluno>
 *      em um JTable.
 */
public class AbastecimentoTableModel extends AbstractTableModel {

    private List<Abastecimento> abastecimentos;
    //private List<Setor> setoresFiltrados;
    private boolean ordenarPorNome = true;

    /** Creates a new instance of TarefasTableModel
     * @param abastecimento */
    public AbastecimentoTableModel(List<Abastecimento> abastecimento) {
        this.abastecimentos = abastecimento;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Abastecimento abastecimento = abastecimentos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return abastecimento.getId();
            case 1:
                if (abastecimento.getData()!= null) {
                    SimpleDateFormat formatDateToStr = new SimpleDateFormat("dd/MM/yyyy");
                    return formatDateToStr.format(abastecimento.getData());
                } else {
                    return "";
                }
            case 2:
                return abastecimento.getVeiculo().getPlaca();
            case 3:
                return abastecimento.getVeiculo().getVeiculo();
            case 4:
                return abastecimento.getMotorista();
            case 5:
                        if (abastecimento.getVeiculo().getTipo().equals("Máquina")){
                        return abastecimento.getHoras();
                        }else 
                            return abastecimento.getKm();
            case 6:
                return abastecimento.getLitros();
            case 7:
                return abastecimento.getVeiculo().getProprietario();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return abastecimentos.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    public Abastecimento getValoresAbastecimento(int rowIndex) {
        return abastecimentos.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }
}
