package cn.tedu.note.web;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonAction extends ActionSupport
        implements SessionAware, RequestAware, ApplicationAware {
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;
    public static final String JSON = "json";

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    //    发送到客户端的Json结果
    private Map<String, Object> result = new HashMap<>();

    /**
     * 设置正确返回值
     *
     * @param value
     */
    protected void setResult(Object value) {
        this.result.put("state", 0);
        this.result.put("message", "");
        this.result.put("data", value);
    }

    /**
     * 设置错误返回值
     *
     * @param error
     */
    protected void setResult(String error) {
        this.result.put("state", 1);
        this.result.put("message", error);
        this.result.put("data", null);
    }


    /**
     * 设置错误返回值
     *
     * @param error
     */
    protected void setResult(Throwable error) {
        this.setResult(error.getMessage());
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        this.application = map;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
