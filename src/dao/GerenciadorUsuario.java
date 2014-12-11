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
import sessao.Sessao;

/**
 *
 * @author aalano
 */
public class GerenciadorUsuario {
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    
    public void inserir(Usuario usuario) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO USUARIO(NOME, CPF ,SENHA, MODIFICADO) "
                + "VALUES (?,?,?,?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(Usuario usuario) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE USUARIO SET NOME = ?, CPF = ? ,SENHA = ?, MODIFICADO = ? "
                + " WHERE IDUSUARIO = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setInt(5, usuario.getIdusuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(Usuario usuario) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM USUARIO WHERE IDUSUARIO = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, usuario.getIdusuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }
    
    private static Usuario transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        Usuario a = new Usuario();
        try {
            a.setIdusuario(rs.getInt("IDUSUARIO"));
            a.setNome(rs.getString("NOME"));
            a.setCpf(rs.getString("CPF"));
            a.setSenha(rs.getString("SENHA"));
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
 
    public Usuario obterUsuario(int id) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1,  id );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há usuario com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }
    
    public ArrayList<Usuario> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM USUARIO ;";
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
    
    private ArrayList<Usuario> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Usuario a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(IDUSUARIO) AS IDUSUARIO FROM USUARIO";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("IDUSUARIO");
        rs.close();
        stmt.close();
        return lastId + 1;
    }
    
     public boolean ValorExistente(String cpf) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "SELECT CPF FROM USUARIO WHERE CPF LIKE '" + cpf+ "'";
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
