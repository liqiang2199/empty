package com.empty.cuplibrary.weight.httpretrofitorrxjava;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by empty cup on 2017/10/11.
 *
 * 配置使用 这个封装
 */

public class UseRetrofit {

    /**
     * 提交json  使用Retrofit
     * @param name
     * @param tag
     * @param start
     * @param count
     */
//    public void getSearchBooks(String name,String tag,int start,int count){
//
//        LoginBeenHttp loginBeenHttp = new LoginBeenHttp();
//        loginBeenHttp.setLoginName("400997990031");
//        loginBeenHttp.setLoginPass("96E79218965EB72C92A549DD5A330112");
//
//        /**
//         * collectionapp" + "?AppId=kexinjinrong&InterfaceName=kexin.collectionapp.user.login" +
//         "&TimeStamp="+timestamp
//         + "&signdata=10acbb9dd9e859bf036b032443a30e1d"
//         */
//        String Appid = "kexinjinrong";
//        String InterfaceName = "kexin.collectionapp.user.login";
//        String TimeStamp = timestamp;
//        String signdata = "10acbb9dd9e859bf036b032443a30e1d";
//
//        String myurl = "http://192.168.199.87:8003/api/";
//
//
//
//
//        /**
//         * 打印 请求体  响应体
//         */
////        OkHttpClient client = new OkHttpClient().newBuilder()
////                .addInterceptor(new Interceptor() {
////                    @Override
////                    public okhttp3.Response intercept(Chain chain) throws IOException {
////                        Request request = chain.request().newBuilder()
////                                .addHeader("creater_oid", "123411") //这里就是添加一个请求头
////                                .build();
////
//////                        Buffer buffer = new Buffer();       不依赖logging，用这三行也能打印出请求体
//////                        request.body().writeTo(buffer);
////                        Log.d(getClass().getSimpleName(), "intercept: " + request.body().toString());
////
////                        return chain.proceed(request);
////                    } //下面是关键代码
////                }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
////                .build();
////
////
////        Retrofit retrofit=new Retrofit.Builder()
////                .baseUrl(myurl)
////                .client(client)
////                .addConverterFactory( GsonConverterFactory.create())
////                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
////                .build();
////        RetrofitService postRoute=retrofit.create(RetrofitService.class);
//        MRequest req = DataCommon.getIstance().createRequest(InterfaceName,loginBeenHttp,"92f8924e86d6f3ff2e1e27878b314ebb");
//
//        RetrofitService postRoute = RetrofitHelper.getInstance(mContext).getServer();
//
//        RequestBody body=RequestBody.create(MediaType.parse("application/json"),req.getData());
//
//        //这句 是使用一个没有 RxJava 的
//        Observable<ResponseBody> call=postRoute.getSearchBooksObservable(req.getAppId(),req.getInterfaceName(),req.getTimeStamp()
//                ,req.getSignData(),body);
////        Log.v("call","     请求 call     "+call);
//        //这句 是使用一个没有 RxJava 的
////        Call<ResponseBody> call = postRoute.getSearchBooks(Appid,InterfaceName,TimeStamp,signdata,body);
//
//        RetrofitHelper.getInstance(mContext)._postAsynObservable(mCompositeSubscription,call,this);
//
//        mCompositeSubscription.add(
//                call
////                        .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//
//                        .subscribe(new Observer<ResponseBody>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                e.printStackTrace();
////                                mBookView.onError("请求失败！！");
//                                try {
//                                    Log.v("Logins"," 请求失败  "+e.getMessage());
//                                } catch (Exception w) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onNext(ResponseBody book) {
//                                try {
//                                    Log.v("Logins"," 成功  "+book.string());
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        })
//        );
//
//
//    }
}
