package com.broad.web.framework.file;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.broad.web.framework.context.CommonContextHolder;
import com.broad.web.framework.tool.StrPool;
import com.broad.web.framework.utils.DateUtils;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午1:37-2020/5/16
 * @Last modified by:
 */
@Configuration
@Slf4j
@ConditionalOnProperty(name = "broad.file.type", havingValue = "ALI")
@EnableConfigurationProperties(value = FileProperties.class)
public class AliOssFileStrategyImpl extends AbstractFileStrategy {


    @Override
    protected String uploadFile(MultipartFile multipartFile) throws Exception {
        FileProperties.AliOss ali = fileProperties.getAli();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                ali.getAccessKeySecret());
        String bucketName = ali.getBucketName();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }
        //生成文件名
        String fileName = StrUtil.join(StrPool.EMPTY, UUID.randomUUID().toString(), StrPool.DOT,
                Files.getFileExtension(multipartFile.getOriginalFilename()));
        //日期文件夹
        String tenant = CommonContextHolder.getTenant();
        String relativePath = tenant + StrPool.SLASH + LocalDate.now().format(DateTimeFormatter.ofPattern(DateUtils.PATTERN_YYYY_SLASH_MM));
        // web服务器存放的绝对路径
        String relativeFileName = relativePath + StrPool.SLASH + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition("attachment;fileName=" + multipartFile.getOriginalFilename());
        metadata.setContentType(multipartFile.getContentType());
        PutObjectRequest request = new PutObjectRequest(ali.getBucketName(), relativeFileName, multipartFile.getInputStream(), metadata);
        PutObjectResult result = ossClient.putObject(request);
        log.info("result={}", JSONObject.toJSONString(result));
        String url = ali.getUriPrefix() + relativeFileName;
        ossClient.shutdown();
        return url;
    }

    @Override
    public String upload(File file) {
        FileProperties.AliOss ali = fileProperties.getAli();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                ali.getAccessKeySecret());
        String bucketName = ali.getBucketName();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        String fileName = file.getName();
        // 获取文件的后缀名
        String suffixName = Files.getFileExtension(fileName);
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + "/" + finalFileName;

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentDisposition("attachment;");

        ossClient.putObject(bucketName, objectName, file, meta);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        ossClient.shutdown();
        return url.toString();
    }
}
