package cn.tedu.test;

import cn.tedu.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.*;


public class TestCase {
    SessionFactory factory;

    @Before
    public void init() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        //创建Session的工厂
        this.factory = cfg.buildSessionFactory();
    }

    @After
    public void close() {
        this.factory.close();
    }

    @Test
    public void testSession() {
//        session的底层就是JDBC Connection
//        如果没有异常,代表数据库连接成功
        Session session = this.factory.openSession();
        System.out.println(session);
        session.close();
    }

    @Test
    public void testSaveObject() {
        User user = new User();
        user.setId("300");
        user.setName("Tom");
        user.setPassword("123");
        user.setToken("Cat");
        user.setNick("ttt");
        Session session = this.factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);//user转换为持久状态
        //修改持久状态对象的属性，将影响数据库，不在调用session.update 也会更改数据中的数据
        user.setNick("Andy");
        tx.commit();
        session.close();
    }

    @Test
    public void testUpdateObject() {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.factory.openSession();
//            开启事务
            tx = session.beginTransaction();
//            测试更新功能
//            找到现有的用户信息
            User user = (User) session.get(User.class, "200" +
                    "");
            System.out.println(user);
//            修改信息
            user.setName("Jerry");
//            将修改结果更改到数据库
            session.update(user);
//          提交事务
            tx.commit();//执行update
        } catch (Exception e) {
            e.printStackTrace();
//            回滚事务
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Test
    public void testDeleteObject() {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.factory.openSession();
//            开启事务
            tx = session.beginTransaction();
//            测试更新功能
//            找到现有的用户信息
            User user = (User) session.get(User.class, "100");
            System.out.println(user);
//            修改信息
            user.setName("Jerry");
//            将修改结果更改到数据库
            session.delete(user);
//          提交事务
            tx.commit();//执行update
        } catch (Exception e) {
            e.printStackTrace();
//            回滚事务
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 持久状态
     */
    @Test
    public void testUpdate() {
        Session session = this.factory.openSession();
        Transaction tx = session.beginTransaction();
//        get返回的对象是 持久状态对象
        User user = (User) session.get(User.class, "200");
//        修改“持久状态”对象的属性，将影响数据库中的数据
        user.setNick("老王");
        tx.commit();
        session.close();
    }


    /**
     * 持久-->游离-->持久
     * 将对象从持久状态抽离
     */
    @Test
    public void testEvice() {
        Session session = this.factory.openSession();
        Transaction tx = session.beginTransaction();
        User user = (User) session.get(User.class, "200");
        System.out.println(user);
//        清除一个对象，将对象user提出session缓存，使其成为游离状态
        session.evict(user);
//        清除所有对象
        session.clear();
//        游离状态的user对象，修改其属性不影响数据库
        user.setNick("老宋");
//        将user转变为持久状态
        session.update(user);
        tx.commit();
        session.close();
    }

    /**
     * delete后对象为临时状态
     */
}
