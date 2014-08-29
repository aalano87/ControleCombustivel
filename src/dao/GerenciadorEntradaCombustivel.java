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
import java.util.ArrayList;
import model.EntradaCombustivel;
import model.Proprietario;

/**
 *
 * @author aalano
 */
public class GerenciadorEntradaCombustivel {

    public void inserir(EntradaCombustivel entradaCombustivel) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO ENTRADA (DATA, NF, FORNECEDOR, IDPROPRIETARIO, QTDE_LITROS, VALOR_UNITARIO) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(entradaCombustivel.getData().getTime()));
            ps.setString(2, entradaCombustivel.getNf());
            ps.setString(3, entradaCombustivel.getFornecedor());
            ps.setInt(4, entradaCombustivel.getProprietario().getId());
            ps.setDouble(5, entradaCombustivel.getQtdeLitros());
            ps.setDouble(6, entradaCombustivel.getValorUnitario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
            
        }
    }

    public void atualizar(EntradaCombustivel entradaCombustivel) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE ENTRADA SET DATA = ?, IDPROPRIETARIO = ?, "
                + "QTDE_LITROS = ?, VALOR_UNITARIO = ?"
                + " WHERE NF = ? AND FORNECEDOR = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(entradaCombustivel.getData().getTime()));
            ps.setInt(2, entradaCombustivel.getProprietario().getId());
            ps.setDouble(3, entradaCombustivel.getQtdeLitros());
            ps.setDouble(4, entradaCombustivel.getValorUnitario());
            ps.setString(5, entradaCombustivel.getNf());
            ps.setString(6, entradaCombustivel.getFornecedor());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(EntradaCombustivel entradaCombustivel) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM ENTRADA WHERE NF = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, entradaCombustivel.getNf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public ArrayList<EntradaCombustivel> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ENTRADA E, PROPRIETARIO P "
                + "WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO"
                + " ORDER BY DATA;";
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

    private ArrayList<EntradaCombustivel> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<EntradaCombustivel> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                EntradaCombustivel a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    public EntradaCombustivel obterEntradaCombustivel(String nf) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM ENTRADA E, PROPRIETARIO P WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO AND NF = ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, nf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                EntradaCombustivel a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há Entrada Combustivel com esta NF.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    public ArrayList<EntradaCombustivel> pesquisaFornecedor(String fornecedor) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ENTRADA E, PROPRIETARIO P WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO "
                + "AND FORNECEDOR LIKE ? ORDER BY DATA;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + fornecedor + "%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    public ArrayList<EntradaCombustivel> pesquisaComprador(String comprador) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ENTRADA E, PROPRIETARIO P "
                + "WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO "
                + "AND P.NOME LIKE ? ORDER BY DATA;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + comprador + "%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    public ArrayList<EntradaCombustivel> pesquisaData(java.util.Date dataInicio, java.util.Date dataFim) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ENTRADA E, PROPRIETARIO P "
                + "WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO  "
                + "AND DATA BETWEEN ? AND ? ORDER BY DATA ;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
            ps.setDate(2, new java.sql.Date(dataFim.getTime()));
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }

    public EntradaCombustivel obterEntradaCombustivel() throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM ENTRADA E, PROPRIETARIO P WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                EntradaCombustivel a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há Entrada com esta NF.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    private static EntradaCombustivel transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        EntradaCombustivel obj = new EntradaCombustivel();
        try {
            obj.setId(rs.getInt("E.IDENTRADA"));
            Proprietario p = new Proprietario();
            p.setId(rs.getInt("P.IDPROPRIETARIO"));
            p.setNome(rs.getString("P.NOME"));
            obj.setProprietario(p);
            if (rs.getDate("E.DATA") != null) {
                obj.setData(new java.util.Date(rs.getDate("E.DATA").getTime()));
            } else {
                obj.setData(null);
            }
            obj.setFornecedor(rs.getString("E.FORNECEDOR"));
            obj.setNf(rs.getString("E.NF"));
            obj.setQtdeLitros(rs.getDouble("E.QTDE_LITROS"));
            obj.setValorUnitario(rs.getDouble("E.VALOR_UNITARIO"));
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return obj;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(IDENTRADA) as identrada FROM entradaCombustivel";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("identrada");
        rs.close();
        stmt.close();
        return lastId + 1;
    }

    public boolean ValorExistente(String nf, String fornecedor) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "select nf, fornecedor from entrada where nf like '" + nf + "' and fornecedor like '" + fornecedor + "' ";
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

    public float totalLitrosFornecedor(String fornecedor) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS) FROM ENTRADA WHERE FORNECEDOR LIKE ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + fornecedor + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("Sum(qtde_litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }

    public double valorFornecedor(String fornecedor) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS * VALOR_UNITARIO) FROM ENTRADA WHERE FORNECEDOR LIKE ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + fornecedor + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double valorTotal = rs.getDouble("Sum(qtde_litros * valor_unitario)");
        rs.close();
        stmt.close();
        return valorTotal;
    }

    public float totalLitrosComprador(String comprador) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS) AS litros FROM ENTRADA E, PROPRIETARIO P WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO"
                + " AND P.NOME LIKE ?;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + comprador + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("litros");
        rs.close();
        stmt.close();
        return totalLitros;
    }

    public double valorComprador(String comprador) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS * VALOR_UNITARIO) FROM ENTRADA E , PROPRIETARIO P "
                + "WHERE E.IDPROPRIETARIO = P.IDPROPRIETARIO"
                + " AND P.NOME LIKE ?;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + comprador + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double valorTotal = rs.getDouble("Sum(qtde_litros * valor_unitario)");
        rs.close();
        stmt.close();
        return valorTotal;
    }

    public float totalLitros() throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS) FROM ENTRADA;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("Sum(qtde_litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }

    public double valorTotal() throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS * VALOR_UNITARIO) FROM ENTRADA ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double valorTotal = rs.getDouble("Sum(qtde_litros * valor_unitario)");
        rs.close();
        stmt.close();
        return valorTotal;
    }

    public double totalLitrosData(java.util.Date dataInicio, java.util.Date dataFim) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS) FROM ENTRADA WHERE DATA BETWEEN ? AND ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(dataInicio.getTime()));
        stmt.setDate(2, new java.sql.Date(dataFim.getTime()));
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double totalLitros = rs.getDouble("Sum(qtde_litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }

    public double valorTotalData(java.util.Date dataInicio, java.util.Date dataFim) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(QTDE_LITROS * VALOR_UNITARIO) FROM ENTRADA WHERE DATA BETWEEN ? AND ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(dataInicio.getTime()));
        stmt.setDate(2, new java.sql.Date(dataFim.getTime()));
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double valorTotal = rs.getDouble("Sum(qtde_litros * valor_unitario)");
        rs.close();
        stmt.close();
        return valorTotal;
    }

}
