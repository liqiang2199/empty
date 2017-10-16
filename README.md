# empty
##使用方法
- 第一步：
allprojects { repositories {

		maven { url 'https://jitpack.io' }
	}
}  
- 第二步：
dependencies { compile 'com.github.liqiang2199:empty:0.1' }

aar 创建

//body 请求的数据体 提交一个json

RequestBody body=RequestBody.create(MediaType.parse("application/json"),req.getData());

//这句 是使用一个没有 RxJava 的 

//req 协议参数been  

Observable<ResponseBody> call=postRoute.getSearchBooksObservable(req.getAppId(),req.getInterfaceName(),req.getTimeStamp()
                ,req.getSignData(),body);
  

//这句 是使用一个没有 RxJava 的

Call<ResponseBody> call = postRoute.getSearchBooks(Appid,InterfaceName,TimeStamp,signdata,body);
  
  //取消 订阅
  @Override
    protected void onDestroy(){
        super.onDestroy()
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }
