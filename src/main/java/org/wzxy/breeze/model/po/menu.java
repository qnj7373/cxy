package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-04
 */

//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class menu implements Serializable {
    private  String menuId;
    private  String menuName;
    private  String menuPid;
    private  String url;

    public menu() {
        super();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        //这里的code需要改成你要判断如重复的属性名称
        result = prime * result + ((menuId == null)?0:menuId.hashCode());
        return result;
    }

    /**
     * 重写equals方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }if(obj == null){
            return false;
        }if (getClass() != obj.getClass()){
            return false;
        }
        //Parts是我的实体类名称
        menu menu = (menu) obj;
        //code改成你需要的属性名
        if(menuId == null){
            if(menu.menuId != null){
                return false;
            }
        }else if(!menuId.equals(menu.menuId)){
            return false;
        }
        return true;
    }

}

