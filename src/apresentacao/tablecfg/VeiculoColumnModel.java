/*
 * AbastecimentoColumnModel.java
 *
 * Created on 29 de Novembro de 2006, 20:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package apresentacao.tablecfg;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author aalano
 */
public class VeiculoColumnModel extends DefaultTableColumnModel{
    private TableColumn criaColuna(int columnIndex, int largura,
            FontMetrics fm, boolean resizeable, String titulo) {
        int larguraTitulo = fm.stringWidth(titulo + " ");
        if (largura < larguraTitulo)
            largura = larguraTitulo;
        TableColumn col = new TableColumn(columnIndex);
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);
        if (!resizeable) {
            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizeable);
        return col;
    }
    /** Creates a new instance of TarefasColumnModel */
    public VeiculoColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criaColuna(0, 10 * digito, fm, false, "Código"));
        addColumn(criaColuna(1, 18 * letra, fm, false, "Tipo"));
        addColumn(criaColuna(2, 9 * letra, fm, false, "Status"));
        addColumn(criaColuna(3, 20 * letra, fm, false, "Placa"));
        addColumn(criaColuna(4, 25 * letra, fm, false, "Veículo"));
        addColumn(criaColuna(5, 30 * letra, fm, true, "Proprietário"));
        addColumn(criaColuna(6, 30 * letra, fm, true, "Última alteração"));
    } 
}
