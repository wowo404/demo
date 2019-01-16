package org.liu.encryptdecrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;

import org.liu.enums.AnimalType;
import org.liu.model.Animal;

import com.alibaba.fastjson.JSON;

/**
 * Created by hello on 2017/11/9.
 */
public class RSACryptography {
    public static String data="{a:a}";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("小强");
        animal.setAge(12);
        animal.setBirthday(new Date());
        animal.setAnimalType(AnimalType.LAND);
        String json = JSON.toJSONString(animal);
        System.out.println(json);
        KeyPair keyPair=genKeyPair(1024);

        //获取公钥，并以base64格式打印出来
        PublicKey publicKey=keyPair.getPublic();
        System.out.println("公钥："+new String(Base64.getEncoder().encode(publicKey.getEncoded())));

        //获取私钥，并以base64格式打印出来
        PrivateKey privateKey=keyPair.getPrivate();
        System.out.println("私钥："+new String(Base64.getEncoder().encode(privateKey.getEncoded())));

        //公钥加密
        byte[] encryptedBytes=encrypt(json.getBytes(), publicKey);
        System.out.println("加密后："+new String(Base64.getEncoder().encode(encryptedBytes)));

        //私钥解密
        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
        System.out.println("解密后："+new String(decryptedBytes));
    }

    //生成密钥对
    public static KeyPair genKeyPair(int keyLength) throws Exception{
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom random = new SecureRandom();
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLength, random);
        return keyPairGenerator.generateKeyPair();
    }

    //公钥加密
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }
}
