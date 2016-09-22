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
import model.Usuario;
import model.ValorVenda;
import sessao.Sessao;

/**
 *
 * @author aalano
 */
public class GerenciadorValorVenda {
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    
    public void inserir(ValorVenda valor) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO VALORVENDA(VALOR, DATA , MODIFICADO) "
                + "VALUES (?,?,?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDouble(1, valor.getValor());
            ps.setDate(2, new java.sql.Date(valor.getDatainserido().getTime()));
            ps.setString(3, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(ValorVenda valor) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE VALOR SET VALOR = ?, DATA = ? , MODIFICADO = ? "
                + " WHERE ID = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDouble(1, valor.getValor());
            ps.setDate(2, new java.sql.Date(valor.getDatainserido().getTime()));
            ps.setString(3, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setInt(4, valor.getIdvalorvenda());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(ValorVenda valor) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM VALORVENDA WHERE ID = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, valor.getIdvalorvenda());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }
    
    private static ValorVenda transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        ValorVenda a = new ValorVenda();
        try {
            a.setIdvalorvenda(rs.getInt("ID"));
            a.setValor(rs.getDouble("VALOR"));
            a.setDatainserido(rs.getDate("DATA"));
            a.setModificado(rs.getString("MODIFICADO"));
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return a;
    }

  //Método para Consultar, verificar e validar Acesso  
    public Usuario consultar() throws ExcecaoConexao, ExcecaoSQL, SQLException {  
        Usuario a = new Usuario();
        String sql = "SELECT * FROM USUARIO";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            a.setIdusuario(rs.getInt("IDUSUARIO"));
            a.setNome(rs.getString("NOME"));
            a.setCpf(rs.getString("CPF"));
            a.setSenha(rs.getString("SENHA"));
            a.setModificado(rs.getString("MODIFICADO"));
        } 
        return a;
    }  
 
    public ValorVenda obterValorVenda(int id) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM VALORVENDA WHERE ID = ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1,  id );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ValorVenda a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há cadastro com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }
    
    public ArrayList<ValorVenda> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM VALORVENDA ;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
          //  ps.setString(1, nome);
           // ps.setString(2, senha);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
    
    private ArrayList<ValorVenda> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<ValorVenda> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                ValorVenda a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(ID) AS ID FROM VALORVENDA";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("ID");
        rs.close();
        stmt.close();
        return lastId;
    }
    
    public double maxValor() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT VALOR AS VALOR FROM VALORVENDA WHERE ID = ?";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setInt(1, lastID());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double mv = rs.getDouble("VALOR");
        rs.close();
        stmt.close();
        return mv;
    }
    
    public Date maxData() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(DATA) AS DATA FROM VALORVENDA";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Date data = rs.getDate("DATA");
        rs.close();
        stmt.close();
        return data;
    }
    
//     public boolean ValorExistente(String cpf) throws ExcecaoSQL, SQLException, ExcecaoConexao {
//        boolean result = true;
//        String sql = "SELECT CPF FROM USUARIO WHERE CPF LIKE '" + cpf+ "'";
//        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
////            String[] op = {"Sim", "Não"};
////            String nome = "Opa! " + placa + " já existe!\n Deseja continuar mesmo assim?";
////            if (JOptionPane.showOptionDialog(null, nome, "Já existe, deseja continuar?",
////                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, null) == JOptionPane.YES_OPTION){
////                result = false;
////            }
//        } else {
//            result = false;
//        }
//        return result;
//
//    }
}
