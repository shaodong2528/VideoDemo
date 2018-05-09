package company.video.com.videodemo.http;

import android.content.Context;
import android.net.ParseException;
import android.text.TextUtils;
import org.json.JSONException;
import java.io.IOException;

import company.video.com.videodemo.base.MyApplication;
import company.video.com.videodemo.bean.BaseRequestBean;
import company.video.com.videodemo.config.HttpConfig;
import company.video.com.videodemo.utils.PreferenceValues;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by zhangxiaodong on 2018/4/2.
 * <br/>
 * 网络请求处理
 *
 */

public abstract class MgeSubscriber<T extends BaseRequestBean> extends Subscriber {

    /**
     * 版本更新
     */
    private final int CODE_101 = 101;
    /**
     * Token错误, 退出重新登录
     */
    private final int CODE_110 = 110;
    /**
     * 系统维护
     */
    private final int CODE_120 = 120;
    /**
     * 账号被禁用
     */
    private final int CODE_999 = 999;

    RxApiManager rxApiManager = new RxApiManager();

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        // 2017年12月7日16:33:36 张路 当接口出错的情况下，不需要一下把所有的网络队列全删掉，而是依附于请求消息体自身销毁
        //destroyApiRequest();
        onDismissLoading();
        onFailure(e);
    }

    @Override
    public void onNext(Object o) {
        //2017年11月3日16:02:50 张路添加请求成功，咱不移除网络请求，页面的生命周期会处理 是否 移除
//        destroyApiRequest();
        onDismissLoading();
        BaseRequestBean baseBean = (BaseRequestBean) o;
        if (baseBean.getCode() == 0) {
            PreferenceValues.SaveAppToken(MyApplication.getInstance(), baseBean.getToken());
            onSuccess((T) o);
        } else if (baseBean.getCode() == CODE_101) {
            // 版本更新
        } else {
            // 错误
            if (TextUtils.isEmpty(baseBean.getMsg())) {
                baseBean.setMsg("Server Error!!!");
            }
            onFailure(baseBean.getCode(), baseBean.getMsg());
        }
    }

    /**
     * 关闭网络请求loading
     */
    public abstract void onDismissLoading();

    /**
     * 返回的数据，已解析
     *
     * @param result
     */
    public abstract void onSuccess(T result);

    /**
     * 失败时的处理
     */
    public abstract void onFailure(int code, String msg);

    /**
     * 移除网络请求
     */
    private void destroyApiRequest() {
        onDismissLoading();
        Context[] ContextArray = HttpConfig.rxHttpManageHashMap.keySet().toArray(new Context[HttpConfig.rxHttpManageHashMap.size()]);
        for (Context context : ContextArray) {
            rxApiManager.onDestroy(context);
        }
    }

    /**
     * 请求error处理
     *
     * @param e
     */
    private void onFailure(Throwable e) {
        if (e instanceof HttpException) {
            // Http 错误
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case 401:
                case 403:
                case 404:
                case 500:
                case 502:
                case 503: {
                    onFailure(httpException.code(), "41、403、404、500、502、503 Error!!!");
                }
                break;
                case 408:
                case 504: {
                    onFailure(httpException.code(), "408、504 Error!!!");
                }
                break;
                default: {
                    onFailure(httpException.code(), "Default HttpException Error!!!");
                }
                break;
            }
        } else if ( e instanceof JSONException || e instanceof ParseException) {
            // JSON 解析错误
            onFailure(996, "JSONException!!!");
        } else if (e instanceof IOException) {
            onFailure(997, "IOException!!!");
        } else if (e instanceof ClassCastException) {
            onFailure(998, "ClassCastException!!!");
        } else {
            onFailure(999, "Other Error!!!");
        }
    }
}
