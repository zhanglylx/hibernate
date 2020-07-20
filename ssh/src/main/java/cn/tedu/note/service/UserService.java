package cn.tedu.note.service;

import cn.tedu.note.entity.User;

public interface UserService {
    User login(String username, String password);
}
