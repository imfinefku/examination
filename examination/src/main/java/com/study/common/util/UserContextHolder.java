package com.study.common.util;

import com.study.bean.User;

/**
 * user上下文threadLocal操作类
 */
public class UserContextHolder {

    private static final ThreadLocal<User> userLocal = new ThreadLocal<User>();

    public static void setInstance(User user) {
        if (user != null) {
            userLocal.set(user);
        }
    }

    public static User getInstance() {
        return userLocal.get();
    }

    public static void removeInstance() {
        userLocal.remove();
    }

}
