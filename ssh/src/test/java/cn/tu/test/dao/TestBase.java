package cn.tu.test.dao;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
   public ApplicationContext ctx;


    public void init() {
        String[] cfg = {"conf/spring-orm.xml","conf/spring-service.xml"};
        ctx = new ClassPathXmlApplicationContext(cfg);
    }
}
