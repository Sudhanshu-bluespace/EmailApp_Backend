package com.bluespacetech.core.constants;

/**
 * @author : Sudhanshu Shekhar Tripathy
 * @Version : 1.0
 * @Creation Date : 22-Oct-2014
 * 
 * @Description
 * This class consists of the Regular Expressions and Encryption parameters mapped as constants to be used across the Application.
 * A constant in JAVA is defined as a variable that cannot be re-assigned once initialized.
 * We initialize the variable with public and final modifiers and make them static. That way the constants are accesible through Class Reference across 
 * the application, instead of object reference.
 * 
 * @Usage
 * Following constants have been configured as a part of this class representation
 * ENCRYPTION_SALT : The pattern to initialize a DES Encryption salt
 * ENCRYPTION_SECRET_KEY : The pattern to initiaize a DES Encryption secret key
 * ENCRYPTION_PADDING : The pattern to initialize the padding algorithm
 * ENCRYPTION_STRING : The uniform identfier to mask and unmask the Decded/Encoded password
 * 
 * 
 **/

public class EncryptionUtilityConstants {
	public static final String ENCRYPTION_SALT = "XYZ%JOB%PORTAL_SALT";
	public static final String ENCRYPTION_SECRET_KEY = "PBKDF2WithHmacSHA1";
	public static final String ENCRYPTION_PADDING = "AES/ECB/PKCS5Padding";
	public static final String ENCRYPTION_STRING = "XYZJOBPORTAL";
	public static final String REQUEST_PARAMETER_TOKENIZER_CODE = "DTVPTAFCPE";
	public static final String REQUEST_PARAMETER_CODE_SEPARATOR = "|~~|"; 

	
}
