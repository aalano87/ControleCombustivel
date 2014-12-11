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
import model.Usuario;

/**
 *
 * @author aalano
 *
 * Esta classe permite a exibição dos dados de uma List<Aluno>
 * em um JTable.
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;
    //private List<Setor> setoresFiltrados;
    private boolean ordenarPorNome = true;

    /**
     * Creates a new instance of TarefasTableModel
     *
     * @param usuario
     */
    public UsuarioTableModel(List<Usuario> usuario) {
        this.usuarios = usuario;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getIdusuario();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getCpf();
            case 3:
                return usuario.getModificado();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Usuario getValoresUsuario(int rowIndex) {
        return usuarios.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }
}
