package company.video.com.videodemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import company.video.com.videodemo.R;
import company.video.com.videodemo.base.BaseFragment;

/**
 * Created by zhangxiaodong on 2018/4/2.
 * <br/>
 * 发现fragment
 */
public class HomeDiscoverFragment extends BaseFragment {

    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_home_main, container, false);
        TextView tv = mView.findViewById(R.id.tv_fragment_txt);
        tv.setText("发现");
        return mView;
    }
}
