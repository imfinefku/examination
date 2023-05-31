package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.bean.*;
import com.common.annotation.NoLogin;
import com.common.util.JWTUtil;
import com.common.util.UserContextHolder;
import com.study.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 权限管理相关
 */
@RestController
@RequestMapping("authority")
public class AuthorityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);

    @Autowired
    private AuthorityService authorityService;

    /**
     * 分页获取角色
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getRolePage")
    public CommonResult getRolePage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<Role> dataList = authorityService.getRolePage(dataMap);
        int count = authorityService.getRoleCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("/getRoleAll")
    public CommonResult getRoleAll() {
        List<Role> dataList = authorityService.getRoleAll();
        return CommonResult.success(dataList);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("/getMenu")
    public CommonResult getMenu() {
        List<Menu> dataList = authorityService.getMenu();
        return CommonResult.success(dataList);
    }

    /**
     * 获取所有顶级菜单
     *
     * @return
     */
    @GetMapping("/getTopLevelMenuAll")
    public CommonResult getTopLevelMenuAll() {
        List<Menu> dataList = authorityService.getTopLevelMenuAll();
        return CommonResult.success(dataList);
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping("/getMenuTree")
    public CommonResult getMenuTree() {
        List<MenuTree> rtnList = new ArrayList<MenuTree>();
        List<Menu> dataList = authorityService.getTopLevelMenuAll();
        authorityService.setMenuTree(rtnList, dataList);
        return CommonResult.success(rtnList);
    }

    /**
     * 根据角色id获取角色的菜单权限
     *
     * @param roleid
     * @return
     */
    @GetMapping("/getRoleMenu")
    public CommonResult getRoleMenu(@RequestParam String roleid) {
        List<RoleMenu> dataList = authorityService.getRoleMenu(roleid, RoleMenu.ALL_SELECT);
        List<String> rtnList = new ArrayList<String>();
        for (RoleMenu roleMenu : dataList) {
            rtnList.add(roleMenu.getMenuid());
        }
        return CommonResult.success(rtnList);
    }


    /**
     * 分页获取用户信息
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getUserPage")
    public CommonResult getUserPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<User> dataList = authorityService.getUserPage(dataMap);
        int count = authorityService.getUserCount();
        return CommonResult.success(dataList, count);
    }


    /**
     * 登录
     *
     * @param user
     * @return
     */
    @NoLogin
    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        User curUser = authorityService.getUserByUsernamePassword(user);
        if (curUser == null) {
            return CommonResult.failed("账号或密码错误");
        }
        //更新最后登录时间
        authorityService.updateLastLoginTime(curUser.getId(), new Date());
        //返回token
        String token = JWTUtil.sign(curUser);
        curUser.setToken(token);
        return CommonResult.success(curUser);
    }

    /**
     * 获取用户的菜单树权限
     *
     * @return
     */
    @GetMapping("/getUserMenuList")
    public CommonResult getUserMenuList() {
        User curUser = UserContextHolder.getInstance();
        //获取用户的菜单树权限
        List<Menu> dataList = authorityService.getTopLevelMenuByUser(curUser.getId());
        List<MenuTree> rtnList = new ArrayList<MenuTree>();
        authorityService.setMenuTree(rtnList, dataList);
        return CommonResult.success(rtnList);
    }


    /**
     * 获取部门树
     *
     * @return
     */
    @GetMapping("/getDepartmentTree")
    public CommonResult getDepartmentTree() {
        List<Department> rtnList = authorityService.getDepartmentTree();
        return CommonResult.success(rtnList);
    }
}