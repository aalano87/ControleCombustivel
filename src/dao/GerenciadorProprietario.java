/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Proprietario;
import sessao.Sessao;

/**
 *
 * @author aalano
 */
public class GerenciadorProprietario {

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void inserir(Proprietario proprietario) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO PROPRIETARIO(NOME, DOCUMENTO, MODIFICADO, VENDADIESEL) "
                + "VALUES (?,?,?,?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, proprietario.getNome());
            ps.setString(2, proprietario.getDocumento());
            ps.setString(3, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setString(4, proprietario.getVendadiesel());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(Proprietario proprietario) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE PROPRIETARIO SET NOME = ?, DOCUMENTO = ?, MODIFICADO = ?, VENDADIESEL = ? "
                + " WHERE IDPROPRIETARIO = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, proprietario.getNome());
            ps.setString(2, proprietario.getDocumento());
            ps.setString(3, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setString(4, proprietario.getVendadiesel());
            ps.setInt(5, proprietario.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(Proprietario proprietario) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM PROPRIETARIO WHERE IDPROPRIETARIO = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, proprietario.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public ArrayList<Proprietario> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM PROPRIETARIO ORDER BY NOME;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    public ArrayList<Proprietario> pesquisaProprietario(int idProprietario) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM PROPRIETARIO WHERE IDPROPRIETARIO = ? ;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, idProprietario);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    private ArrayList<Proprietario> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<Proprietario> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Proprietario a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    private static Proprietario transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        Proprietario a = new Proprietario();
        try {
            a.setId(rs.getInt("IDPROPRIETARIO"));
            a.setNome(rs.getString("NOME"));
            a.setDocumento(rs.getString("DOCUMENTO"));
            a.setModificado(rs.getString("MODIFICADO"));
            a.setVendadiesel(rs.getString("VENDADIESEL"));
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return a;
    }

    public Proprietario obterProprietario(int id) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM PROPRIETARIO WHERE IDPROPRIETARIO = ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Proprietario a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há proprietario com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    public ArrayList<Proprietario> pesquisaProprietarioNome(String nome) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM PROPRIETARIO WHERE NOME LIKE ? ;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(IDPROPRIETARIO) AS IDPROPRIETARIO FROM PROPRIETARIO";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("IDPROPRIETARIO");
        rs.close();
        stmt.close();
        return lastId + 1;
    }

    public boolean ValorExistente(String documento) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "SELECT DOCUMENTO FROM PROPRIETARIO WHERE DOCUMENTO LIKE '" + documento + "'";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
//            String[] op = {"Sim", "Não"};
//            String nome = "Opa! " + placa + " já existe!\n Deseja continuar mesmo assim?";
//            if (JOptionPane.showOptionDialog(null, nome, "Já existe, deseja continuar?",
//                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, null) == JOptionPane.YES_OPTION){
//                result = false;
//            }
        } else {
            result = false;
        }
        return result;

    }

}
