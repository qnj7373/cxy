package org.wzxy.breeze.model.vo;

/**
 * @author 覃能健
 * @create 2020-05
 */
public class roleNodesForm {
    private  int roleId;
    private  String menuIds;

    public roleNodesForm() {
        super();
    }

    public roleNodesForm(int roleId, String menuIds) {
        this.roleId = roleId;
        this.menuIds = menuIds;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
