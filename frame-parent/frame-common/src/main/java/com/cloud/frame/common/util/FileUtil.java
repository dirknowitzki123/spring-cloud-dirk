package com.cloud.frame.common.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	/**  
     *   
     * @param srcfile 文件名数组  
     * @param zipfile 压缩后文件  
     */  
    public static void zipFiles(File[] srcfile, File zipfile) {
    	final int num = 1024;
        byte[] buf = new byte[num];  
        try {  
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                    zipfile));  
            for (int i = 0; i < srcfile.length; i++) {  
                FileInputStream in = new FileInputStream(srcfile[i]);  
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));  
                int len;  
                while ((len = in.read(buf)) > 0) {  
                    out.write(buf, 0, len);  
                }  
                out.closeEntry();  
                in.close();  
            }  
            out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
     //删除指定文件夹下所有文件
  	//param path 文件夹完整绝对路径
    public static void delFolder(String folderPath) {
	     try {
	    	//folderPath = folderPath.replace("\\", "/");
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        File myFilePath = new File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}

		//删除指定文件夹下所有文件
		//param path 文件夹完整绝对路径
	   public static boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	     }
	   
	   /**
		 * 判断文件大小,单位byte
		 * @param ins
		 * @return null 表示不支持计算，
		 */
		public static Long calcFileSize(InputStream ins) {
			if(!ins.markSupported()){
				return null;
			}
			try {
				ins.mark(0);
				long size = 0l;
				long len = 0;
				byte[] buffer = new byte[1024];
				while((len = ins.read(buffer)) !=-1){
					size +=  len;
				}
				ins.reset();
				return Long.valueOf(size);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

}
