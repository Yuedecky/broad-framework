package com.broad.web.framework.file;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午11:42-2020/5/15
 * @Last modified by:
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "broad.file")
@RefreshScope
public class FileProperties {

    /**
     * 为以下3个值，指定不同的自动化配置
     * qiniu：七牛oss
     * aliyun：阿里云oss
     */
    private FileStorageType type = FileStorageType.ALI;

    private AliOss ali;



    @Data
    public static class AliOss {
        private String uriPrefix;
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
    }





}
