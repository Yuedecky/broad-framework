package com.broad.web.framework.constant;

/**
 * 权限相关字典常量类
 */
public class DictCodeConstant {


    /**
     * //男
     */
    public static final Integer USER_GENDER_CODE_0 = 0;

    /**
     * //女
     */
    public static final Integer USER_GENDER_CODE_1 = 1;

    /**
     * //未知
     */
    public static final Integer USER_GENDER_CODE_2 = 2;

    /**
     * //禁用
     */
    public static final Integer USER_STATUS_CODE_0 = 0;

    /**
     * //启用
     */
    public static final Integer USER_STATUS_CODE_1 = 1;

    /**
     * 权限实体- 角色
     */
    public static final Integer PERMISSION_ENTITY_CODE_1 = 1;

    /**
     * 权限实体- 角色实例
     */
    public static final Integer PERMISSION_ENTITY_CODE_2 = 2;


    /**
     * 权限实体-用户
     */
    public static final Integer PERMISSION_ENTITY_CODE_3 = 3;





    /**
     * 被授权的实体 - 菜单&按钮
     */
    public static final Integer PERMISSION_ACCESS_ENTITY_1 = 1;


    /**
     * 被授权的实体 -数据权限*（字典值DATA_PERMISSION_TYPE）
     */
    public static final Integer PERMISSION_ACCESS_ENTITY_2 = 2;



    /**
     * 角色实例状态 - 无效
     */
    public static final Integer USER_ROLE_STATE_0 = 0 ;

    /**
     * 角色实例状态 - 有效
     */
    public static final Integer USER_ROLE_STATE_1 = 1;


    /**
     *  机构状态 无效
     */
    public static final Integer ORG_STATE_0 = 0;


    /**
     *  机构状态 有效
     */
    public static final Integer ORG_STATE_1 = 1;



    /**
     * 组织机构 - 机构
     */
    public static final Integer ORG_TYPE_1 = 1;

    /**
     * 组织机构 - 部门
     */
    public static final Integer ORG_TYPE_2 = 2;


    /**
     * 数据权限 - 系统全部数据
     */
    public static final Integer DATA_PERMISSION_TYPE_1 = 1;

    /**
     * 数据权限 - 所属机构及本机构下属所有机构数据
     */
    public static final Integer DATA_PERMISSION_TYPE_2 = 2;

    /**
     * 数据权限 - 只所属构数据
     */
    public static final Integer DATA_PERMISSION_TYPE_3 = 3;

    /**
     * 数据权限 - 指定机构+指定机构下属机构数据
     */
    public static final Integer DATA_PERMISSION_TYPE_4 = 4;

    /**
     * 数据权限 - 指定机构数据
     */
    public static final Integer DATA_PERMISSION_TYPE_5 = 5;


    /**
     * 数据权限 - 自己负责的数据
     */
    public static final Integer DATA_PERMISSION_TYPE_6 = 6;


}
