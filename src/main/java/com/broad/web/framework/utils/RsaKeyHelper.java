package com.broad.web.framework.utils;

import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * 生成rsa工具類
 */
@Slf4j
public class RsaKeyHelper {

    private static final RsaKeyHelper INSTANCE = new RsaKeyHelper();

    private RsaKeyHelper() {

    }

    public static RsaKeyHelper builder() {
        return INSTANCE;
    }


    /**
     * 获取公钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(String filename) throws
            IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        InputStream resourceAsStream = this.getFromFileName(filename);
        assert resourceAsStream != null;
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private InputStream getFromFileName(String fileName) {
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * 获取密钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getFromFileName(filename);
        assert resourceAsStream != null;
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * 生存rsa公钥和密钥
     *
     * @param publicKeyFilename
     * @param privateKeyFilename
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void generateKey(String publicKeyFilename, String privateKeyFilename, String password) throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
        fos.write(publicKeyBytes);
        fos.close();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        fos = new FileOutputStream(privateKeyFilename);
        fos.write(privateKeyBytes);
        fos.close();
    }


    /**
     * RSA签名      SHA1withRSA
     *
     * @param content    待签名的内容
     * @param privateKey 私钥
     * @return 签名结果
     */
    public static byte[] sign(String content, PrivateKey privateKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (Objects.isNull(privateKey)) {
            return null;
        }
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(content.getBytes("utf-8"));
            byte[] result = signature.sign();
            return result;
        } catch (Exception e) {
            log.error("RSA签名错误:{}", e);
        }
        return null;
    }


    /**
     * RSA签名  MD5withRSA
     *
     * @param content
     * @param privateKey
     * @return
     */
    public static String MD5WithRSASign(String content, PrivateKey privateKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (null == privateKey) {
            return null;
        }
        Signature signature = null;
        try {
            signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(content.getBytes(Charsets.UTF_8));
            byte[] result = signature.sign();
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            log.error("MD5withRSA,生成Sign出错:{}", e);
        }
        return null;
    }

}
