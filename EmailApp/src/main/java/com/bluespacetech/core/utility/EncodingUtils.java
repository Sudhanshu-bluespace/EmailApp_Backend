package com.bluespacetech.core.utility;

import com.bluespacetech.contact.controller.ContactController;
import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EncodingUtils
{
  private static final Logger LOGGER = LogManager.getLogger(ContactController.class);
  public static final String ISO_ENCODING = "ISO-8859-1";
  private static final String UTF8_ENCODING = "UTF-8";
  private static final int UTF8_BOM_LENGTH = 3;
  
  public static String getSkippedBomString(byte[] bytes)
    throws UnsupportedEncodingException
  {
    int length = bytes.length - 3;
    byte[] barray = new byte[length];
    
    System.arraycopy(bytes, 3, barray, 0, barray.length);
    String reEncoded = new String(barray, "ISO-8859-1");
    
    LOGGER.debug("BOM String re-encoded to " + reEncoded);
    return reEncoded;
  }
  
  public static String getUTF8String(byte[] bytes)
    throws UnsupportedEncodingException
  {
    return new String(bytes, "UTF-8");
  }
  
  public static boolean isUTF8(byte[] bytes)
  {
    if (((bytes[0] & 0xFF) == 239) && ((bytes[1] & 0xFF) == 187) && ((bytes[2] & 0xFF) == 191)) {
      return true;
    }
    return false;
  }
}
