package com.study.dao;

import com.study.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AuthorityMapper {

    public int addRole(Role role);

    public int updateRole(Role role);

    public int deleteRole(@Param("id") String id);

    public List<Role> getRolePage(Map dataMap);

    public int getRoleCount();

    public List<Role> getRoleAll();

    public int addMenu(Menu menu);

    public int updateMenu(Menu menu);

    public int deleteMenu(Menu menu);

    public List<Menu> getMenu();

    public List<Menu> getTopLevelMenuAll();

    public List<MenuTree> getChildrenMenuTree(@Param("masterid") String masterid);

    public List<RoleMenu> getRoleMenu(@Param("roleid") String roleid, @Param("status") int status);

    public int deleteRoleMenu(@Param("roleid") String roleid);

    public int addRoleMenuList(List<RoleMenu> roleMenuList);

    public int addUser(User user);

    public int updateUser(User user);

    public int addUserRole(List<UserRole> userRoleList);

    public List<User> getUserPage(Map dataMap);

    public List<UserRole> getUserRoleByUserid(@Param("userid") String userid);

    public int deleteUser(@Param("id") String id);

    public int deleteUserRole(@Param("userid") String userid);

    public int getUserCount();

    public User getUserByUsernamePassword(User user);

    public int updateLastLoginTime(@Param("id") String id, @Param("lastlogintime") Date lastlogintime);

    public List<Menu> getTopLevelMenuByUser(@Param("userid") String userid);

    public int updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);

    public User getUserById(@Param("id") String id);

    public int checkUsernameRepeat(@Param("username") String username);

    public int addDepartment(Department department);

    public int updateDepartment(Department department);

    public int deleteDepartment(@Param("id") String id);

    public List<Department> getDepartmentAll();
}