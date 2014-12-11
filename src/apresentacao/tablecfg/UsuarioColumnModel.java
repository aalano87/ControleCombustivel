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
public class UsuarioColumnModel extends DefaultTableColumnModel{
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
    public UsuarioColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criaColuna(0, 25 * digito, fm, false, "Código"));
        addColumn(criaColuna(1, 40 * letra, fm, false, "Nome"));
        addColumn(criaColuna(2, 25 * digito, fm, false, "CPF"));
        addColumn(criaColuna(3, 30 * letra, fm, true, "Última alteração"));
        
    } 
}
