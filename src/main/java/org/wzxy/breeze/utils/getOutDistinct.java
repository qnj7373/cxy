package org.wzxy.breeze.utils;


import org.wzxy.breeze.model.po.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-03
 */
public class getOutDistinct {
   List<menu> menuList=new ArrayList<menu>();

    public   List<menu> distinct(List<menu> menus){

        Map<menu,menu> map = new HashMap<>();
        for (menu parts:menus) {
            if (map.containsKey(parts)){
            }else {
                map.put(parts,parts);
            }
        }
        menuList.clear();
        System.err.println(map.values());
        for (menu key : map.values()) {
            menuList.add(key);
        }

        return menuList;

    }




}
