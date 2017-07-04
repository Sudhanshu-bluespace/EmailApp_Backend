package com.bluespacetech.core.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.Logger;

import com.bluespacetech.core.constants.EncryptionUtilityConstants;

import org.apache.logging.log4j.LogManager;

/**
 * The Class Decryptor.
 */
public class Decryptor {

        /** The logger. */
        private static Logger logger = LogManager.getLogger(Decryptor.class);
        
        /**
         * Decrypt.
         *
         * @param hashedText the hashed text
         * @return the string
         */
        public static String Decrypt(String hashedText)
    {
          String decoded = null;        
          try
          {
        String encodedEncrypted = new String(hashedText);

            byte[] saltDecrypt = EncryptionUtilityConstants.ENCRYPTION_SALT.getBytes();
            int iterationsDecrypt = 10000;
            
            SecretKeyFactory factoryKeyDecrypt = SecretKeyFactory.getInstance(EncryptionUtilityConstants.ENCRYPTION_SECRET_KEY);
            
            SecretKey tmp2 = factoryKeyDecrypt.generateSecret(new PBEKeySpec(
                        EncryptionUtilityConstants.ENCRYPTION_STRING.toCharArray(), saltDecrypt, iterationsDecrypt, 128));
            SecretKeySpec decryptKey = new SecretKeySpec(tmp2.getEncoded(), "AES");

            Cipher aesCipherDecrypt = Cipher.getInstance(EncryptionUtilityConstants.ENCRYPTION_PADDING);
            aesCipherDecrypt.init(Cipher.DECRYPT_MODE, decryptKey);

            //basically we reverse the process we did earlier
            // get the bytes from encodedEncrypted string
            byte[] e64bytes = StringUtils.getBytesUtf8(encodedEncrypted);

            // decode 64, now the bytes should be encrypted
            byte[] eBytes = Base64.decodeBase64(e64bytes);

            // decrypt the bytes
            byte[] cipherDecode = aesCipherDecrypt.doFinal(eBytes);

            // to string
            decoded = StringUtils.newStringUtf8(cipherDecode);
          
          }
          catch(InvalidKeyException|IllegalBlockSizeException|BadPaddingException|InvalidKeySpecException|NoSuchAlgorithmException|NoSuchPaddingException ex)
          {
                  logger.error("Exception while Decrypting data : "+ex.getMessage());
          } 
          
          return decoded;

    }
}

