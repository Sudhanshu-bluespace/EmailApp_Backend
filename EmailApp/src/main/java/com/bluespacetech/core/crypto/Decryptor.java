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

public class Decryptor
{
  private static Logger logger = LogManager.getLogger(Decryptor.class);
  
  public static String Decrypt(String hashedText)
  {
    String decoded = null;
    try
    {
      String encodedEncrypted = new String(hashedText);
      
      byte[] saltDecrypt = "XYZ%JOB%PORTAL_SALT".getBytes();
      int iterationsDecrypt = 10000;
      
      SecretKeyFactory factoryKeyDecrypt = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      
      SecretKey tmp2 = factoryKeyDecrypt.generateSecret(new PBEKeySpec("XYZJOBPORTAL"
        .toCharArray(), saltDecrypt, iterationsDecrypt, 128));
      SecretKeySpec decryptKey = new SecretKeySpec(tmp2.getEncoded(), "AES");
      
      Cipher aesCipherDecrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
      aesCipherDecrypt.init(2, decryptKey);
      
      byte[] e64bytes = StringUtils.getBytesUtf8(encodedEncrypted);
      
      byte[] eBytes = Base64.decodeBase64(e64bytes);
      
      byte[] cipherDecode = aesCipherDecrypt.doFinal(eBytes);
      
      decoded = StringUtils.newStringUtf8(cipherDecode);
    }
    catch (InvalidKeyException|IllegalBlockSizeException|BadPaddingException|InvalidKeySpecException|NoSuchAlgorithmException|NoSuchPaddingException ex)
    {
      logger.error("Exception while Decrypting data : " + ex.getMessage());
    }
    return decoded;
  }
}
