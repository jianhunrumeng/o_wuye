package com.wuye.common.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuVo {
    /**
     * 菜单Id。
     */
    private int menuId;
    /**
     * 菜单路径。
     */
    private String menuPath;
    /**
     * 菜单名称.
     */
    private String menuName;
    /**
     * 下级菜单
     */
    private List<MenuVo> childMenus = new ArrayList<MenuVo>();

    public List<MenuVo> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenuVo> childMenus) {
        this.childMenus = childMenus;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
