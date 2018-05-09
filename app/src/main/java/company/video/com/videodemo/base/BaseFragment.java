package company.video.com.videodemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.m.permission.MPermissions;

import company.video.com.videodemo.http.RxApiManager;
import company.video.com.videodemo.widget.loadingdialog.LoadingDialogHolder;

/**
 * Created by zhangxiaodong on 2018/3/30 14:33.
 * <br/>
 * fragment基类
 * 2018/4/2
 */

public class BaseFragment extends Fragment {

    RxApiManager rxApiManager = new RxApiManager();
    protected LoadingDialogHolder mLoadingDialogHolder;
    //传递过来的参数Bundle，供子类使用
    protected Bundle args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
    }

    /**
     * 创建fragment的静态方法，方便传递参数
     *
     * @param args 传递的参数
     * @return
     */
    public static <T extends Fragment> T newInstance(Class clazz, Bundle args) {
        T mFragment = null;
        try {
            mFragment = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rxApiManager.onCreate(getContext());
        mLoadingDialogHolder = LoadingDialogHolder.init(getContext());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        rxApiManager.onDestroy(getContext());
    }

    /**
     * 显示
     */
    protected void showLoading() {
        if (null != mLoadingDialogHolder) {
            mLoadingDialogHolder.showLoading();
        }
    }

    /**
     * 隐藏
     */
    protected void dismissLoading() {
        if (null != mLoadingDialogHolder) {
            mLoadingDialogHolder.dismissLoading();
        }
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
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
