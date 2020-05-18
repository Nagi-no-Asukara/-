package com.pixiv.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.authority.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface UserMapper  extends BaseMapper<User> {


    public User SelectUserAndRoleById(String id);

    public User SelectUserAndRoleByUsername(String username);

    @Select("Select id,username,password,name,level from user where username=#{username}")
    public User selectByUsername(@Param("username") String username);


    @Insert("insert into user_role(user_id,role_id) values (#{userId},#{roleId})")
    @Options(useGeneratedKeys = true)
    public boolean insertUser_role(String userId,String roleId);


    @Select("Select username,password,name,level,avatar from user where id=#{id}")
    public User selectSimpleUserById(@Param("id") String id);

    @Select("Select * from user where username=#{username}")
    public User selectSimpleUserByUsername( String username);

    @Select("Select * from user")
    public List<User> findAll();



}

