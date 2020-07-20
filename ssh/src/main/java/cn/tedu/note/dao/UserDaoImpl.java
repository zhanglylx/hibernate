package cn.tedu.note.dao;

import cn.tedu.note.entity.User;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addUser(User user) {
        this.hibernateTemplate.save(user);
    }

    @Override
    public void deleteUser(User user) {
        this.hibernateTemplate.delete(user);
    }

    @Override
    public void updateUser(User user) {
        this.hibernateTemplate.update(user);
    }

    @Override
    public User findUserById(String id) {
        return this.hibernateTemplate.get(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
//        HQL
//       SQL:SELECT * FROM cn_user WHERE cn_user_name=?
//        HQL: FROM User WHERE name = ?  或者 name = :name
        String hql = "FROM User WHERE name=?";
        List<User> list = this.hibernateTemplate.find(hql, name);
        if (list.isEmpty()) return null;
        return list.get(0);
    }
1:48
    @Override
    public List<Map<String, Object>> findUsersLikeName(String name) {
        name = "%" + name + "%";
        String hql = "new map(id, name) from User where name Like :name";

        return this.hibernateTemplate.findByNamedParam(hql, "name", name);
    }
}
