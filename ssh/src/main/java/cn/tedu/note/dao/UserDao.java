package cn.tedu.note.dao;

import cn.tedu.note.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User findUserById(String id);

    User findUserByName(String name);

    List<Map<String, Object>> findUsersLikeName(String name);
}
