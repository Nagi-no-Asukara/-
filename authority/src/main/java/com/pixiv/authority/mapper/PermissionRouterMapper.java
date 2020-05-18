package com.pixiv.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.pixiv.authority.entity.PermissionRouter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface PermissionRouterMapper  extends BaseMapper<PermissionRouter> {


    @Delete("Delete from permission_role where role_id=#{id}")
    public void deletePermissionByRoleId(String id);

    @Insert("Insert into permission_role (role_id,permission_id) values (#{role_id},#{permission_id}")
    public void insertPermission_Role(String role_id,String permission_id);
}
