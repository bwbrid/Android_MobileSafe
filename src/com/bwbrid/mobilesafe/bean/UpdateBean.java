package com.bwbrid.mobilesafe.bean;

public class UpdateBean {

	private String versionName;
	private String versionDes;
	private String versionCode;
	private String downloadUrl;

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionDes() {
		return versionDes;
	}

	public void setVersionDes(String versionDes) {
		this.versionDes = versionDes;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
