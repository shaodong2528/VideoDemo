package company.video.com.videodemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import com.liulishuo.filedownloader.FileDownloader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import company.video.com.videodemo.fragments.HomeDiscoverFragment;
import company.video.com.videodemo.fragments.HomeMainFragment;
import company.video.com.videodemo.fragments.HomeMyFragment;
import company.video.com.videodemo.fragments.HomeVideoFragment;
import company.video.com.videodemo.widget.dialog.CustomDialog;

/**
 *  Created by zhangxiaodong on 2018/4/2.
 *  <br/>
 * 首页界面
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 首页
     */
    @BindView(R.id.rbtn_home_main)
    RadioButton mMainRBtn;
    /**
     * 视频
     */
    @BindView(R.id.rbtn_home_video)
    RadioButton mVideoRBtn;
    /**
     * 发现
     */
    @BindView(R.id.rbtn_home_discover)
    RadioButton mDiscoverRBtn;
    /**
     * 我的
     */
    @BindView(R.id.rbtn_home_my)
    RadioButton mMyRBtn;
    /**
     * fragment管理器
     */
    private FragmentManager mFragmentManager;
    /**
     * 首页fragment
     */
    private HomeMainFragment mMainFragment;
    /**
     * 视频fragment
     */
    private HomeVideoFragment mVideoFragment;
    /**
     * 发现fragment
     */
    private HomeDiscoverFragment mDiscoverFragment;
    /**
     * 我的fragment
     */
    private HomeMyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init(){
        //绑定控件
        ButterKnife.bind(this);
        mFragmentManager = this.getSupportFragmentManager();
        setTabSelect(0);


        //下载初始化
        FileDownloader.setup(this);
    }

    //设置当前显示哪个页面
    private void setTabSelect(int index){
        //设置按扭不可选
        mMainRBtn.setSelected(false);
        mVideoRBtn.setSelected(false);
        mDiscoverRBtn.setSelected(false);
        mMyRBtn.setSelected(false);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        if (index == 0) {
            //首页
            mMainRBtn.setSelected(true);
            if (mMainFragment == null) {
                mMainFragment = HomeMainFragment.newInstance(HomeMainFragment.class,null);
                transaction.add(R.id.apkol_content_layout, mMainFragment);
            } else {
                transaction.show(mMainFragment);
            }
        } else if (index == 1) {
            //视频
            mVideoRBtn.setSelected(true);
            if (mVideoFragment == null) {
                mVideoFragment = HomeVideoFragment.newInstance(HomeVideoFragment.class,null);
                transaction.add(R.id.apkol_content_layout, mVideoFragment);
            } else {
                transaction.show(mVideoFragment);
            }
        } else if(index == 2){
            //发现
            mDiscoverRBtn.setSelected(true);
            if(mDiscoverFragment == null){
                mDiscoverFragment = HomeDiscoverFragment.newInstance(HomeDiscoverFragment.class,null);
                transaction.add(R.id.apkol_content_layout, mDiscoverFragment);
            }else{
                transaction.show(mDiscoverFragment);
            }
        } else if(index == 3){
            //我的
            mMyRBtn.setSelected(true);
            if(mMyFragment == null){
                mMyFragment = HomeMyFragment.newInstance(HomeMyFragment.class,null);
                transaction.add(R.id.apkol_content_layout, mMyFragment);
            }else{
                transaction.show(mMyFragment);
            }
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }
        if (mVideoFragment != null) {
            transaction.hide(mVideoFragment);
        }
        if(mDiscoverFragment != null){
            transaction.hide(mDiscoverFragment);
        }
        if(mMyFragment != null){
            transaction.hide(mMyFragment);
        }
    }

    @OnClick ({R.id.rbtn_home_main,R.id.rbtn_home_video,R.id.rbtn_home_discover,R.id.rbtn_home_my,R.id.tv_back})
    public void onViewClick(View v) {
            switch (v.getId()){
                case R.id.rbtn_home_main:
                    //首页
                    setTabSelect(0);
                    break;
                case R.id.rbtn_home_video:
                    //视频
                    setTabSelect(1);
                    break;
                case R.id.rbtn_home_discover:
                    //发现
                    setTabSelect(2);
                    break;
                case R.id.rbtn_home_my:
                    //我的
                    setTabSelect(3);
                    break;
                    //返回
                case R.id.tv_back:
                    break;
            }
    }
}
