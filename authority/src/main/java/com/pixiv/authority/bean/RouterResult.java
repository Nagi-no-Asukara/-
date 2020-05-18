package com.pixiv.authority.bean;

import com.pixiv.authority.entity.Permission;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class RouterResult implements Serializable {

    private Set<MenuList> menu=new HashSet<>();

    private List<Role> roles;

    private static String menu_id1="图片管理";

    private static String menu_id2="用户管理";

    private static String menu_id3="权限管理";

    private static String menu_id4="页面管理";

    public RouterResult(Set<PermissionRouter> list, List<Role> roles)
    {
      
        MenuList menuList1=new MenuList(menu_id1);
        MenuList menuList2=new MenuList(menu_id2);
        MenuList menuList3=new MenuList(menu_id3);
        MenuList menuList4=new MenuList(menu_id4);

       for(PermissionRouter router:list)
       {



           if(router.getMenuId()==1)
           {
               menuList1.getRouterList().add(router);
           }
           else if (router.getMenuId()==2){
               menuList2.getRouterList().add(router);
           }
           else if (router.getMenuId()==3){
               menuList3.getRouterList().add(router);
           }
           else if (router.getMenuId()==4){
               menuList4.getRouterList().add(router);
           }
       }

        add(menuList1);
        add(menuList2);
        add(menuList3);
        add(menuList4);
        this.roles=roles;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("menu",this.menu);
        map.put("roles",this.roles);
        return  map;
    }

    private void add(MenuList menuList)
    {
        if (menuList.getRouterList().size()!=0)
        {
            this.menu.add(menuList);
        }
    }


}
