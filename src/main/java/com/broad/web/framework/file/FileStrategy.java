package com.broad.web.framework.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午11:38-2020/5/15
 * @Last modified by:
 */
public interface FileStrategy {


    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件对象
     * @author zuihou
     * @date 2019-05-06 16:38
     */
    String upload(MultipartFile file);


    /**
     * @param file
     */
    String upload(File file);


}
