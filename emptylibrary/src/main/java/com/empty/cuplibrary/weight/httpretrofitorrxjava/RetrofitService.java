package com.empty.cuplibrary.weight.httpretrofitorrxjava;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 * 请求地址 和 参数 的配置
 */

public interface RetrofitService {
    /**
     * collectionapp" + "?AppId=kexinjinrong&InterfaceName=kexin.collectionapp.user.login" +
     "&TimeStamp="+timestamp
     + "&signdata=10acbb9dd9e859bf036b032443a30e1d"
     */
    @Headers({"Content-Type: application/json"})//需要添加头
    @POST("collectionapp")
    Observable<ResponseBody> getSearchBooksObservable(@Query("AppId") String AppId, @Query("InterfaceName") String InterfaceName,
                                                      @Query("TimeStamp") String TimeStamp, @Query("signdata") String signdata, @Body RequestBody name);

    //只用Retrofit  请求
    @POST("category?key=257754c656e52cc857038c0498849d0c")
    Call<ResponseBody> contributorsBySimpleGetCall();

    // 使用RxJava 和Retrofit 请求
    @POST("category?key=257754c656e52cc857038c0498849d0c")
    Observable<ResponseBody> rxJavacontributorsBySimpleGetCall();



    @Headers({"Content-Type: application/json"})//需要添加头
    @POST("collectionapp")
    Call<ResponseBody> getSearchBooks(@Query("AppId") String AppId, @Query("InterfaceName") String InterfaceName,
                                      @Query("TimeStamp") String TimeStamp, @Query("signdata") String signdata, @Body RequestBody route);//传入的参数为RequestBody
}
