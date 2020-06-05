package com.broad.web.framework.file;

import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午11:41-2020/5/15
 * @Last modified by:
 */
@Slf4j
public abstract class AbstractFileStrategy implements FileStrategy {

    private static final String FILE_SPLIT = ".";

    private static final String INVALID_SEPARATOR = "..";

    @Autowired
    protected FileProperties fileProperties;

    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     */
    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (!multipartFile.getOriginalFilename().contains(FILE_SPLIT)) {
                throw new BaseException(ReturnCode.FILE_SUFFIX_REQUIRED);
            }
            if (fileName.contains(INVALID_SEPARATOR)) {
                throw new BaseException(ReturnCode.FILE_NAME_INVALID);
            }
            return uploadFile(multipartFile);
        } catch (Exception e) {
            log.error("上传文件失败,message={},e={}", e.getMessage(), e);
            throw BaseException.wrap(FileBaseCode.UPLOAD_FAIL);
        }
    }

    /**
     * 具体类型执行上传操作
     *
     * @param multipartFile
     * @throws Exception
     */
    protected abstract String uploadFile(MultipartFile multipartFile) throws Exception;


}
