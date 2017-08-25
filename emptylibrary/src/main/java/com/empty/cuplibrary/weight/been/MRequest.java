package com.empty.cuplibrary.weight.been;

public class MRequest {


	private String AppId;
	private String DeviceType;
	private String SignData;
	private String TimeStamp;
	private String DeviceId;
	private String PostData;

	public String getPostData() {
		return PostData;
	}

	public void setPostData(String postData) {
		PostData = postData;
	}

	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getSignData() {
		return SignData;
	}

	public void setSignData(String signData) {
		SignData = signData;
	}

	public String getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}
	
}
