package company.video.com.videodemo.http;
import com.apkfuns.logutils.LogUtils;

import java.util.concurrent.TimeUnit;

import company.video.com.videodemo.config.HttpConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import static company.video.com.videodemo.config.HttpConfig.DEFAULT_TIMEOUT;

/**
 * Created by zhangxiaodong on 2018/4/2.
 * <br/>
 * OK HTTP Client
 */
public class OkHttpRetrofit {

    static OkHttpRetrofit mOkHttpRetrofit;

    static OkHttpClient mHttpClientBuilder;

    /**
     * 给脉友列表用的，那个请求时间太长，改成自定义超时时间
     * @param timeout
     */
    public OkHttpRetrofit(int timeout) {
        //手动创建一个OkHttpClient并设置超时时间
        mHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    /**
     * 默认请求连接
     */
    public OkHttpRetrofit() {
        //手动创建一个OkHttpClient并设置超时时间
        mHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


    /**
     * 获取OkHttp Client
     * @return
     */
    public static OkHttpRetrofit getInstance() {
        if(mOkHttpRetrofit == null) {
            mOkHttpRetrofit = new OkHttpRetrofit();
        }
        return mOkHttpRetrofit;
    }

    public static OkHttpRetrofit getInstance(int timeout) {
        if(mOkHttpRetrofit == null) {
            mOkHttpRetrofit = new OkHttpRetrofit(timeout);
        }
        return mOkHttpRetrofit;
    }



    /**
     * 初始化 Retrofit
     * @param isHttpsRequest            是否是HTTPS
     * @return
     */
    public static Retrofit getRetrofit(boolean isHttpsRequest) {
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .client(mHttpClientBuilder)
                .addConverterFactory(FastJsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        if(isHttpsRequest)
            retrofit.baseUrl(HttpConfig.BASE_URL_S);
        else
            retrofit.baseUrl(HttpConfig.BASE_URL);
        return retrofit.build();
    }

    public static Retrofit getRetrofit() {
        return getRetrofit(true);
    }

    /**
     * 设置OKHttp Log日志打印
     */
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            LogUtils.d(message.toString());
        }
    });
}
