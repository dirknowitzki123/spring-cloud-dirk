package com.cloud.frame.common.util;

public class ThirdPartyServiceLog{
	  private String reqTime;
	  private String reqUrl;
	  private String reqHeader;
	  private String reqParams;
	  private String detailBusin;
	  private String thirdPartyService;
	  private String desc;
	  private String respStatus;
	  private String respMsg;
	  private String respTime;
	  private String errorMsg;
	  private boolean isSuccess;
	public String getReqTime() {
		return reqTime;
	}
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
	public String getReqUrl() {
		return reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	public String getReqHeader() {
		return reqHeader;
	}
	public void setReqHeader(String reqHeader) {
		this.reqHeader = reqHeader;
	}
	public String getReqParams() {
		return reqParams;
	}
	public void setReqParams(String reqParams) {
		if (reqParams != null && reqParams.length() > 1024 * 10) {
			this.reqParams = reqParams.substring(0, 1024 * 10) + "...";
		} else {
			this.reqParams = reqParams;
		}
	}
	public String getThirdPartyService() {
		return thirdPartyService;
	}
	public void setThirdPartyService(String thirdPartyService) {
		this.thirdPartyService = thirdPartyService;
	}
	public String getRespStatus() {
		return respStatus;
	}
	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		if (respMsg != null && respMsg.length() > 1024 * 10 ) {
			this.respMsg = respMsg.substring(0, 1024 * 10) + "...";
		}else{
			this.respMsg = respMsg;
		}
	}
	public String getRespTime() {
		return respTime;
	}
	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	  public String getDetailBusin() {
		return detailBusin;
	}
	public void setDetailBusin(String detailBusin) {
		this.detailBusin = detailBusin;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}  
}