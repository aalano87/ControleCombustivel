/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import model.Fornecedor;
import sessao.Sessao;

/**
 *
 * @author aalano
 */
public class GerenciadorFornecedor {

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void inserir(Fornecedor fornecedor) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO FORNECEDOR(NOME, CNPJ, MODIFICADO) "
                + "VALUES (?,?,?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(Fornecedor fornecedor) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE FORNECEDOR SET NOME = ?, CNPJ = ?, MODIFICADO = ?"
                + " WHERE IDFORNECEDOR = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setInt(4, fornecedor.getIdFornecedor());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(Fornecedor fornecedor) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM FORNECEDOR WHERE IDFORNECEDOR = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, fornecedor.getIdFornecedor());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    private static Fornecedor transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        Fornecedor a = new Fornecedor();
        try {
            a.setIdFornecedor(rs.getInt("IDFORNECEDOR"));
            a.setNome(rs.getString("NOME"));
            a.setCnpj(rs.getString("CNPJ"));
            a.setModificado(rs.getString("MODIFICADO"));
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return a;
    }

    //Método para Consultar, verificar e validar Acesso  
    public Fornecedor consultar() throws ExcecaoConexao, ExcecaoSQL, SQLException {
        Fornecedor a = new Fornecedor();
        String sql = "SELECT * FROM FORNECEDOR";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            a.setIdFornecedor(rs.getInt("IDFORNECEDOR"));
            a.setNome(rs.getString("NOME"));
            a.setCnpj(rs.getString("CNPJ"));
            a.setModificado(rs.getString("MODIFICADO"));
        }
        return a;
    }

    public Fornecedor obterFornecedor(int id) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM FORNECEDOR WHERE IDFORNECEDOR = ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Fornecedor a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há fornecedor com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    public ArrayList<Fornecedor> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM FORNECEDOR ORDER BY NOME;";
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

    private ArrayList<Fornecedor> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<Fornecedor> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Fornecedor a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(IDFORNECEDOR) AS IDFORNECEDOR FROM FORNECEDOR";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("IDFORNECEDOR");
        rs.close();
        stmt.close();
        return lastId + 1;
    }

    public boolean ValorExistente(String cnpj) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "SELECT CNPJ FROM FORNECEDOR WHERE CNPJ LIKE '" + cnpj + "'";
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
