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
 * The Class Encryptor.
 */
public class Encryptor {
        
        /** The logger. */
        private static Logger logger = LogManager.getLogger(Encryptor.class);
        
        /**
         * Encrypt.
         *
         * @param toEncrypt the to encrypt
         * @return the string
         */
        public static String Encrypt(String toEncrypt)
        {
                String encoded = null;

        try
        {
            byte[] saltEncrypt = EncryptionUtilityConstants.ENCRYPTION_SALT.getBytes();
            int iterationsEncrypt = 10000;
            SecretKeyFactory factoryKeyEncrypt = SecretKeyFactory.getInstance(EncryptionUtilityConstants.ENCRYPTION_SECRET_KEY);
            SecretKey tmp = factoryKeyEncrypt.generateSecret(new PBEKeySpec(
                        EncryptionUtilityConstants.ENCRYPTION_STRING.toCharArray(), saltEncrypt, iterationsEncrypt,128));
            SecretKeySpec encryptKey = new SecretKeySpec(tmp.getEncoded(),"AES");

            Cipher aesCipherEncrypt = Cipher.getInstance(EncryptionUtilityConstants.ENCRYPTION_PADDING);
            aesCipherEncrypt.init(Cipher.ENCRYPT_MODE, encryptKey);

            // get the bytes
            byte[] bytes = StringUtils.getBytesUtf8(toEncrypt);

            // encrypt the bytes
            byte[] encryptBytes = aesCipherEncrypt.doFinal(bytes);

            // encode 64 the encrypted bytes
            encoded = Base64.encodeBase64URLSafeString(encryptBytes);
           
        }
                  catch(InvalidKeyException|IllegalBlockSizeException|BadPaddingException|InvalidKeySpecException|NoSuchAlgorithmException|NoSuchPaddingException ex)
                  {
                          logger.error("Exception while Encrypting data : "+ex.getMessage());
                  } 
        
         return encoded;
        }
        

}

