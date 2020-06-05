package com.broad.web.framework.constant;

/**
 * @author broad
 */
public class ReturnCode {


    public static final String[] USER_NOT_EXISTS = {"500", "用户名或者密码错误"};

    public static final String[] TREE_HEIGHT_INVALID = {"500", "菜单树高度超出限制"};

    public static final String[] PARENT_MENU_NOT_EXISTS = {"500", "父节点不存在"};

    public static final String[] NULL_POINT_EXCEPTION = {"500", "空指针异常!"};

    public static final String[] SYSTEM_ERROR = {"500", "系统错误，请联系管理员！"};

    public static final String[] METHOD_ERROR = {"400", "请求方式出错！"};

    public static final String[] JSON_DATA_ERROR = {"400", "请求参数有问题！"};

    public static final String[] USER_TOKEN_ERROR = {"401", "用户token错误！"};

    public static final String[] USER_TOKEN_EXPIRED = {"402", "用户token过期了！"};

    public static final String[] USER_TOKEN_NULL = {"401", "用户token是空的！"};

    public static final String[] METHOD_NOT_FOUND = {"400", "方法不存在！"};

    public static final String[] METHOD_ARGUMENT_INVALID = {"400", "方法参数错误:"};

    public static final String[] GENERATE_TOKEN_ERROR = {"500", "生成token异常！"};

    public static final String[] ERROR_ACCOUNT_NOT_EXISTS = {"500", "账号不存在"};


    public static final String[] METHOD_NOT_ALLOW = {"401", "禁止访问该方法"};

    public static final String[] INVALID_PERMISSION_ACCESS = {"401", "非法权限访问"};

    public static final String[] NEED_LOGIN = {"401", "需要登录"};

    public static final String[] AUTH_ROLE_FAILED = {"401", "授权失败"};

    public static final String[] SERVICE_ERROR = {"500", "业务异常"};


    public static final String[] LOGIN_PASSWORD_ERROR = {"500", "用户名或者密码错误"};

    public static final String[] CURRENT_USER_NO_ROLE = {"500", "当前用户无绑定角色，无法登录"};

    public static final String[] PLAT_NOT_EXISTS = {"500", "当前平台不存在"};

    public static final String[] PLAT_MENU_NOT_EXISTS = {"500", "当前平台尚未关联菜单"};

    public static final String[] MENU_NOT_EXISTS = {"500", "菜单不存在"};

    public static final String[] TENANT_NOT_EXISTS = {"500", "商户不存在"};

    public static final String[] USER_HAS_NO_MENU = {"500", "用户没分配菜单权限"};


    public static final String[] ROLE_REQUIRED = {"500", "角色必须"};

    public static final String[] NO_PERMISSION = {"401", "无权访问"};

    public static final String[] DUP_RECORD = {"400", "提交重复记录"};


    public static final String[] INVALID_PARAMS = {"400", "非法参数"};

    public static final String[] INVALID_PAGE_PARAMS = {"400", "分页参数非法"};


    public static final String[] REQUEST_CURRENCY_OVERLOAD = {"400", "请求次数过于频繁"};


    public static final String[] RPC_ERROR = {"500", "远程调用服务异常"};

    public static final String[] NEED_PRIMARY_ACCOUNT = {"400", "需要主帐号操作权限"};


    public static final String[] ENTITY_NON_EXISTS = {"400", "查找的实体不存在"};


    public static final String[] FILE_SUFFIX_REQUIRED = {"400", "文件后缀名称必须"};

    public static final String[] FILE_NAME_INVALID = {"400", "非法的文件名称"};

    /**
     * 请求正常
     */
    public static final String OK = "200";
    /**
     * 请求未通过校验
     */
    public static final String NO = "400";
    /**
     * 请求发生异常
     */
    public static final String ERROR = "500";

}
