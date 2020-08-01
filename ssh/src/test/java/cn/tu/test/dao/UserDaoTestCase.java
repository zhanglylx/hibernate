package cn.tu.test.dao;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class UserDaoTestCase extends TestBase {

    UserDao dao;

    @Before
    public void initDao() {
        init();
        this.dao = this.ctx.getBean(UserDao.class);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId("600");
        user.setName("fdf");
        user.setNick("FFF");
        user.setPassword("123");
        user.setToken("");
        this.dao.addUser(user);
    }

    @Test
    public void testFindByName() {
        String name = "fdf";
        User user = this.dao.findUserByName(name);
        System.out.println(user);
    }

    @Test
    public void testFindUsersLinkName() {
        List<Map<String, Object>> list = this.dao.findUsersLikeName("fdf");
        list.forEach(System.out::println);
    }
}
