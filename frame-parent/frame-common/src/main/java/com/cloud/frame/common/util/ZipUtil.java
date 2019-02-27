package com.cloud.frame.common.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class ZipUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * 功能:压缩多个文件成一个zip文件
	 * 
	 * @param srcfile：源文件列表
	 * @param zipfile：压缩后的文件
	 */
	public static void zipFiles(List<File> srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			// ZipOutputStream类：完成文件或文件夹的压缩
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.size(); i++) {
				FileInputStream in = new FileInputStream(srcfile.get(i));
				out.putNextEntry(new ZipEntry(srcfile.get(i).getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			log.info("压缩完成.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能:解压缩
	 * 
	 * @param zipfile：需要解压缩的文件
	 * @param descDir：解压后的目标目录
	 */
	public static void unZipFiles(File zipfile, String descDir) {
		try {
			ZipFile zf = new ZipFile(zipfile);
			for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
				log.info("解压缩完成.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: getSession 
	* @Description: TODO(连接服务器) 
	* @param @param host
	* @param @param user
	* @param @param psw
	* @param @param port
	* @param @return    设定文件 
	* @return Session    返回类型 
	* @throws
	 */
	public static Session getSession(String host, String user, String psw, int port) {

		Session session = null;
		try {
			JSch jsch = new JSch();

			session = jsch.getSession(user, host, port);
			Properties config = new Properties();

			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			// session.setTimeout(10000);
			session.connect();
			log.info("Session connected. session会话链接建立");

		} catch (JSchException e) {
			e.printStackTrace();
		} finally {

		}
		return session;
	}
	
	/*
	 * 服务器打包的使用范例
	 * String host = "ip地址";
		int port = 端口号;
		String user = "username";
		String pwd = "password";
		//zip -r -m fileName.zip dirName 打包zip命令 -r递归逐级遍历所有文件及文件夹 -m 打包后删除源文件
		 * 命令详见 http://man.linuxde.net/zip
		// fileName.zip 打包后文件的存放目录及文件名
		// dirName 需要打包的文件所在目录
		String command = "zip -r -m fileName.zip dirName";
		String directory = "文件所在的服务器的目录";
		String downloadFile = "需要下载的文件所在目录及文件名";
		String saveFile = "本地存放文件目录";
		String exec = exec(directory, downloadFile, saveFile, host, user, pwd, port, command);
		log.info(exec);
		log.info("finished"); 
	 * 
	 * 在服务器上需要安装：
	 * 	linux安装zip命令：
		apt-get install zip 或yum install zip
		或者
		linux安装unzip命令：
		apt-get install unzip 或yum install unzip
	 * */
	
	/**
	 * 
	* @Title: exec 
	* @Description: TODO(通过SSH调用shell命令执行文件的打包) 
	* @param @param directory linux文件目录
	* @param @param downloadFile 下载文件所在服务器目录
	* @param @param saveFile 文件下载目标目录
	* @param @param host 服务器ip地址
	* @param @param user 服务器账户
	* @param @param psw 服务器密码
	* @param @param port 服务器端口
	* @param @param command 命令
	* @param @return   执行结果
	* @return String   执行结果
	* @throws
	 */
	public static String exec(String directory, String downloadFile, String saveFile, String host, String user,
			String psw, int port, String command) {
		ChannelExec ExecChannel = null;
		String result = "";
		ChannelSftp sftp = null;
		Session session = null;
		int returnCode = 0;
		try {
			session = ZipUtil.getSession(host, user, psw, port);

			ExecChannel = (ChannelExec) session.openChannel("exec");
			ExecChannel.setErrStream(System.err);
			ExecChannel.setCommand(command);

			log.info("正在执行脚本命令");

			// 接收远程服务器执行命令的结果

			// ------------------------------------------
			ExecChannel.setInputStream(null);
			ExecChannel.connect();
			BufferedReader input = new BufferedReader(new InputStreamReader(ExecChannel.getInputStream()));
			log.info("远程脚本命令是: " + command);

			// StringBuilder message = new StringBuilder();

			InputStream in = ExecChannel.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String buf = null;

			while ((buf = reader.readLine()) != null) {
				result += new String(buf.getBytes("gbk"), "UTF-8") + "    \r\n";
			}

			ExecChannel.disconnect();

			while (!ExecChannel.isClosed()) {

			}
			result = ExecChannel.getExitStatus() + "";
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			sftp.cd(directory);
			Thread.sleep(1000);
			sftp.get(downloadFile, saveFile);

		} catch (JSchException e) {
			result += e.getMessage();
			log.info("shell 脚本没有执行完毕");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ExecChannel != null && !ExecChannel.isClosed()) {
				ExecChannel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: exec 
	* @Description: TODO(通过SSH调用shell命令执行文件的打包) 
	* @param @param directory linux文件目录
	* @param @param host 服务器ip地址
	* @param @param user 服务器账户
	* @param @param psw 服务器密码
	* @param @param port 服务器端口
	* @param @param command 命令
	* @param @return   执行结果
	* @return String   执行结果
	* @throws
	 */
	public static String exec(String directory, String host, String user,
			String psw, int port, String command) {
		ChannelExec ExecChannel = null;
		String result = "";
		ChannelSftp sftp = null;
		Session session = null;
		int returnCode = 0;
		try {
			session = ZipUtil.getSession(host, user, psw, port);

			ExecChannel = (ChannelExec) session.openChannel("exec");
			ExecChannel.setErrStream(System.err);
			ExecChannel.setCommand(command);

			log.info("正在执行脚本命令");

			// 接收远程服务器执行命令的结果

			// ------------------------------------------
			ExecChannel.setInputStream(null);
			ExecChannel.connect();
			BufferedReader input = new BufferedReader(new InputStreamReader(ExecChannel.getInputStream()));
			log.info("远程脚本命令是: " + command);

			// StringBuilder message = new StringBuilder();

			InputStream in = ExecChannel.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String buf = null;

			while ((buf = reader.readLine()) != null) {
				result += new String(buf.getBytes("gbk"), "UTF-8") + "    \r\n";
			}

			ExecChannel.disconnect();

			while (!ExecChannel.isClosed()) {

			}
			result = ExecChannel.getExitStatus() + "";
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			sftp.cd(directory);
			Thread.sleep(1000);

		} catch (JSchException e) {
			result += e.getMessage();
			log.info("shell 脚本没有执行完毕");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ExecChannel != null && !ExecChannel.isClosed()) {
				ExecChannel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}
		return result;
	}
}
