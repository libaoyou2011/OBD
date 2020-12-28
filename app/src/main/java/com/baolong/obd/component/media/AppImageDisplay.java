package com.baolong.obd.component.media;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.Converter;
import com.baolong.obd.common.utils.LogUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;

public class AppImageDisplay {
    private static final String TAG = AppImageDisplay.class.getSimpleName();

    public static void showImg(final String prePath, final String path, final Context context, final int defaultImageId, final ImageView imageView) {
        //判空
        if (TextUtils.isEmpty(path)) {
            return;
        }

        //String imagePath = path;
        String imagePath = path.replaceAll("\\\\", "/");

        if (!path.startsWith("http")) {

            if (!TextUtils.isEmpty(prePath)) {

                imagePath = Converter.CombineUrl(prePath, imagePath);
            }
            imagePath = Converter.CombineUrl(BaseApplication.host, imagePath);

        }
       /* else {

            //因为图片是内网地址, http://10.10.10.243:8015/20191026/146/180234.jpg，此处替换成外网地址
            //                  http://36.7.144.182:8181/

            //如果图片是内网地址，但服务器是外网地址，则尝试将图片地址变为外网地址
            if (path.startsWith("http://10.") || path.startsWith("http://192.")) {

                if (!BaseApplication.host.startsWith("http://10.") && !BaseApplication.host.startsWith("http://192.")) {
                    String[] tempArray1 = path.split(":");
                    if (tempArray1.length >= 3){
                        //split，数组的长度将不会大于第二个参数
                        String[] tempArray2 = tempArray1[2].split("/",2);
                        if (tempArray2.length >=2 ){
                            imagePath = Converter.CombineUrl(BaseApplication.host, tempArray2[1]);
                        }
                    }

                }
            }

        }*/


        LogUtil.i(TAG, imagePath);

        /*
         * 不带cookie的图片加载方式
         */
        /*Glide.with(context)
                .load((Object) urlStr)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(defaultImageId)
                        .error(defaultImageId))
                .into(imageView);*/


        /*
         * 带cookie的图片加载方式
         */
        GlideUrl glideUrl = new GlideUrl(imagePath, new LazyHeaders.Builder().addHeader("Cookie", BaseApplication.cookie).build());
        Glide.with(context)
                .load(glideUrl)
                .apply(new RequestOptions()
                        .timeout(2000)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(defaultImageId)
                        .error(defaultImageId))
                .into(imageView);
    }
}