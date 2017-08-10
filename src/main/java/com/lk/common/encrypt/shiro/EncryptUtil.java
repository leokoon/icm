package com.lk.common.encrypt.shiro;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.util.Assert;

import java.security.Key;

/**
 * Created by Leo on 2017/7/2.
 */
public class EncryptUtil {
    public static void main(String[] args) {
        String str = "admin";
        String salt = "admin";
        String md5 = new Md5Hash(str, salt,1).toString();//还可以转换为 toBase64()/toHex()
        System.out.println(md5);

        str = "hello";
        salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();

        str = "hello";
        salt = "123";
        //内部使用MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();


        //1、首先创建一个DefaultHashService，默认使用SHA-512算法；
//
//        2、可以通过hashAlgorithmName属性修改算法；
//
//        3、可以通过privateSalt设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
//
//        4、可以通过generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐；
//
//        5、可以设置randomNumberGenerator用于生成公盐；
//
//        6、可以设置hashIterations属性来修改默认加密迭代次数；
//
//        7、需要构建一个HashRequest，传入算法、数据、公盐、迭代次数。
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();


        //SecureRandomNumberGenerator用于生成一个随机数：
        SecureRandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        hex = randomNumberGenerator.nextBytes().toHex();


        // AES
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); //设置key长度
        //生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        //加密
        String encrptText =
                aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 =
                new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        //Assert.assertEquals(text, text2);
    }
}
