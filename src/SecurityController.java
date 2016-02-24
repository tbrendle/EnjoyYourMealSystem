import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityController {
	public static String hashPassword(String password) {
	    try{
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
