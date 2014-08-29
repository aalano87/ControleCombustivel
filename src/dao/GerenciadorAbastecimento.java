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
import java.util.Date;
import model.Abastecimento;
import model.Proprietario;
import model.Veiculo;

/**
 *
 * @author aalano
 */
public class GerenciadorAbastecimento {

    public void inserir(Abastecimento abastecimento) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO ABASTECIMENTO (DATA, MOTORISTA, KM ,HORAS, LITROS, IDVEICULO) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(abastecimento.getData().getTime()));
            ps.setString(2, abastecimento.getMotorista());
            ps.setInt(3, abastecimento.getKm());
            ps.setInt(4, abastecimento.getHoras());
            ps.setFloat(5, abastecimento.getLitros());
            ps.setInt(6, abastecimento.getVeiculo().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(Abastecimento abastecimento) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE ABASTECIMENTO SET DATA = ?, MOTORISTA = ?, "
                + "KM = ?, HORAS = ?, LITROS = ? "
                + " WHERE ID = ? ";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(abastecimento.getData().getTime()));
            ps.setString(2, abastecimento.getMotorista());
            ps.setInt(3, abastecimento.getKm());
            ps.setInt(4, abastecimento.getHoras());
            ps.setFloat(5, abastecimento.getLitros());
            ps.setInt(6, abastecimento.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void remover(Abastecimento abastecimento) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "DELETE FROM ABASTECIMENTO WHERE ID = ?";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, abastecimento.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public ArrayList<Abastecimento> obterTodos() throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO "
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO ORDER BY ID;";
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
    
      public ArrayList<Abastecimento> pesquisaProprietario(String proprietario) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO "
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND P.NOME LIKE ? ;";
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
      
      public ArrayList<Abastecimento> pesquisaPlaca(String placa) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO "
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND V.PLACA LIKE ? ;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + placa + "%");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
       public ArrayList<Abastecimento> pesquisaData(Date dataInicio, Date dataFim) throws ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT * FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO "
                + "AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND DATA BETWEEN ? AND ? ORDER BY ID;";
        ResultSet rs = null;
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(dataInicio.getTime()));
            ps.setDate(2, new java.sql.Date(dataFim.getTime()));
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
      
      public float totalLitrosProprietario(String proprietario) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(LITROS) FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO AND "
                + " V.IDPROPRIETARIO = P.IDPROPRIETARIO AND P.NOME LIKE ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + proprietario + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("Sum(Litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }
      
      public float totalLitrosPlaca(String placa) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(LITROS) FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO AND "
                + " V.IDPROPRIETARIO = P.IDPROPRIETARIO AND V.PLACA LIKE ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + placa + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("Sum(Litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }
    
     public float totalLitros() throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(LITROS) FROM ABASTECIMENTO;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("Sum(Litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }

      public float totalLitrosData(Date dataInicio, Date dataFim) throws ExcecaoConexao, ExcecaoSQL, SQLException {
        String sql = "SELECT SUM(LITROS) FROM ABASTECIMENTO WHERE DATA BETWEEN ? AND ? ;";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setDate(1,new java.sql.Date(dataInicio.getTime()));
        stmt.setDate(2, new java.sql.Date(dataFim.getTime()));
        ResultSet rs = stmt.executeQuery();
        rs.next();
        float totalLitros = rs.getFloat("Sum(Litros)");
        rs.close();
        stmt.close();
        return totalLitros;
    }
     
    private ArrayList<Abastecimento> resultSetParaList(ResultSet rs) throws ExcecaoSQL {
        ArrayList<Abastecimento> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Abastecimento a = transformarResultSetEmObjeto(rs);
                lista.add(a);
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    public Abastecimento obterAbastecimento(int id) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO"
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND ID = ?;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Abastecimento a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há abastecimento com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    public Abastecimento obterAbastecimento() throws ExcecaoSQL, ExcecaoConexao {
        String sql = "SELECT * FROM ABASTECIMENTO A, VEICULO V, PROPRIETARIO P WHERE A.IDVEICULO = V.IDVEICULO"
                + "AND V.IDPROPRIETARIO = P.IDPROPRIETARIO;";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Abastecimento a = transformarResultSetEmObjeto(rs);
                return a;
            } else {
                throw new ExcecaoSQL("Não há Entrada com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }

    private static Abastecimento transformarResultSetEmObjeto(ResultSet rs) throws ExcecaoSQL {
        Abastecimento obj = new Abastecimento();
        try {
            obj.setId(rs.getInt("A.ID"));
            obj.setMotorista(rs.getString("A.MOTORISTA"));
            if (rs.getDate("A.DATA") != null) {
                obj.setData(new java.util.Date(rs.getDate("A.DATA").getTime()));
            } else {
                obj.setData(null);
            }
            obj.setHoras(rs.getInt("A.HORAS"));
            obj.setKm(rs.getInt("A.KM"));
            obj.setLitros(rs.getInt("A.LITROS"));
            Veiculo v = new Veiculo();
            v.setId(rs.getInt("V.IDVEICULO"));
            v.setPlaca(rs.getString("V.PLACA"));
            v.setStatus(rs.getString("V.STATUS"));
            v.setTipo(rs.getString("V.TIPO"));
            v.setVeiculo(rs.getString("V.VEICULO"));
            Proprietario p = new Proprietario();
            p.setId(rs.getInt("P.IDPROPRIETARIO"));
            p.setNome(rs.getString("P.NOME"));
            v.setProprietario(p);
            obj.setVeiculo(v);
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return obj;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(ID) as id FROM abastecimento";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("id");
        rs.close();
        stmt.close();
        return lastId + 1;
    }

    public double MediaKM(String placa) throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT (MAX(KM) - MIN(KM)) / SUM(LITROS) as media FROM abastecimento a, veiculo v where km > 0 and a.idveiculo = v.idveiculo and v.placa like ? ";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + placa + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double mediaKm = rs.getDouble("media");
        rs.close();
        stmt.close();
        return mediaKm;
    }
    
    public double MediaHS(String placa) throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT SUM(LITROS) / (MAX(HORAS) - MIN(HORAS))  as media FROM abastecimento a, veiculo v where horas > 0 and a.idveiculo = v.idveiculo and v.placa like ? ";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + placa + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double mediaHs = rs.getDouble("media");
        rs.close();
        stmt.close();
        return mediaHs;
    }
    
    public boolean ValorExistente(Date data, String placa, float litros) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "select abastecimento.data, veiculo.placa, abastecimento.litros from abastecimento, "
                + "veiculo where abastecimento.data like '" + data + "' and veiculo.placa like '" + placa + "' and abastecimento.litros like '" + litros + "'";
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
    
    public float getLitrosEntrada() throws ExcecaoConexao,SQLException, ExcecaoConexao, ExcecaoSQL {
       float result = 0;
        String sql = "select SUM(QTDE_LITROS) as qtde_litros from entrada";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getFloat("qtde_litros");
            return result;
        }
        return 0;
    }
      
    public float getLitrosSaida() throws ExcecaoConexao,SQLException, ExcecaoConexao, ExcecaoSQL {
       float result = 0;
        String sql = "select SUM(LITROS)as litros from abastecimento";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getFloat("litros");
            return result;
        }
        return 0;
    }
}
