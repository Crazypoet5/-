package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance ("md5").digest (
                    plainText.getBytes ());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException ("找不到MD5");
        }
        String md5code = new BigInteger (1, secretBytes).toString (16);
        for (int i = 0; i < 32 - md5code.length (); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

//    public static void main(String[] args) {
//        String t=MD5Utils.md5 ("12345");
//        String y=MD5Utils.md5("827ccb0eea8a706c4c34a16891f84e7b");
//        System.out.println (t+"----"+y);
//    }
}
