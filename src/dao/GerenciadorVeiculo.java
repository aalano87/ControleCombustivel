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
import model.Veiculo;
import sessao.Sessao;

/**
 *
 * @author aalano
 */
public class GerenciadorVeiculo {

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void inserir(Veiculo veiculo) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO VEICULO(TIPO, VEICULO, STATUS, PLACA, IDPROPRIETARIO, MODIFICADO) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, veiculo.getTipo());
            ps.setString(2, veiculo.getVeiculo());
            ps.setString(3, veiculo.getStatus());
            ps.setString(4, veiculo.getPlaca());
            ps.setInt(5, veiculo.getProprietario().getId());
            ps.setString(6, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(Veiculo veiculo) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE VEICULO SET TIPO = ?, VEICULO = ?, STATUS = ?, IDPROPRIETARIO = ?, MODIFICADO = ? "
                + " WHERE PLACA = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, veiculo.getTipo());
            ps.setString(2, veiculo.getVeiculo());
            ps.setString(3, veiculo.getStatus());
            ps.setInt(4, veiculo.getProprietario().getId());
            ps.setString(5, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setString(6, veiculo.getPlaca());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(Veiculo veiculo) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM VEICULO WHERE PLACA = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, veiculo.getPlaca());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Não é possivel remover o veículo selecionado! \n Veículo possui abastecimentos registrados");
        }
    }

    public ArrayList<Veiculo> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM VEICULO V, PROPRIETARIO P WHERE V.IDPROPRIETARIO = P.IDPROPRIETARIO ORDER BY PLACA;";
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

    public ArrayList<Veiculo> pesquisaProprietario(String proprietario) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM VEICULO V, PROPRIETARIO P WHERE V.IDPROPRIETARIO = P.IDPROPRIETARIO AND P.NOME LIKE ? ;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + proprietario + "%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    public ArrayList<Veiculo> pesquisaStatus(String status) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM VEICULO V, PROPRIETARIO P WHERE V.IDPROPRIETARIO = V.IDPROPRIETARIO AND V.STATUS LIKE ? "
                + "GROUP BY V.PLACA;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + status + "%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    private ArrayList<Veiculo> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<Veiculo> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Veiculo a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    public Veiculo obterVeiculo(String placa) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM VEICULO V, PROPRIETARIO P WHERE V.IDPROPRIETARIO = P.IDPROPRIETARIO AND PLACA LIKE ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + placa + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Veiculo a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há veiculo com esta PLACA.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    public Veiculo obterVeiculo() throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM VEICULO V, PROPRIETARIO P WHERE V.IDPROPRIETARIO = V.IDPROPRIETARIO;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Veiculo a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há veiculo com esta PLACA.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    private static Veiculo transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        Veiculo a = new Veiculo();
        try {
            a.setId(rs.getInt("V.IDVEICULO"));
            a.setVeiculo(rs.getString("V.VEICULO"));
            a.setPlaca(rs.getString("V.PLACA"));
            a.setStatus(rs.getString("V.STATUS"));
            a.setTipo(rs.getString("V.TIPO"));
            Proprietario p = new Proprietario();
            p.setId(rs.getInt("P.IDPROPRIETARIO"));
            p.setNome(rs.getString("P.NOME"));
            a.setModificado(rs.getString("V.MODIFICADO"));
            a.setProprietario(p);

        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return a;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(IDVEICULO) AS IDVEICULO FROM VEICULO";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("IDVEICULO");
        rs.close();
        stmt.close();
        return lastId + 1;
    }

    public boolean ValorExistente(String placa) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "SELECT PLACA FROM VEICULO WHERE PLACA LIKE '" + placa + "'";
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
