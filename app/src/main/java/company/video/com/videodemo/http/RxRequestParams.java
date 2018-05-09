package company.video.com.videodemo.http;

import android.content.Context;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import company.video.com.videodemo.utils.PreferenceValues;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zhangxiaodong on 2018/4/2.
 * </br>
 * 网络请求参数封装
 */

public final class RxRequestParams {

    Map<String, Object> params = new LinkedHashMap<>();

    Context mContext;

    public RxRequestParams() {

    }

    /**
     * 封装网络请求通用参数
     * @param context
     * @param path          请求地址，去掉域名
     * @return
     */
    public Map<String, Object> Builder(Context context, String path) {
        mContext = context;
        int uid = PreferenceValues.GetLoginUid(mContext);
        long timestamp = System.currentTimeMillis() / 1000;
        String token = PreferenceValues.GetAppToken(mContext);
        String uuid = UUID.randomUUID().toString();
        String sign = "clientType=" + 0 + "&lang=" + PreferenceValues.getLanguage(context)+ "&network=" + 1 +
                "&timestamp=" + timestamp + "&uid=" + uid + "token=" + token +
                "uuid=" +  uuid + "action=" + path;
        params.put("uid", uid);
        params.put("clientType", 0);
        params.put("lang", PreferenceValues.getLanguage(context));
        params.put("timestamp", timestamp);
//        params.put("sign", ApplicationUtils.stringToMD5(sign));
        params.put("uuid", uuid);
        return params;
    }


    /**
     * 单张图片封装参数
     * @param partName          图片KEY
     * @param file              图片
     * @return
     */
    public static Map<String, RequestBody> requesFilePart(String partName, File file) {

        Map<String, RequestBody> bodyMap = new LinkedHashMap<>();
        bodyMap.put(partName +"\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        return bodyMap;
    }

    /**
     * 多张图封装参数
     * @param partName          图片KEY
     * @param file              图片
     * @return
     */
    public static Map<String, RequestBody> requesFilePart(String partName, File[] file) {

        Map<String, RequestBody> bodyMap = new LinkedHashMap<>();
        for(int i = 0; i < file.length; i++) {
            bodyMap.put(partName + "[" + i + "]" + "\"; filename=\"" + file[i].getName(), RequestBody.create(MediaType.parse("image/*"), file[i]));
        }

        return bodyMap;
    }

}
