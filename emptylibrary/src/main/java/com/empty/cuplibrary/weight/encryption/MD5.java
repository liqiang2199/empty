package com.empty.cuplibrary.weight.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    private static MD5 md5;

    //IntentMange管理
    public MD5(){

        md5 = this;
    }

    public static MD5 getApplication(){
        return md5;
    }

    public static MD5 getIstance(){
        if(md5==null){
            md5 = new MD5();
        }
        return md5;
    }


    public String ToUpCase(String pass){
        if (pass == null&&pass.equals("")){
            return "";
        }

       String caseto =  encryption(pass);

        return caseto.toUpperCase();
    }

    //转小写
    public String ToLowerCase(String alias){

        return alias.toLowerCase();
    }

    //加密在转小写
    public String MD5ToLowerCase(String alias){
        String caseto =  encryption(alias);

        return caseto.toLowerCase();
    }

    /**
     *
     * @param plainText
     *            明文
     * @return 32位密文
     */
    public String encryption(String plainText) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
//        String s = new String(a);
        return new String(a);

    }


}
