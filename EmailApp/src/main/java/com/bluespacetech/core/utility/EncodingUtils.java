package com.bluespacetech.core.utility;

import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bluespacetech.contact.controller.ContactController;

/**
 * The Class EncodingUtils.
 */
public class EncodingUtils
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactController.class);

    /** The Constant ISO_ENCODING. */
    public final static String ISO_ENCODING = "ISO-8859-1";

    /** The Constant UTF8_ENCODING. */
    private final static String UTF8_ENCODING = "UTF-8";

    /** The Constant UTF8_BOM_LENGTH. */
    private final static int UTF8_BOM_LENGTH = 3;

    /**
     * Gets the skipped bom string.
     *
     * @param bytes the bytes
     * @return the skipped bom string
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static String getSkippedBomString(final byte[] bytes) throws UnsupportedEncodingException
    {
        int length = bytes.length - UTF8_BOM_LENGTH;
        byte[] barray = new byte[length];
        
        System.arraycopy(bytes, UTF8_BOM_LENGTH, barray, 0, barray.length);
        String reEncoded = new String(barray, ISO_ENCODING);
        
        LOGGER.debug("BOM String re-encoded to " + reEncoded);
        return reEncoded;
    }

    /**
     * Gets the UTF 8 string.
     *
     * @param bytes the bytes
     * @return the UTF 8 string
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static String getUTF8String(final byte[] bytes) throws UnsupportedEncodingException
    {
        return new String(bytes, UTF8_ENCODING);
    }

    /**
     * Checks if is utf8.
     *
     * @param bytes the bytes
     * @return true, if is utf8
     */
    public static boolean isUTF8(byte[] bytes)
    {
        if ((bytes[0] & 0xFF) == 0xEF && (bytes[1] & 0xFF) == 0xBB && (bytes[2] & 0xFF) == 0xBF)
        {
            return true;
        }
        return false;
    }
}
