package com.pixiv.authority.controller;

import com.pixiv.authority.service.PermissionRouterService;
import com.pixiv.authority.service.RoleService;
import com.pixiv.authority.utils.AjaxResponse;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/security")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionRouterService permissionRouterService;

    @DeleteMapping("/roles")
    public AjaxResponse deleteCache(){
        roleService.deleteCache();
        return AjaxResponse.success();
    }

    @GetMapping(value = "/roles")
    public AjaxResponse getAll(){
        return AjaxResponse.success(roleService.findAll());
    }

    @GetMapping(value = "/roles/test")
    public AjaxResponse get(){
        return AjaxResponse.success();
    }


    @GetMapping(value = "/role")
    public AjaxResponse getPermissionByRoleId(String roleId){
        return AjaxResponse.success(roleService.findRoleAndRouterByRoleId(roleId));
    }

    @GetMapping(value = "/permissionRouters")
    public AjaxResponse getAllRouter(){
        return AjaxResponse.success(permissionRouterService.list());
    }


    @PutMapping(value = "/assignPermission/{id}")
    public  AjaxResponse assignRouters(@PathVariable String id, @RequestBody Map<String,Object> params)
    {
        String routerIds= (String) params.get("routers");

        String []array=routerIds.split(",");

        roleService.assignRouters(id,array);

        return AjaxResponse.success();
    }




}
