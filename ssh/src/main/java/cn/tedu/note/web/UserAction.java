package cn.tedu.note.web;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Scope("prototype")
public class UserAction extends JsonAction {
    @Autowired
    private UserService userService;

    public String login() {
        try {
            User user = this.userService.login(this.username, this.password);
//            登录成功
            this.setResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            //            登录失败
            this.setResult(e);
        }
        return SUCCESS;
    }


    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
