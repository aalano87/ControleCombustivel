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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Abastecimento;
import model.Proprietario;
import model.Veiculo;
import sessao.Sessao;

/**
 *
 * @author aalano
 */
public class GerenciadorAbastecimento {

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public void inserir(Abastecimento abastecimento) throws ExcecaoConexao, ExcecaoSQL {
        String sql
                = "INSERT INTO ABASTECIMENTO (DATA, MOTORISTA, KM ,HORAS, LITROS, IDVEICULO, MODIFICADO, VLRUNIT, IDPROPRIETARIO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(abastecimento.getData().getTime()));
            ps.setString(2, abastecimento.getMotorista());
            ps.setInt(3, abastecimento.getKm());
            ps.setInt(4, abastecimento.getHoras());
            ps.setFloat(5, abastecimento.getLitros());
            ps.setInt(6, abastecimento.getVeiculo().getId());
            ps.setString(7, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
//            if (abastecimento.getVeiculo().getProprietario().getNome().equals("JJ THOMAZI") ||
//                    abastecimento.getVeiculo().getProprietario().getNome().equals("ELTRANS/TGL")){
//                        ps.setDouble(8, precoCusto());
//                    }else{
//            ps.setDouble(8, new GerenciadorValorVenda().maxValor());
//            }
            if (abastecimento.getVeiculo().getProprietario().getVendadiesel().equals("JJ THOMAZI") ||
                    abastecimento.getVeiculo().getProprietario().getVendadiesel().equals("ELTRANS")){
                ps.setDouble(8, precoCusto());
            }else {
                ps.setDouble(8, new GerenciadorValorVenda().maxValor());
            }

            ps.setInt(9, abastecimento.getVeiculo().getProprietario().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    public void atualizar(Abastecimento abastecimento) throws ExcecaoSQL, ExcecaoConexao {
        String sql = "UPDATE ABASTECIMENTO SET DATA = ?, MOTORISTA = ?, "
                + "KM = ?, HORAS = ?, LITROS = ?, MODIFICADO = ?, IDVEICULO = ?, "
                + "IDPROPRIETARIO = ? "
                + " WHERE ID = ? ";
        try {
            PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(abastecimento.getData().getTime()));
            ps.setString(2, abastecimento.getMotorista());
            ps.setInt(3, abastecimento.getKm());
            ps.setInt(4, abastecimento.getHoras());
            ps.setFloat(5, abastecimento.getLitros());
            ps.setString(6, Sessao.getInstance().getUsuario().toString() + " " + getDateTime());
            ps.setInt(7, abastecimento.getVeiculo().getId());
            ps.setInt(8, abastecimento.getVeiculo().getProprietario().getId());
            ps.setInt(9, abastecimento.getId());
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
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO ORDER BY ID DESC;";
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
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND P.NOME LIKE ? ORDER BY A.DATA DESC ;";
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
                + " AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND V.PLACA LIKE ? ORDER BY A.DATA DESC";//" +"%"+placa+"%'";
        ResultSet rs = null;
        //ResultSet rs = conexao.Conexao.executeSQL(sql);
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
                + "AND V.IDPROPRIETARIO = P.IDPROPRIETARIO AND DATA BETWEEN ? AND ? ORDER BY A.DATA DESC;";
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
              return null;
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
            p.setVendadiesel(rs.getString("P.VENDADIESEL"));
            v.setProprietario(p);
            obj.setVeiculo(v);
            obj.setModificado(rs.getString("A.MODIFICADO"));
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return obj;
    }

    public int lastID() throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT MAX(ID) AS ID FROM ABASTECIMENTO";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int lastId = rs.getInt("ID");
        rs.close();
        stmt.close();
        return lastId + 1;
    }

    public double MediaKM(String placa) throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT (MAX(KM) - MIN(KM)) / SUM(LITROS) AS MEDIA FROM ABASTECIMENTO A, VEICULO V WHERE KM > 0 AND A.IDVEICULO = V.IDVEICULO AND V.PLACA LIKE ? ";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + placa + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double mediaKm = rs.getDouble("MEDIA");
        rs.close();
        stmt.close();
        return mediaKm;
    }
    
    public double MediaHS(String placa) throws SQLException, ExcecaoConexao, ExcecaoSQL {
        String sql = "SELECT SUM(LITROS) / (MAX(HORAS) - MIN(HORAS))  AS MEDIA FROM ABASTECIMENTO A, VEICULO V WHERE HORAS > 0 AND A.IDVEICULO = V.IDVEICULO AND V.PLACA LIKE ? ";
        PreparedStatement stmt = (PreparedStatement) Conexao.getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + placa + "%");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        double mediaHs = rs.getDouble("MEDIA");
        rs.close();
        stmt.close();
        return mediaHs;
    }
    
