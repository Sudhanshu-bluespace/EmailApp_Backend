package com.bluespacetech.core.utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoUtil
{
  Cipher ecipher;
  Cipher dcipher;
  byte[] salt = { -87, -101, -56, 50, 86, 53, -29, 3 };
  int iterationCount = 19;
  
  public String encrypt(String secretKey, String plainText)
    throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
  {
    KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), this.salt, this.iterationCount);
    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
    
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
    
    this.ecipher = Cipher.getInstance(key.getAlgorithm());
    this.ecipher.init(1, key, paramSpec);
    String charSet = "UTF-8";
    byte[] in = plainText.getBytes("UTF-8");
    byte[] out = this.ecipher.doFinal(in);
    String encStr = new BASE64Encoder().encode(out);
    return encStr;
  }
  
  public String decrypt(String secretKey, String encryptedText)
    throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException
  {
    KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), this.salt, this.iterationCount);
    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
    
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
    
    this.dcipher = Cipher.getInstance(key.getAlgorithm());
    this.dcipher.init(2, key, paramSpec);
    byte[] enc = new BASE64Decoder().decodeBuffer(encryptedText);
    byte[] utf8 = this.dcipher.doFinal(enc);
    String charSet = "UTF-8";
    String plainStr = new String(utf8, "UTF-8");
    return plainStr;
  }
}
