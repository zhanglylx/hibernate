package cn.tedu.service;

import cn.tedu.dao.UserDao;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    void deleteUsers(String... ids);

    UserDao getUserDao();

}
