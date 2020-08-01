package cn.tedu.note.service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> listUsers(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("参数不能为空");
        }
        return userDao.findUsersLikeName(name);
    }
}
