package com.pixiv.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.authority.entity.PermissionApi;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.Role;
import com.pixiv.authority.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {




    /**
     *
     * @param id role的Id
     * @return
     */
    public Role selectPermissionRouterById(String id);




    @Select("Select * from role")
    public List<Role> getAll();

    @Delete("Delete from user_role where user_id=#{userId}")
    public void deleteRoleByUserId(String userId);



    @Insert("Insert into user_role (user_id,role_id) values (#{userId},#{roleId})")
    public void insertUser_Role(String userId,String roleId);




}
