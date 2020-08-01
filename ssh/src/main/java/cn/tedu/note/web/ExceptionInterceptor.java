package cn.tedu.note.web;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.omg.CORBA.*;
import org.springframework.stereotype.Component;

import java.lang.Object;

@Component
public class ExceptionInterceptor implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        try {
//            调用控制器
            String str = actionInvocation.invoke();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            Object o = actionInvocation.getAction();
            if (o instanceof JsonAction) {
                JsonAction jsonAction = (JsonAction) o;
                jsonAction.setResult(e);
                return JsonAction.JSON;
            }
            throw e;
        }
    }
}
