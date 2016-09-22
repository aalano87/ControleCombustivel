/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author aalano
 */
public class EncriptaSenha {

    public static String encripta(String senha) {
        try {
            String x = "my_pattern+_pass";
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.concat(x) .getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
    }
}
