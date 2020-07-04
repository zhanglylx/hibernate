package cn.tedu.service.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    @Transactional
    public void deleteUsers(String... ids) {
        for (String id : ids) {
            User user = userDao.findUserById(id);
            if (user == null) throw new RuntimeException("id错误" + id);
            userDao.deleteUser(user);
        }
    }
}
