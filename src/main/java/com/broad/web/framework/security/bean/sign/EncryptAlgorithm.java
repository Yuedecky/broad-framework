package com.broad.web.framework.security.bean.sign;

public interface EncryptAlgorithm {

    /**
     * 加密
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    String encrypt(String content, String encryptKey) throws Exception;

    /**
     * 解密
     * @param encryptStr
     * @param decryptKey
     * @return
     * @throws Exception
     */
    String decrypt(String encryptStr, String decryptKey) throws Exception;
}
