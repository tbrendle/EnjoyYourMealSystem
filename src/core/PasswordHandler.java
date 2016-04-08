package core;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Class used to handle operations on passwords
 */
public class PasswordHandler {
	/**
	 * Hash a password
	 * @param password the unencrypted password
	 * @return the crypted password (the hash)
	 */
	public static String hashPassword(String password) {
	    try{
	    	// Classic SHA-1 encryption
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		    crypt.reset();
		    crypt.update(password.getBytes("UTF-8"));
		    return new BigInteger(1, crypt.digest()).toString(16);
	    } catch (NoSuchAlgorithmException e) {
	    	return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		} 
	}
}
