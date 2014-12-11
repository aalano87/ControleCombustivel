/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aalano
 */
public class Conexao {

    private static Connection connection;
    private static Statement stm;
    private static ResultSet resultset;

    public static Connection getConnection() throws ExcecaoConexao, ExcecaoSQL {
        if (connection != null) {
            //    System.out.println("conectado");
            return connection;
        } else {
            //  System.out.println("tentando");
            return connection = connect();
        }

    }

    private static Connection connect() throws ExcecaoConexao, ExcecaoSQL {
        String url = null;
        String user = null;
        String pass = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");//defina o driver do SGBD
           // url = "jdbc:mysql://127.0.0.1:3306/jjthomaz_combustivel";//coloque o nome do seu banco de dados BD LOCAL
            //url = "jdbc:mysql://192.168.1.53:3306/bd_controle_combustivel";//coloque o nome do seu banco de dados BD REDE INTERNA
            url = "jdbc:mysql://179.127.174.110:3306/jjthomaz_combustivel";//coloque o nome do seu banco de dados BD REDE EXTERNA
            //url = "jdbc:mysql://rbr37.dizinc.com:3306/jjthomaz_combustivel";//BD CPANEL
            user = "root";//coloque o nome de usuário do seu banco de dados
            // user = "jjthomaz";
            pass = "jjt813";//coloque a senha de acesso ao banco de dados
            //pass = "QNUZO35j";//coloque a senha de acesso ao banco de dados
            // pass = "jjth2012";

            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            throw new ExcecaoConexao("Falha na conexão com banco de dados. \n"
                    + "Está faltando a biblioteca correta do driver ou, ainda, \n"
                    + " pode haver falha na especificação da url " + url + "\n"
                    + " [" + ex.getMessage() + "].", ex.getCause());
        } catch (SQLException ex) {
            throw new ExcecaoSQL(""
                    + "Confira um dos seguintes parâmetros: \n"
                    + "URL: " + url + "\n"
                    + "USER: " + user + "\n"
                    + "PASS: " + pass + ".\n"
                    + "URL, USER ou PASS podem estar incorretos. Confira.\n"
                    + "[" + ex.getMessage() + "].", ex.getCause());
        }
        return connection;

    }

    public static void closeConnection() throws ExcecaoSQL {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new ExcecaoSQL("Não foi possível encerrar a conexão.\n"
                    + "[" + ex.getMessage() + "].", ex.getCause());
        }
    }

    public static ResultSet executeSQL(String sql) {
        try {
            stm = connection.createStatement();
            resultset = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultset;
    }
}
