import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 Aes encryption
 */
public class AES
{

    private static SecretKeySpec secretKey ;
    private static byte[] key ;

    private static String decryptedString;
    private static String encryptedString;


    public static void setKey(String myKey){

        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit

            secretKey = new SecretKeySpec(key, "AES");

            //System.out.println(key.length);
            //System.out.println(key.length);
            //System.out.println(new String(key,"UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static String getDecryptedString() {
        return decryptedString;
    }
    public static void setDecryptedString(String decryptedString) {
        AES.decryptedString = decryptedString;
    }
    public static String getEncryptedString() {
        return encryptedString;
    }
    public static void setEncryptedString(String encryptedString) {
        AES.encryptedString = encryptedString;
    }
    public static String encrypt(String strToEncrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);


            setEncryptedString(Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));

        }
        catch (Exception e)
        {

            setEncryptedString("Error while encrypting!");
        }
        return null;
    }
    public static String decrypt(String strToDecrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            setDecryptedString(new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))));

        }
        catch (Exception e)
        {

            setDecryptedString("Error while decrypting! Verify password!");
        }
        return null;
    }

    public static String getEncryptionStatus(String encryptionString, String pass) {

      final String strToEncrypt = encryptionString;
      final String strPssword = pass;

      AES.setKey(strPssword);
      AES.encrypt(strToEncrypt.trim());

      String info = "Plain text to Encrypt: " + strToEncrypt;
      String encryption = "Encrypted: " + AES.getEncryptedString();

      return String.format("%s\n%s", info, encryption);
    }

    public static String getDecryptionStatus(String decryptionString, String pass) {

      final String strPssword = pass;
      final String strToDecrypt =  decryptionString;

      AES.setKey(strPssword);
      AES.decrypt(strToDecrypt.trim());

      String info = "Crypted text To Decrypt : " + strToDecrypt;
      String decryption = "Decrypted : " + AES.getDecryptedString();

      return String.format("%s\n%s", info, decryption);

    }

}
