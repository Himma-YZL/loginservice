package com.api.login.apilogin.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class EncryptUtil {

    private static int hashIterations = 9;



    /**
     * MD5加密
     * @param userName
     * @param password
     * @return
     */
    public static String md5Password(String userName, String password){
        // 加密算法MD5
        // 加密数据
        // salt盐 "username + salt"(可以自行定义)
        // 迭代次数
        String md5Password = new SimpleHash("MD5",password, ByteSource.Util.bytes(userName+"salt"),hashIterations).toHex();
        return md5Password;
    }

    /**
     * SHA-1加密
     * @param userName
     * @param password
     * @return
     */
    public static String sha1Password(String userName,String password){
        // 加密算法sha1
        // 加密数据
        // salt盐 "username + salt"(可以自行定义)
        // 迭代次数
        String sha1Password = new SimpleHash("SHA-1",password, ByteSource.Util.bytes(userName+"salt"),hashIterations).toHex();
        return sha1Password;
    }

    /**
     * SHA-256加密
     * @param userName
     * @param password
     * @return
     */
    public static String sha256Password(String userName,String password){
        // 加密算法sha256
        // 加密数据
        // salt盐 "username + salt"(可以自行定义)
        // 迭代次数
        String sha256Password = new SimpleHash("SHA-256",password, ByteSource.Util.bytes(userName+"salt"),hashIterations).toHex();
        return sha256Password;
    }

    /**
     * SHA-512加密
     * @param userName
     * @param password
     * @return
     */
    public static String sha512Password(String userName,String password){
        // 加密算法sha512
        // 加密数据
        // salt盐 "username + salt"(可以自行定义)
        // 迭代次数
        String sha256Password = new SimpleHash("SHA-512",password, ByteSource.Util.bytes(userName+"salt"),hashIterations).toHex();
        return sha256Password;
    }

    public static void main(String[] args) {
        String userName = "user";
        String passwotd = "123456";

        String md2 = md5Password(userName,passwotd);
        String sha1 = sha1Password(userName,passwotd);
        String sha256 = sha256Password(userName,passwotd);
        String sha512 = sha512Password(userName,passwotd);
        System.out.println("md2->"+md2);
        System.out.println("sha1->"+sha1);
        System.out.println("sha256->"+sha256);
        System.out.println("sha512->"+sha512);
    }
}
