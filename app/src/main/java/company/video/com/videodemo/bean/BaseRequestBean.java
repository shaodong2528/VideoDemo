package company.video.com.videodemo.bean;

/**
 * Created by zhangxiaodong on 2018/4/2.
 * <br/>
 * 网络请求返回通用参数父类Bean
 */

public class BaseRequestBean {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 语言类型
     */
    private int lang;

    /**
     * 唯一标识
     */
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
