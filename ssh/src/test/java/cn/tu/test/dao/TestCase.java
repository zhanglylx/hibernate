package cn.tu.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestCase extends TestBase {

    @Test
    public void testDataSource() throws SQLException {
        DataSource ds = this.ctx.getBean(DataSource.class);
        Connection connection = ds.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
