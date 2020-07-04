package cn.tedu.dao.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public int addUser(User user) {
        Object id = this.hibernateTemplate.save(user);
        System.out.println(id);
        return 1;
    }

    @Override
    public int deleteUser(User user) {
        this.hibernateTemplate.delete(user);
        return 1;
    }

    @Override
    public int updateUser(User user) {
        this.hibernateTemplate.update(user);
        return 1;
    }

    @Override
    public User findUserById(String id) {
        return this.hibernateTemplate.get(User.class, id);
    }
}
