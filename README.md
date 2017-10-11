# empty

aar 创建

//body 请求的数据体 提交一个json

RequestBody body=RequestBody.create(MediaType.parse("application/json"),req.getData());

//这句 是使用一个没有 RxJava 的 

//req 协议参数been  

Observable<ResponseBody> call=postRoute.getSearchBooksObservable(req.getAppId(),req.getInterfaceName(),req.getTimeStamp()
                ,req.getSignData(),body);
  

//这句 是使用一个没有 RxJava 的

Call<ResponseBody> call = postRoute.getSearchBooks(Appid,InterfaceName,TimeStamp,signdata,body);
  