    public boolean ValorExistente(Date data, String placa, float litros) throws ExcecaoSQL, SQLException, ExcecaoConexao {
        boolean result = true;
        String sql = "SELECT ABASTECIMENTO.DATA, VEICULO.PLACA, ABASTECIMENTO.LITROS FROM ABASTECIMENTO, "
                + "VEICULO WHERE ABASTECIMENTO.DATA LIKE '" + data + "' AND VEICULO.PLACA LIKE '" + placa + 
                "' AND ABASTECIMENTO.LITROS LIKE '" + litros + "'"
;
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
        String sql = "SELECT SUM(QTDE_LITROS) AS QTDE_LITROS FROM ENTRADA";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getFloat("QTDE_LITROS");
            System.out.println("litros entrada" + result);
            return result;
        }
        return 0;
    }
      
    public float getLitrosSaida() throws ExcecaoConexao,SQLException, ExcecaoConexao, ExcecaoSQL {
       float result = 0;
        String sql = "SELECT SUM(LITROS) AS LITROS FROM ABASTECIMENTO";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getFloat("LITROS");
              System.out.println("litros saida" + result);
            return result;
        }
        return 0;
    }
    
    public double precoCusto() throws ExcecaoConexao,SQLException, ExcecaoConexao, ExcecaoSQL {
        double result = 0;
        String sql = "SELECT AVG(VALOR_UNITARIO) AS VALOR_UNITARIO FROM ENTRADA  WHERE DATA > '2016/09/01';";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getDouble("VALOR_UNITARIO");
            return result;
        }
        return 0;
    }
    
    public void updateValor() {
        PreparedStatement ps;
        try {
            ps = Conexao.getConnection().prepareStatement("UPDATE ABASTECIMENTO A SET A.VLRUNIT = (SELECT VALOR FROM VALORVENDA WHERE ID = (SELECT MAX(ID) FROM VALORVENDA)) \n" +
"WHERE A.DATA >= (SELECT MAX(DATA) FROM VALORVENDA) AND A.IDPROPRIETARIO  > 3 AND A.IDPROPRIETARIO < 18 OR\n" +
"A.DATA >= (SELECT MAX(DATA) FROM VALORVENDA) AND A.IDPROPRIETARIO  < 2 OR"
                    + " A.DATA >= (SELECT MAX(DATA) FROM VALORVENDA) AND A.IDPROPRIETARIO  = 20;");
            ps.executeUpdate();
        } catch (ExcecaoConexao | ExcecaoSQL | SQLException ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public void atualizarQuinzena() {
        PreparedStatement ps;
        PreparedStatement ps2;
        try{
            ps = Conexao.getConnection().prepareStatement("call sp_1_quinz;");
            ps.executeQuery();
            ps2 = Conexao.getConnection().prepareStatement("call sp_2_quinz;");
            ps2.executeQuery();
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
      public void atualizarMes() {
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = Conexao.getConnection().prepareStatement("UPDATE ABASTECIMENTO SET MES = MONTH(ABASTECIMENTO.DATA);");
            ps.executeUpdate();
            ps2 = Conexao.getConnection().prepareStatement("UPDATE ABASTECIMENTO SET ANO = YEAR(ABASTECIMENTO.DATA);");
            ps2.executeUpdate();
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarMesEntrada() {
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = Conexao.getConnection().prepareStatement("UPDATE ENTRADA SET MES = MONTH(ENTRADA.DATA);");
            ps.executeUpdate();
            ps2 = Conexao.getConnection().prepareStatement("UPDATE ENTRADA SET ANO = YEAR(ENTRADA.DATA);");
            ps2.executeUpdate();
        } catch (ExcecaoConexao ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoSQL ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorAbastecimento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
