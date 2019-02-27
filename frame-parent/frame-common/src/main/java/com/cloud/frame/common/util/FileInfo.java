package com.cloud.frame.common.util;

public class FileInfo {
	private static final long serialVersionUID = -315031590230735772L;
	/**
	 * 文件上传时候为文件服务器地址，文件下载时为：文件服务器的本机ip地址
	 */
	private String fileServerAddr;
	/**
	 * 存储过后的新的文件名，不包括扩展名
	 */
	private String uuidName;
	/**
	 * 文件的原始名称，不包括扩展名
	 */
	private String originFileName;
	/**
	 * 文件后缀名，包括.号
	 */
	private String extName;
	/*** 文件大小，单位byte */
	private String fileSize;
	/**
	 * 文件存储路径，不包括文件本身的名字
	 */
	private String savePath;
	
	public FileInfo(){}
	
	public FileInfo(String fileServerAddr, String uuidName, String originFileName, String extName, String fileSize, String savePath) {
		this.fileServerAddr = fileServerAddr;
		this.uuidName = uuidName;
		this.originFileName = originFileName;
		this.extName = extName;
		this.fileSize = fileSize;
		this.savePath = savePath;
	}
	
	
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	
	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileServerAddr() {
		return fileServerAddr;
	}

	public void setFileServerAddr(String fileServerAddr) {
		this.fileServerAddr = fileServerAddr;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
