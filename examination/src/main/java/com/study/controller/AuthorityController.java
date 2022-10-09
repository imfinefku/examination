package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.*;
import com.study.common.annotation.NoLogin;
import com.study.common.bean.CommonResult;
import com.study.common.util.JWTUtil;
import com.study.common.util.UserContextHolder;
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
     * 添加角色
     *
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public CommonResult addRole(@RequestBody @Valid Role role, BindingResult validResult) {
        Date date = new Date();
        role.setId(UUID.randomUUID().toString());
        role.setCreatetime(date);
        int result = authorityService.addRole(role);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PostMapping("/updateRole")
    public CommonResult updateRole(@RequestBody @Valid Role role, BindingResult validResult) {
        int result = authorityService.updateRole(role);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除角色
     *
     * @param role
     * @return
     */
    @PostMapping("/deleteRole")
    public CommonResult deleteRole(@RequestBody Role role) {
        int result = authorityService.deleteRole(role.getId());
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

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
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/addMenu")
    public CommonResult addMenu(@RequestBody @Valid Menu menu, BindingResult validResult) {
        Date date = new Date();
        menu.setId(UUID.randomUUID().toString());
        menu.setCreatetime(date);
        int result = authorityService.addMenu(menu);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/updateMenu")
    public CommonResult updateMenu(@RequestBody @Valid Menu menu, BindingResult validResult) {
        int result = authorityService.updateMenu(menu);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/deleteMenu")
    public CommonResult deleteMenu(@RequestBody Menu menu) {
        int result = authorityService.deleteMenu(menu);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
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
     * 添加角色菜单权限
     *
     * @param roleMenuList
     * @return
     */
    @PostMapping("/addRoleMenu")
    public CommonResult addRoleMenu(@RequestBody @Valid RoleMenuList roleMenuList, BindingResult validResult) {
        authorityService.addRoleMenu(roleMenuList);
        return CommonResult.success();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public CommonResult addUser(@RequestBody @Valid User user, BindingResult validResult) {
        if (authorityService.checkUsernameRepeat(user.getUsername())) {
            return CommonResult.failed("已存在相同用户名");
        }
        authorityService.addUser(user);
        return CommonResult.success();
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public CommonResult updateUser(@RequestBody @Valid User user, BindingResult validResult) {
        authorityService.updateUser(user);
        return CommonResult.success();
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
     * 删除用户
     *
     * @param user
     * @return
     */
    @PostMapping("/deleteUser")
    public CommonResult deleteUser(@RequestBody User user) {
        authorityService.deleteUser(user.getId());
        return CommonResult.success();
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
     * 修改密码
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(@RequestBody JSONObject jsonObject) {
        String oldPassword = jsonObject.get("oldPassword") == null ? "" : jsonObject.get("oldPassword").toString();
        String newPassword = jsonObject.get("newPassword") == null ? "" : jsonObject.get("newPassword").toString();
        String repeatNewPassword = jsonObject.get("repeatNewPassword") == null ? "" : jsonObject.get("repeatNewPassword").toString();
        if ("".equals(oldPassword) || "".equals(newPassword) || "".equals(repeatNewPassword)) {
            return CommonResult.failed("密码不能为空");
        }
        if (!newPassword.equals(repeatNewPassword)) {
            return CommonResult.failed("两次新密码输入不一致");
        }
        User curUser = authorityService.getUserById(UserContextHolder.getInstance().getId());
        if (!oldPassword.equals(curUser.getPassword())) {
            return CommonResult.failed("原密码输入错误");
        }
        int result = authorityService.updatePassword(curUser.getId(), newPassword);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
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
     * 添加部门
     *
     * @param department
     * @param validResult
     * @return
     */
    @PostMapping("/addDepartment")
    public CommonResult addDepartment(@RequestBody @Valid Department department, BindingResult validResult) {
        department.setId(UUID.randomUUID().toString());
        department.setCreatetime(new Date());
        int result = authorityService.addDepartment(department);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改部门
     *
     * @param department
     * @param validResult
     * @return
     */
    @PostMapping("/updateDepartment")
    public CommonResult updateDepartment(@RequestBody @Valid Department department, BindingResult validResult) {
        int result = authorityService.updateDepartment(department);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除部门
     *
     * @param department
     * @return
     */
    @PostMapping("/deleteDepartment")
    public CommonResult deleteDepartment(@RequestBody Department department) {
        int result = authorityService.deleteDepartment(department.getId());
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
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