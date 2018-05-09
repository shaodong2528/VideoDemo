package company.video.com.videodemo.widget.loadingdialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import butterknife.ButterKnife;
import company.video.com.videodemo.R;
import company.video.com.videodemo.config.HttpConfig;
import company.video.com.videodemo.utils.PreferenceValues;

/**
 * Created by Jungle on 2017/8/7.
 */
public class LoadingDialog extends Dialog {

    private BackPressedCallback callback;

    private Context mContext;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.loadingDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
        // 根据sp保存的语言字段，匹配出相应的gif加载动画 ，默认中文动画
        if (PreferenceValues.getLanguage(mContext) == HttpConfig.APP_LANG_CN) {
//            ImageDisplayUtils.displayWithMode(getContext(), R.drawable.loading_cn, vIvLoading, ImageDisplayUtils.MODE.MODE_GIF);
        } else if (PreferenceValues.getLanguage(mContext) == HttpConfig.APP_LANG_EN) {
//            ImageDisplayUtils.displayWithMode(getContext(), R.drawable.loading_en, vIvLoading, ImageDisplayUtils.MODE.MODE_GIF);
        }
    }


    /**
     * 显示
     * @param cancel 是否可以通过back键或者点击屏幕其他区域取消
     */
    public void showWithCancelable(boolean cancel) {
        setCancelable(cancel);
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (callback != null) {
            callback.callback();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 设置等待框显示时，返回按钮的点击回调
     * 不设置时，点击事件交给系统处理
     * @param callback
     */
    public void setCallback(BackPressedCallback callback) {
        this.callback = callback;
    }

    public interface BackPressedCallback {
        void callback();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }
}
