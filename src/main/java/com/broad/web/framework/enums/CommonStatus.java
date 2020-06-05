package com.broad.web.framework.enums;

/**
 *
 */
public enum CommonStatus {

    /**
     * 禁用
     */
    DISABLED(0, "禁用"),
    ENABLE(1, "启用"),
    UNKNOWN(2, "未知"),
    ;

    private int code;

    private String name;

    CommonStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static boolean validateValue(String value) {
        for (CommonStatus status : CommonStatus.values()) {
            if (status.name.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param code
     * @return
     */
    public static CommonStatus fromCode(int code) {
        for (CommonStatus status : CommonStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}
