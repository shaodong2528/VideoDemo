package company.video.com.videodemo.http;

import android.content.Context;


import company.video.com.videodemo.config.HttpConfig;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sky on 2017/7/12.
 */

public class RxApiManager implements RxActionManager {

    @Override
    public void onCreate(Context context) {
    }

    @Override
    public void onDestroy(Context context) {
        destroyApiRequest(context);
    }

    /**
     * 移除网络请求
     *
     * @param context
     */
    private void destroyApiRequest(Context context) {
        if (HttpConfig.rxHttpManageHashMap != null) {
            Subscription subscription = HttpConfig.rxHttpManageHashMap.get(context);
            if (subscription != null) {
                if (!subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
            //2017年11月3日16:02:50 张路添加移除HashMap 以前放在subscription.unsubscribe(); 之后
            HttpConfig.rxHttpManageHashMap.remove(context);
        }
    }

    /**
     * 存储网络请求
     *
     * @param observable
     * @param subscriber
     */
    public static void putApiRxSubscription(Context context, Observable observable, MgeSubscriber subscriber) {
        Subscription mSubscription = observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        HttpConfig.rxHttpManageHashMap.put(context, mSubscription);
    }


}
