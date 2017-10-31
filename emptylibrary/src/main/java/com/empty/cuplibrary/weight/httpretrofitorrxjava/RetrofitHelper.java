package com.empty.cuplibrary.weight.httpretrofitorrxjava;

import android.content.Context;
import android.util.Log;

import com.empty.cuplibrary.weight.tools.ToastTools;
import com.empty.cuplibrary.weight.tools.UtilsTools;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by win764-1 on 2016/12/12.
 *
 * compile 'com.squareup.retrofit2:retrofit:2.1.0'
 compile 'com.squareup.retrofit2:converter-gson:2.1.0'
 compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
 compile 'io.reactivex:rxandroid:1.2.0'
 compile 'com.google.code.gson:gson:2.6.2'
 compile 'com.squareup.okhttp3:logging-interceptor:+'
 *
 * Retrofit 的配置
 */

public class RetrofitHelper {
    //网络请求延迟时间，默认为50秒
    private final static int DEFAULT_TIMEOUT = 0x000032;
    private Context mCntext;
    private String url;

    /**
     * 打印 请求体  响应体
     */
    OkHttpClient client = new OkHttpClient().newBuilder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("creater_oid", "123411") //这里就是添加一个请求头
                            .build();

//                        Buffer buffer = new Buffer();       不依赖logging，用这三行也能打印出请求体
//                        request.body().writeTo(buffer);
                    Log.d(getClass().getSimpleName(), "intercept: " + request.body().toString());
                    return chain.proceed(request);
                } //下面是关键代码
            }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
            .build();


    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(Context context, String url){
        if (instance == null){
            synchronized (RetrofitHelper.class){
                if (instance == null){
                    instance = new RetrofitHelper(context,url);
                }
            }
        }
        return instance;
    }
    private RetrofitHelper(Context mContext, String url){
        mCntext = mContext;
        this.url = url;
        init();
    }

    private void init() {
        resetApp();
    }

    // 初始化界面
    private void resetApp() {
        if (UtilsTools.StringNull(url)){
            ToastTools.Toast_ShortTip(mCntext,"请求地址为空");
            return;
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    //获取 对应的服务类
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }

    //提交请求  并添加到请求  Observable 使用RxJava
    public void _postAsynObservable(CompositeSubscription mCompositeSubscription, final Observable<ResponseBody> call, final RetrofitCallBackInterface callBackInterface){

        mCompositeSubscription.add(
                call
//                        .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onCompleted() {
                                callBackInterface.callBackonCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                callBackInterface.callBackononError(e);

                            }

                            @Override
                            public void onNext(ResponseBody book) {
                                callBackInterface.callBackononNext(book);
                            }
                        })
        );

    }


    //提交请求  并添加到请求  Call 没有用RxJava
    public void _postAsynCall(CompositeSubscription mCompositeSubscription, final Call<ResponseBody> call, final RetrofitCallBackInterface callBackInterface){

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200){
                        callBackInterface.callBackononNext(response.body());
                    }else{
                        callBackInterface.callBackononNext(response.errorBody());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackInterface.callBackononError(t);
            }
        });

    }

}
