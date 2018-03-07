package com.zyjd.mips.retrofit;

import com.zyjd.mips.entity.Program;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Service {
    //    // users
//    @POST("login/")
//    Observable<Token> login(@Body Login login);
//
//    @POST("verify-jwt-token/")
//    Observable<ResponseBody> verifyToken(@Body Token token);
//
//    // 文件下载
//    @Streaming
//    @GET
//    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    //    // 请求本广告机的最新节目
//    @GET("terminals/latest-program/")
//    Observable<Program> getLatestProgram(@Path String id);
//
    // 请求节目
    @GET("programs/1/")
    Observable<Program> getProgram();

    // 文件下载
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}


























