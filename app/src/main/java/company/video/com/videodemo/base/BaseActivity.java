package company.video.com.videodemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zhy.m.permission.MPermissions;

import company.video.com.videodemo.http.RxApiManager;
import company.video.com.videodemo.widget.loadingdialog.LoadingDialogHolder;

/**
 * Created by zhangxiaodong on 2018/4/2 14:06.
 * <br/>
 * activity的基类
 */

public class BaseActivity extends AppCompatActivity {

    RxApiManager rxApiManager = new RxApiManager();
    protected LoadingDialogHolder mLoadingDialogHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxApiManager.onCreate(this);
        mLoadingDialogHolder = LoadingDialogHolder.init(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxApiManager.onDestroy(this);
    }

    /**
     * 显示
     */
    protected void showLoading() {
        mLoadingDialogHolder.showLoading();
    }

    /**
     * 隐藏
     */
    protected void dismissLoading() {
        mLoadingDialogHolder.dismissLoading();
    }

    /**
     * 显示
     *
     * @param cancelable 是否可以通过back键或者点击屏幕其他区域取消
     */
    protected void showLoadingWithCancelable(boolean cancelable) {
        mLoadingDialogHolder.showLoadingWithCancelable(cancelable);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
