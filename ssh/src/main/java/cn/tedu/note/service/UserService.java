package cn.tedu.note.service;

import cn.tedu.note.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(String username, String password);

    List<Map<String, Object>> listUsers(String name);
}
