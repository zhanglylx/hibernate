package cn.tu.test.service;

import cn.tedu.note.service.UserService;
import cn.tu.test.dao.TestBase;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTestCase extends TestBase {

    UserService userService;

    @Before
    public void initService() {
        init();
        this.userService = this.ctx.getBean(UserService.class);
    }

    @Test
    public void test() {
        this.userService.login("fdf", "123");
    }

}
