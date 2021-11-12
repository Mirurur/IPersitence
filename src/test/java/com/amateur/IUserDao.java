package com.amateur;

import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/12 16:06
 */
public interface IUserDao {
    List<User> findAll();

    User findById(User user);
}
