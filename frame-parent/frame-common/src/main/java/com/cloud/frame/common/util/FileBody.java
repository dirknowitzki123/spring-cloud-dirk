package com.cloud.frame.common.util;

import java.io.InputStream;
import java.io.Serializable;

public class FileBody extends FileInfo  implements Serializable {
	private static final long serialVersionUID = 115828257987685010L;
	
	
	/*** 文件上传或下载时的文件输入流 */
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
