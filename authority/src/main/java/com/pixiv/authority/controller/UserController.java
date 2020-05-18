package com.pixiv.authority.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pixiv.authority.bean.RouterResult;
import com.pixiv.authority.bean.UserResult;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.Role;
import com.pixiv.authority.mapper.RoleMapper;
import com.pixiv.authority.service.PermissionRouterService;
import com.pixiv.authority.service.RoleService;
import com.pixiv.authority.service.UserService;
import com.pixiv.authority.config.exception.CommonException;
import com.pixiv.authority.config.exception.CommonExceptionType;
import com.pixiv.authority.entity.User;
import com.pixiv.authority.mapper.UserMapper;
import com.pixiv.authority.utils.AjaxResponse;
import com.pixiv.authority.config.JWT.JwtToken;
import com.pixiv.authority.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/security")
@Slf4j
/**
 * 管理员用户管理
 * 用户信息修改以及获取用户权限
 */
public class UserController  {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionRouterService permissionRouterService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    JwtToken jwtToken;

    //采用注解形式的api 没有权限的时候不会跳转至在shiro过滤器中设置的页面 而是会抛出异常

    /*jwt方式的登录
     * 登录成功之后获取jwt 返回前端
*/  /*
    @PostMapping(value = "/login")
    public AjaxResponse login(@RequestBody Map<String,String> Map){

        String username=Map.get("username");
        String password=Map.get("password");


        String token=userService.login(username,password);
        if(token==null)
            return AjaxResponse.error(new CommonException(CommonExceptionType.USER_INPUT_ERROR));
        else
            return AjaxResponse.success(token);
    }*/

    @GetMapping(value = "/delete")
    public AjaxResponse deleteCache(){

        userService.deleteCache();
        return AjaxResponse.success();
    }

    @RequestMapping(value = "/login")
    public AjaxResponse login(@RequestBody User user){


        String username=user.getUsername();
        String password=user.getPassword();

            //Md5盐值加密
           // password=new Md5Hash(password,username,3).toString();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            Subject subject = SecurityUtils.getSubject();

            String sid=(String)subject.getSession().getId();

            subject.login(token);
            //login过了说明账号密码没错

            User user1=userMapper.selectSimpleUserByUsername(username);

           // Set<PermissionRouter> routers=new HashSet<>();

            /*for(Role role:user1.getRoleList()) {
                routers.addAll(roleService.findRoleAndRouterByRoleId(role.getId()).getPermissionRouterList());
            }*/

            Map<String,Object> map=new HashMap<>();
            map.put("id",user1.getId());
            map.put("sid",sid);

            //这个getSession方法其实是自己SessionManager中定义的session方法

            return AjaxResponse.success( jwtToken.createJwt(map));


    }


    /**
     * 通过jwt查询获取权限路由
     * @return
     */
    @RequestMapping(value = "/profile")
    public AjaxResponse profile(HttpServletRequest request){


        String id= getIdByJwtToken(request);
        //token接收成功
        User user=userService.getUserAndRoleById(id);

        Set<PermissionRouter> list=new HashSet<>();

        for(Role role:user.getRoleList()){
            list.addAll(roleMapper.selectPermissionRouterById(role.getId()).getPermissionRouterList());
        }

           RouterResult result=new RouterResult(list,user.getRoleList());
            //result=new ProfileResult(user,list);

        return AjaxResponse.success(result);
    }


     /*
      shiro的profile方法

    @GetMapping(value = "/profile")
    public AjaxResponse profile(HttpServletRequest request){

        Subject subject=SecurityUtils.getSubject();

        PrincipalCollection principals=subject.getPreviousPrincipals();


        if(principals!=null&&!principals.isEmpty()) {
             User user = (User) principals.getPrimaryPrincipal();   //疑问 有多台主机 它是怎么识别的？ sessionId?
            return AjaxResponse.success(user);
        }

        return AjaxResponse.error(new CommonException(CommonExceptionType.SYSTEM_ERROR));


    }*/


    @GetMapping(value="/point")
    public AjaxResponse point(HttpServletRequest request){

        //return permissionService.
        return  AjaxResponse.success();
    }

    //用户基本信息crud
    @GetMapping(value = "/users")
    public AjaxResponse findAll(Integer pageNum,Integer pageSize){

        //String orderBy = "id desc";
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userService.findAll());
        UserResult result=new UserResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setPageSize(pageInfo.getPageSize());
        result.setUserList(pageInfo.getList());
        return AjaxResponse.success(result);
    }

    @GetMapping(value = "/user")
    public AjaxResponse findById(String id){

        return AjaxResponse.success(userService.getUserAndRoleById(id));
    }

    /**
     * 添加or修改user 即注册用户
     */
    @PostMapping(value = "/user")
    public AjaxResponse save(@RequestBody User user) {
        user.setId(IDUtils.createID());
        userService.insertUser(user);
        return AjaxResponse.success();
    }

    @DeleteMapping(value = "/user/{id}")
    public AjaxResponse delete(@PathVariable String id){

        userService.delete(id);
        return AjaxResponse.success();
    }

    //接下来是查看用户权限

    @GetMapping(value = "/user/roles")
    public  AjaxResponse Roles(String id)
    {
        return AjaxResponse.success(userMapper.SelectUserAndRoleById(id).getRoleList());
    }

    @PostMapping(value = "/user/roles/{id}")
    public  AjaxResponse assignRoles(@PathVariable String id, @RequestBody Map<String,Object> params){

        List<String> arrayList= (List<String>) params.get("roles");

        //String []array1=roleName.split(",");
        userService.assignRoles(id, arrayList);
        return AjaxResponse.success();
    }


    /**
     * 通过jwt查询用户信息
     * @return
     */
    @GetMapping(value = "/info")
    public AjaxResponse UserInfo(HttpServletRequest request){

        String id=getIdByJwtToken(request);

        //token接收成功
        User user=userService.getUserAndRoleById(id);

        return AjaxResponse.success(user);

    }





    private String getIdByJwtToken(HttpServletRequest request)
    {
        String token=request.getHeader("Authorization");

        if (StringUtils.isEmpty(token))
        {
            throw new CommonException(CommonExceptionType.Authority_ERROR);//还要做权限捕获处理
        }

        return (String) jwtToken.jwtParse(token).get("id");
    }
}
