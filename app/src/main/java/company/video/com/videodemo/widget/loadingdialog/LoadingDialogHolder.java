package company.video.com.videodemo.widget.loadingdialog;
import android.content.Context;

/**
 * 等待对话框持有类
 * Created by Jungle on 2017/8/7.
 */
public class LoadingDialogHolder {
    /**
     * 等待框
     */
    private LoadingDialog mLoadingDialog;

    private LoadingDialogHolder(Context context) {
        mLoadingDialog = new LoadingDialog(context);
        // 点击周围不消失
        mLoadingDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 初始化
     * @param context
     * @return
     */
    public static LoadingDialogHolder init(Context context) {
        return new LoadingDialogHolder(context);
    }

    /**
     * 显示
     */
    public void showLoading() {
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏
     */
    public void dismissLoading() {
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 显示
     * @param cancelable 是否可以通过back键或者点击屏幕其他区域取消
     */
    public void showLoadingWithCancelable(boolean cancelable) {
        mLoadingDialog.showWithCancelable(cancelable);
        showLoading();
    }

    /**
     * 设置等待框显示时，返回按钮的点击回调
     * 不设置时，点击事件交给系统处理
     * @param callback
     */
    public void addBackPressListener(LoadingDialog.BackPressedCallback callback) {
        mLoadingDialog.setCallback(callback);
    }

}
