package cn.tedu.note.dao;

import cn.tedu.note.entity.User;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List findUsersLikeName(String name) {
        name = "%" + name + "%";
//        将查询结果中的行封装为map对象
        String hql = "select new map(id, name) from User where name Like :name";
//         执行带参数的HQL查询参数名:name
        return this.hibernateTemplate.findByNamedParam(hql, "name", name);
    }
}
