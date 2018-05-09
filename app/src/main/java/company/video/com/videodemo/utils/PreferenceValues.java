package company.video.com.videodemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import company.video.com.videodemo.config.HttpConfig;

import static company.video.com.videodemo.config.HttpConfig.APP_LANG_CN;


/**
 * Created by zhangxiaodong on 2018/4/2.
 * <br/>
 * Preference帮助类
 */

public final class PreferenceValues {

    public PreferenceValues() {
    }

    private static final String DEFAULT_PREFERENCE_NAME = "MyApp";

    /**
     * 获得程序默认{@link SharedPreferences}
     *
     * @param context
     * @return
     */
    public static SharedPreferences GetDefaultPreferences(Context context) {
        return context.getSharedPreferences(DEFAULT_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 用来保存全局变量如uid什么的。只能保存String, int, long, float,
     * boolean的值,StringSet的话自己拿
     *
     * @param context
     * @param key
     * @param value   如果是null的话都会被保存为String
     * @return Returns true if the new values were successfully written to
     * persistent storage.
     */
    public static boolean SaveValue(Context context, String key, Object value) {
        synchronized (DEFAULT_PREFERENCE_NAME) {
            SharedPreferences.Editor editor = GetDefaultPreferences(context).edit();
            if (value instanceof String)
                editor.putString(key, (String) value);
            else if (value instanceof Integer)
                editor.putInt(key, (Integer) value);
            else if (value instanceof Float)
                editor.putFloat(key, (Float) value);
            else if (value instanceof Long)
                editor.putLong(key, (Long) value);
            else if (value instanceof Boolean)
                editor.putBoolean(key, (Boolean) value);
            else if (value == null)
                editor.putString(key, null);
            return editor.commit();
        }
    }

    /**
     * 删除全局变量譬如uid什么的。
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean RemoveValue(Context context, String key) {
        synchronized (DEFAULT_PREFERENCE_NAME) {
            SharedPreferences.Editor editor = GetDefaultPreferences(context).edit();
            editor.remove(key);
            return editor.commit();
        }
    }

    /**
     * 清空保存的参数
     *
     * @param context
     */
    public static void ClearValues(Context context) {
        SharedPreferences.Editor editor = GetDefaultPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 保存登录的用户id
     *
     * @param context
     * @param uid
     * @return
     */
    public static boolean SaveLoginUid(Context context, int uid) {
        return SaveValue(context, "login_uid", uid);
    }


    /**
     * 获得现在登录的用户ID
     *
     * @param context
     * @return 没有的话返回-1
     */
    public static int GetLoginUid(Context context) {
        return GetDefaultPreferences(context).getInt("login_uid", 0);
    }


    /**
     * 清除当前登录用户login_uid
     * @param context
     * @return
     */
    public static boolean RemoveLoginUid(Context context) {
        return  RemoveValue(context, "login_uid");
    }

    /** 首页
     * @param context
     * @param index
     */
    public static void SaveIndex(Context context, String index) {
        SaveValue(context, "index", index);
    }

    /**
     *  获取首页
     * @param context
     * @return
     */
    public static String GetIndex(Context context) {
        return GetDefaultPreferences(context).getString("index", "");
    }



    /**
     * 保存接口返回的Token
     *
     * @param context
     * @param token
     */
    public static void SaveAppToken(Context context, String token) {
        SaveValue(context, "app_token", token);
    }

    /**
     * 获取Token，用于网络请求，如果没有则""
     *
     * @param context
     * @return
     */
    public static String GetAppToken(Context context) {
        return GetDefaultPreferences(context).getString("app_token", "");
    }

    /**
     * 清除当前登录用户Token
     * @param context
     * @return
     */
    public static boolean RemoveAppToken(Context context) {
        return  RemoveValue(context, "app_token");
    }

    /**
     * 保存登录用户头像
     *
     * @param context
     * @param userAvatar 头像地址
     */
    public static void SaveLoginUserAvatar(Context context, String userAvatar) {
        SaveValue(context, "login_avatar", userAvatar);
    }

    /**
     * 获取之前登录用户的头像
     *
     * @param context
     * @return
     */
    public static String GetLoginUserAvatar(Context context) {
        return GetDefaultPreferences(context).getString("login_avatar", "");
    }

    /**
     * 保存上次登录时输入的账号或者手机号
     *
     * @param context
     * @param uidOrMobile
     * @return
     */
    public static boolean SaveLoginUidORMobile(Context context, String uidOrMobile) {
        return SaveValue(context, "uid_mobile", uidOrMobile);
    }

    /**
     * 获取上次登录时输入的账号或者手机号
     *
     * @param context
     * @return
     */
    public static String getLoginUidORMobile(Context context) {
        return GetDefaultPreferences(context).getString("uid_mobile", "");
    }

    /**
     * 保存用户选取的语言环境
     *
     * @param context
     * @return
     */
    public static boolean SaveLanguage(Context context, int id) {
        return SaveValue(context, "languageID", id);
    }

    /**
     * 获取用户选取的语言版本
     *
     * @param context
     * @return
     */
    public static int getLanguage(Context context) {
        int appLang;
        if (GetDefaultPreferences(context).getInt("languageID", APP_LANG_CN) != 1) {
            appLang = APP_LANG_CN;
        } else {
            appLang = HttpConfig.APP_LANG_EN;
        }
        SaveLanguage(context, appLang);
        return appLang;
    }

    /**
     * 保存换肤中文名称
     *
     * @param context
     * @param skinName
     * @return
     */
    public static boolean SaveSkinName(Context context, String skinName) {
        return SaveValue(context, "skinName", skinName);
    }

    /**
     * 获取换肤中文名称
     *
     * @param context
     * @return
     */
    public static String getSkinName(Context context) {
        return GetDefaultPreferences(context).getString("skinName", "");
    }
    /**
     * 保存皮肤对应的下标索引
     * @param context
     * @param index  皮肤对应的下标
     */
    public static boolean saveSkinIndex(Context context, int index){
        return SaveValue(context, "skinIndex", index);
    }
    /**
     * 获取换肤下标
     *
     * @param context
     * @return
     */
    public static int getSkinIndex(Context context) {
        return GetDefaultPreferences(context).getInt("skinIndex", 0);
    }
    /**
     * 保存脉友列表最后刷新时间
     *
     * @param context
     * @param lastTime
     * @return
     */
    public static boolean SaveFollowLastTime(Context context, String lastTime) {
        return SaveValue(context, "follow_lastTime", lastTime);
    }

    /**
     * 获取脉友列表最后刷新时间
     *
     * @param context
     * @return
     */
    public static String GetFollowLastTime(Context context) {
        return GetDefaultPreferences(context).getString("follow_lastTime", "");
    }

    /**
     * 保存当前定位城市
     * @param context
     * @param city                      当前城市
     * @return
     */
    public static boolean SaveLocationCity(Context context, String city) {
        return SaveValue(context, "location_city", city);
    }

    /**
     * 获取当前定位城市
     * @param context
     * @return
     */
    public static String GetLocationCity(Context context) {
        return GetDefaultPreferences(context).getString("location_city", "");
    }

    /**
     * 保存发布文章草稿
     * @param context
     * @param publishArticle                      发布文章草稿内容
     * @return
     */
    public static boolean SavePublishArticle(Context context, String publishArticle) {
        return SaveValue(context, "publish_article", publishArticle);
    }

    /**
     * 获取发布文章草稿
     * @param context
     * @return
     */
    public static String GetPublishArticle(Context context) {
        return GetDefaultPreferences(context).getString("publish_article", "");
    }

    /**
     * 获取通讯录上传成功的状态
     */
    public static boolean getUpdateAddressStatus(Context context, String key){
        return GetDefaultPreferences(context).getBoolean(key, false);
    }

    /**
     * 获取最后一次打开客户端的版本号
     * @param context
     */
    public static int getLastApplicationVersion(Context context) {
        return GetDefaultPreferences(context).getInt("last_application_version", -1);
    }


    /**
     * 保存是否删除环信的数据库
     * @param context
     */
    public static void SaveHxDataBaseIsDelete(Context context, boolean isDelete) {
        SaveValue(context, "hx_database_is_delate", isDelete);
    }

    /**
     * 获取是否删除环信的数据库
     * @param context
     */
    public static boolean getHxDataBaseIsDelete(Context context) {
        return GetDefaultPreferences(context).getBoolean("hx_database_is_delate", false);
    }

    /**进入app时是否弹出语言选择框
     * @param mContext
     * @return
     */
    public static boolean GetIsShowFirstLanguageDialog(Context mContext){
        return GetDefaultPreferences(mContext).getBoolean("firstSetLanguage",false);
    }

    /**
     * 获取当前app的语言
     * @param context
     * @return
     */
    public static String getAppLanguage(Context context) {
        return GetDefaultPreferences(context).getString("app_language","zh");
    }

    /**
     * 保存当前app的语言
     * @param context
     * @return
     */
    public static void saveAppLanguage(Context context, String value) {
        SaveValue(context, "app_language", value);
    }
}
