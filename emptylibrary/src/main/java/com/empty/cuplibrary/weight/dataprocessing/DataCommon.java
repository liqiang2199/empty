package com.empty.cuplibrary.weight.dataprocessing;



import com.empty.cuplibrary.weight.been.MRequest;
import com.empty.cuplibrary.weight.encryption.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by admin on 2016/11/9.
 * 对Url地址的签名
 */
public class DataCommon {

    // appid
    private static String AppId = "signbill";
    private String data;
    private String key;
    private String DeviceId;
    private String DeviceType;
    // 时间戳
    public String timestamp;

    public static DataCommon dataCommon;

    public DataCommon(){
        dataCommon = this;
    }


    public static DataCommon getIstance(){
        if(dataCommon==null){
            synchronized (DataCommon.class){
                if (dataCommon == null){
                    dataCommon = new DataCommon();
                }
            }
        }
        return dataCommon;
    }


    /***
     * 生成请求对象
     *
     *
     */
    public MRequest createRequest(String DeviceId,String DeviceType, Object _data,
                                  String _key) {
        if (!_data.equals("")) {
            data = JsonUtil.toJson(_data);
//            LogCommon.LogShowPrint("生成的json数据"+data);
        }else{
            data="";
        }
        key = _key;
        this.DeviceId = DeviceId;
        this.DeviceType = DeviceType;

        timeStamp();// 生成时间戳
        MRequest req = new MRequest();
        req.setAppId(AppId);// APPID
        req.setSignData(GetSignature());// 签名后的数据
        req.setTimeStamp(timestamp);// 时间戳
        req.setPostData(data);
        req.setDeviceId(DeviceId);
        req.setDeviceType(DeviceType);
        return req;
    }


    public JSONObject jsonObjectXutils(String msg){
        JSONObject jsonObject =null;
        if (!msg.equals("")||msg != null){
            try {
                jsonObject=new JSONObject(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    /***
     * 生成签名
     *
     */
    private String GetSignature() {
        try {
            String str = AppId + DeviceId+DeviceType + data+ timestamp  + key;
//            LogCommon.LogShowPrint("签名原型"+str);
            MD5 md5 = new MD5();
            String result = md5.encryption(str);
//            LogCommon.LogShowPrint("生成签名"+result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 生成时间戳
     *
     */
    public void timeStamp() {
//        int max = 10000;
//        int min = 30000;
//        Random random = new Random();
        long time = System.currentTimeMillis();
        timestamp = String.valueOf(time / 1000);

    }
}
