package com.broad.web.framework.security.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {

    private Long roleId;

    private String roleName;

    private String status;

    private Long tenantId;


}
