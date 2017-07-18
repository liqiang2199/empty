package com.empty.cuplibrary.weight.been;

public class MRequest {
	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
	}

	public String getInterfaceName() {
		return InterfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		InterfaceName = interfaceName;
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
	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}
	private String AppId;
	private String InterfaceName;
	private String SignData;
	private String TimeStamp;
	private String Data;
	
}
