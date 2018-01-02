package com.bluespacetech.core.crypto;

import java.security.GeneralSecurityException;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Encryptor
{
  private static Logger logger = LogManager.getLogger(Encryptor.class);
  
  public static String Encrypt(String toEncrypt)
  {
    String encoded = null;
    try
    {
      byte[] saltEncrypt = "XYZ%JOB%PORTAL_SALT".getBytes();
      int iterationsEncrypt = 10000;
      SecretKeyFactory factoryKeyEncrypt = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      SecretKey tmp = factoryKeyEncrypt.generateSecret(new PBEKeySpec("XYZJOBPORTAL"
        .toCharArray(), saltEncrypt, iterationsEncrypt, 128));
      SecretKeySpec encryptKey = new SecretKeySpec(tmp.getEncoded(), "AES");
      
      Cipher aesCipherEncrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
      aesCipherEncrypt.init(1, encryptKey);
      
      byte[] bytes = StringUtils.getBytesUtf8(toEncrypt);
      
      byte[] encryptBytes = aesCipherEncrypt.doFinal(bytes);
      
      encoded = Base64.encodeBase64URLSafeString(encryptBytes);
    }
    catch (InvalidKeyException|IllegalBlockSizeException|BadPaddingException|InvalidKeySpecException|NoSuchAlgorithmException|NoSuchPaddingException ex)
    {
      logger.error("Exception while Encrypting data : " + ex.getMessage());
    }
    return encoded;
  }
}
