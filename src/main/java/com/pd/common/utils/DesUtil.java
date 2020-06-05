package com.pd.common.utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

//Access restriction: The type BASE64Decoder is not accessible due to restriction on required library ,java build path jre???workspace

/**
 * DES???? ??????
 * @author zhangdi
 *
 */
public class DesUtil {

    private final static String DES = "DES";
    private final static String ENCODE = "GBK";

    

    
    /**
     * Description ?????????��???
     * @param data ??????????
     * @param key ???
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * ?????????��???
     * @param data ??????????
     * @param key    ???
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes(ENCODE));
        return new String(bt, ENCODE);
    }

    /**
     * Description ?????????��???
     * 
     * @param data
     * @param key
     *            ?????byte????
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // ????????????��???????
        SecureRandom sr = new SecureRandom();

        // ??????????????DESKeySpec????
        DESKeySpec dks = new DESKeySpec(key);

        // ?????????????????????????DESKeySpec?????SecretKey????
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher????????????????
        Cipher cipher = Cipher.getInstance(DES);

        // ??????????Cipher????
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description ?????????��???
     * 
     * @param data
     * @param key ?????byte????
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // ????????????��???????
        SecureRandom sr = new SecureRandom();

        // ??????????????DESKeySpec????
        DESKeySpec dks = new DESKeySpec(key);

        // ?????????????????????????DESKeySpec?????SecretKey????
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher????????????????
        Cipher cipher = Cipher.getInstance(DES);

        // ??????????Cipher????
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
    
    public static void main(String[] args){
        String data = "12AUism810jsqASI08";
        //?????????????8?????
       // String key ="12345678abcdefgh";
         String key ="22345678abcdefgh";

        System.out.println("?????===>"+data);
        try {
        	String e1=encrypt(data, key);
            System.out.println("???key?????===>"+e1);
            String d1=decrypt(e1, key);
           System.out.println("???key?????===>"+d1);
            
            
        //   ?????===>12AUism810jsqASI08
        	//	   ???key?????===>JWtfwlWtpNjyPIVTNXHmG9a9gyR8o0lU
        	//	   ???key?????===>12AUism810jsqASI08
       //    ?????===>12AUism810jsqASI08
        	//	   ???key?????===>/EyJSjHBoqbROfJ/arVGoC7EFP+qOIVS
        	//	   ???key?????===>12AUism810jsqASI08
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}