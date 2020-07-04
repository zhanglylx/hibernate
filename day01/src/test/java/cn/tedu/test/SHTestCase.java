package cn.tedu.test;


import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SHTestCase {
    ApplicationContext applicationContext;
    SessionFactory factory;

    @Before
    public void init() {
        String[] cfg = {"spring-hibernate.xml"};
        this.applicationContext = new ClassPathXmlApplicationContext(cfg);
        this.factory = this.applicationContext.getBean("sessionFactory", SessionFactory.class);
    }

    @Test
    public void testSession() {
        Session session = this.factory.openSession();
        System.out.println(session);
        session.close();
    }

    @Test
    public void testHibernateTemplate() {
//        相对于Session对象HibernateTemplate对象使用更加简便
//        HibernateTemplate由Spring提供的是对Session的封装，是一个更加简便的API
        HibernateTemplate tempplate =
                this.applicationContext.getBean("hibernateTemplate", HibernateTemplate.class);
        User user = new User();
        user.setId("1234234");
        user.setName("FD");
        user.setNick("fsd");
        user.setPassword("2222");
        user.setToken("23");
        tempplate.save(user);
    }

    @Test
    public void testUserDao() {
        UserService service = this.applicationContext.getBean(UserService.class);
        User user = new User();
        user.setId("5000");
        user.setName("FD");
        user.setNick("fsd");
        user.setPassword("2222");
        user.setToken("23");
        service.getUserDao().addUser(user);
        User u = service.getUserDao().findUserById("200");
        service.getUserDao().deleteUser(u);
    }

    @Test
    public void testDeleteUsers() {
//        测试事务是否能够正常提交或者回滚
        UserService userService = this.applicationContext.getBean(UserService.class);
        userService.deleteUsers("5000","a8c13bd244030f70f5354ef81b6bcd5f");
        /*
            Spring AOP底层自动的使用 JDK 动态代理或者CGLIB代理
            1.当对象有接口时候使用 JDK 动态代理
            2.当对象没有接口时候使用 CGLIB 代理
         */
    }
}
