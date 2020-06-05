package com.broad.web.framework.enums;

/**
 * 菜单 or 按钮
 */
public enum MenuType {

    MENU("菜单"), //菜单
    BUTTON("按钮"), //按钮
    ALL("全部")
    ;

    private String menuName;

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    MenuType(String menuName) {
        this.menuName = menuName;
    }
}
