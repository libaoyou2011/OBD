package com.baolong.obd.component.webview;

import com.baolong.obd.common.network.ResponseWrapper;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public class ReportApis {

    //在服务器上生成待下载的文件：检测结果报告
    public interface ProductOneFileInServer {
        @FormUrlEncoded
        @POST("/modules/overproof/app/exportapp")
        Observable<ResponseWrapper<String>> req(@Field("ids") String jlbh);
    }

    //在服务器上生成待下载的文件：检测结果报告 + 告知书
    public interface ProductTwoFileInServer {
        @FormUrlEncoded
        @POST("/modules/overproof/app/exportapp")
        Observable<ResponseWrapper<String>> req(@Field("ids") String jlbh);
    }

    //从服务器上下载文件
    public interface DownloadFile {
        @Streaming //大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
        @GET
        Observable<ResponseBody> downloadFile(@Url String fileUrl);
    }

    //从服务器上删除文件
    public interface DeleteFileInServer {
        @FormUrlEncoded
        @POST("/modules/overproof/delpath")
        Observable<ResponseWrapper<String>> req(@Field("path") String fileName);
    }

}
