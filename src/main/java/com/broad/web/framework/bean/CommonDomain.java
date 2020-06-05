package com.broad.web.framework.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.broad.web.framework.exception.BaseException;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.broad.web.framework.constant.ReturnCode;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CommonDomain implements Serializable, Cloneable {


    public static final String FIELD_ID = "id";
    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";


    public static final String CREATED_BY = "createdBy";
    public static final String UPDATED_BY = "updatedBy";

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id必须", groups = CommonDomain.Update.class)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private String createdBy;

    @ApiModelProperty(value = "最后修改人")
    private String updatedBy;


    private Date createdTime;


    @ApiModelProperty(value = "最后修改时间")
    private Date updatedTime;


    private Boolean deleted;


    @Override
    public Object clone() {
        //支持克隆  提高性能  仅仅是浅克隆
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new BaseException(e.getMessage(), ReturnCode.ERROR);
        }
    }

    /**
     * 保存和缺省验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新和缺省验证组
     */
    public interface Update extends Default {

    }

}
