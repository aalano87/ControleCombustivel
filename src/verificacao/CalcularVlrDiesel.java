/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package verificacao;

import apresentacao.DialogRelatorioCompraConsumo;
import conexao.Conexao;
import excecao.ExcecaoConexao;
import excecao.ExcecaoSQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aalano
 */
public class CalcularVlrDiesel {
    
    public static void atualizarTotal(){
        PreparedStatement ps;
        try {
            ps = Conexao.getConnection().prepareStatement(
                    "UPDATE ABASTECIMENTO SET TOTALRS = LITROS * VLRUNIT;");
            ps.executeUpdate();
        } catch (ExcecaoConexao | ExcecaoSQL | SQLException ex) {
            Logger.getLogger(DialogRelatorioCompraConsumo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
