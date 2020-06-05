package com.broad.web.framework.generator;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.dto.OutPageDto;
import com.broad.web.framework.dto.PageDto;
import com.broad.web.framework.exception.BaseException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author broad
 * 分页查询生成器
 */
public class PageOutGenerator<T> {

    private PageOutGenerator() {
    }


    /**
     * 组装分页参数
     *
     * @param pageDto
     * @param <T>
     * @return
     */
    public static <T> IPage<T> fromPageDto(PageDto pageDto) {
        if (Objects.isNull(pageDto) || pageDto.getPageNum() < 0 || pageDto.getPageSize() < 0) {
            throw new BaseException(ReturnCode.INVALID_PAGE_PARAMS);
        }
        return new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
    }


    /**
     * 生成分页对象
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> OutPageDto<T> generate(IPage<T> page) {
        final OutPageDto<T> result = new OutPageDto<>();
        if (CollectionUtils.isEmpty(page.getRecords())) {
            result.setList(new ArrayList<>());
            result.setTotalCount(0);
        } else {
            result.setList(page.getRecords());
            result.setTotalCount(page.getTotal());
        }
        return result;
    }


    public static <T> OutPageDto<T> success(List<T> list, long total) {
        final OutPageDto<T> result = new OutPageDto<>();
        if (CollectionUtils.isEmpty(list)) {
            result.setList(new ArrayList<>());
            result.setTotalCount(0);
        } else {
            result.setList(list);
            result.setTotalCount(total);
        }
        return result;
    }


}
