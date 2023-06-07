package com.study.service;

import com.common.bean.*;
import com.study.dao.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 权限管理相关
 */
@Service
public class AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    public List<Role> getRolePage(Map dataMap) {
        return authorityMapper.getRolePage(dataMap);
    }

    public int getRoleCount() {
        return authorityMapper.getRoleCount();
    }

    public List<Role> getRoleAll() {
        return authorityMapper.getRoleAll();
    }

    public List<Menu> getMenu() {
        List<Menu> allMenuList = authorityMapper.getMenu();
        List<Menu> topMenuList = new ArrayList<Menu>();
        setChildrenMenu(topMenuList, allMenuList);
        return topMenuList;
    }

    private void setChildrenMenu(List<Menu> topMenuList, List<Menu> allMenuList) {
        //给所有上级菜单赋予子级菜单
        for (Menu menu : topMenuList) {
            List<Menu> children = new ArrayList<Menu>();
        }
    }

    public List<Menu> getTopLevelMenuAll() {
        return authorityMapper.getTopLevelMenuAll();
    }

    public void setMenuTree(List<MenuTree> rtnList, List<Menu> dataList) {
        for (Menu menu : dataList) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(menu.getId());
            menuTree.setName(menu.getName());
            menuTree.setRoute(menu.getRoute());
            menuTree.setIcon(menu.getIcon());
            menuTree.setSort(menu.getSort());
            menuTree.setChildren(getChildrenMenuTree(menuTree.getId()));
            rtnList.add(menuTree);
        }
    }

    public List<MenuTree> getChildrenMenuTree(String masterid) {
        return authorityMapper.getChildrenMenuTree(masterid);
    }

    public List<RoleMenu> getRoleMenu(String roleid, int status) {
        return authorityMapper.getRoleMenu(roleid, status);
    }

    public List<User> getUserPage(Map dataMap) {
        List<User> userList = authorityMapper.getUserPage(dataMap);
        for (User user : userList) {
            List<UserRole> userRoleList = authorityMapper.getUserRoleByUserid(user.getId());
            user.setUserRoleList(userRoleList);
            List<String> userRoleLists = new ArrayList<>();
            user.setUserRoleLists(userRoleLists);
        }
        return userList;
    }

    public int getUserCount() {
        return authorityMapper.getUserCount();
    }

    public User getUserByUsernamePassword(User user) {
        return authorityMapper.getUserByUsernamePassword(user);
    }

    public int updateLastLoginTime(String id, Date lastlogintime) {
        return authorityMapper.updateLastLoginTime(id, lastlogintime);
    }

    public List<Menu> getTopLevelMenuByUser(String userid) {
        return authorityMapper.getTopLevelMenuByUser(userid);
    }

    public List<Department> getDepartmentTree() {
        List<Department> allDepartmentList = authorityMapper.getDepartmentAll();
        List<Department> topDepartmentList = new ArrayList<Department>();
        setChildrenDepartment(topDepartmentList, allDepartmentList);
        return topDepartmentList;
    }

    private void setChildrenDepartment(List<Department> topDepartmentList, List<Department> allDepartmentList) {
        //给所有上级部门赋予子级部门
        for (Department department : topDepartmentList) {
            List<Department> children = new ArrayList<Department>();
            for (Department childDepartment : allDepartmentList) {
                if (childDepartment.getMasterid() != null && department.getId().equals(childDepartment.getMasterid())) {
                    children.add(childDepartment);
                }
            }
        }
    }
}
