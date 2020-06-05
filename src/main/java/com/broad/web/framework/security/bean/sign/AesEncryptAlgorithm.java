package com.broad.web.framework.security.bean.sign;


import com.broad.web.framework.utils.AesEncryptUtils;

/**
 * @author broad
 */
public class AesEncryptAlgorithm implements EncryptAlgorithm {

    /**
     * 加密
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    @Override
    public String encrypt(String content, String encryptKey) throws Exception {
        return AesEncryptUtils.aesEncrypt(content, encryptKey);
    }

    /**
     * 解密
     * @param encryptStr
     * @param decryptKey
     * @return
     * @throws Exception
     */
    @Override
    public String decrypt(String encryptStr, String decryptKey) throws Exception {
        return AesEncryptUtils.aesDecrypt(encryptStr, decryptKey);
    }
}
