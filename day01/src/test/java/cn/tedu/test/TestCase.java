package cn.tedu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    }

}
