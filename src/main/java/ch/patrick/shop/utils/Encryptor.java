package ch.patrick.shop.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/**
 * Verschlüssler für Passwörter
 *
 * @author Patrick
 * @version 1.0
 */
public class Encryptor {

    /**
     * Anzahl Iterationen
     */

    private static final int ITERATIONS = 65536;

    /**
     * Schlüssellänge
     */

    private static final int KEY_LENGTH = 512;

    /**
     * Algorithmus
     */

    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Salz welches dem Passwort hinzugefügt wird
     */

    private static final String salt = "*8ZzPatrickzZKommtHinzu8*";

    /**
     * Passwort verschlüsseln
     *
     * @param password zu verschlüsselndes Passwort
     *
     * @return verschlüsseltes Passwort
     */

    public static Optional<String> hashPassword (String password) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);
        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        }catch(Exception ex){
            return Optional.empty();
        }
    }

}
