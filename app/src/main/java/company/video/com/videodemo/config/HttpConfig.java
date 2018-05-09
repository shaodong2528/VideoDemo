package company.video.com.videodemo.config;

import android.content.Context;
import java.util.HashMap;
import rx.Subscription;

/**
 * Created by zhangxiaodong on 2018/4/2.
 * <br/>
 * 网络请求配置文件
 */

public class HttpConfig {

    /**
     * 域名
     */
//    public static final String BASE_URL = BuildConfig.API_HOST;
    /**
     * http
      */
    public static final String BASE_URL = "http://:www.xxx";
    /**
     *https
     */
    public static final String BASE_URL_S = "https://:www.xxx";

    /**
     * 超时时间
     */
    public static final int DEFAULT_TIMEOUT = 30;

    /**
     * 网络请求接口管理Map
     */
    public static HashMap<Context, Subscription> rxHttpManageHashMap = new HashMap();

    /**
     * 网络请求，语言类型
     * 0.简体中文
     * 1.English
     */
    public static final Integer[] langArray = {HttpConfig.APP_LANG_CN, HttpConfig.APP_LANG_EN};

    /**
     *简体中文
     */
    public static final int APP_LANG_CN = 0;

    /**
     * English
     */
    public static final int APP_LANG_EN = 1;
}
