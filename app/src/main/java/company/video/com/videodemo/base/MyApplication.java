package company.video.com.videodemo.base;

import android.app.Application;

/**
 * Created by zhangxiaodong on 2018/3/30 14:33.
 * <br/>
 */

public class MyApplication extends Application {
    /**
     * 设置全局上下为单例
     */
    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    //获取全局上下文
    public static MyApplication getInstance(){
        return mContext;
    }
}
