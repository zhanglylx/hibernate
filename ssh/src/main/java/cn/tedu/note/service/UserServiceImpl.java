package cn.tedu.note.service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User login(String username, String password) {
        User user = this.userDao.findUserByName(username);
        if (user == null) throw new RuntimeException("用户名错误");
        if (!user.getPassword().equals(password)) throw new RuntimeException("密码错误");
        return user;
    }
}
