package com.pixiv.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.authority.entity.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {


    public List<Permission> selectChildrenRoute(String id);

    public PermissionMenu selectMeanByRouter(PermissionRouter router);


}
