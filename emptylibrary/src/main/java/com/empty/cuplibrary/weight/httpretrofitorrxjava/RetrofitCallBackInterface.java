package com.empty.cuplibrary.weight.httpretrofitorrxjava;

import okhttp3.ResponseBody;

/**
 * Created by empty cup on 2017/10/11.
 */

public interface RetrofitCallBackInterface {
    void callBackonCompleted();
    void callBackononError(Throwable e);
    void callBackononNext(ResponseBody book);
}
