package cn.tedu.dao;

import cn.tedu.entity.User;
import org.springframework.transaction.annotation.Transactional;


public interface UserDao {

    int addUser(User user);

    int deleteUser(User user);

    int updateUser(User user);

    User findUserById(String id);

}
