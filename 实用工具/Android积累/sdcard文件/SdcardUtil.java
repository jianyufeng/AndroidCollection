package com.qf.androidday_13_sdcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.os.Environment;

public class SdcardUtil {
	/**
	 * 判断sdcard是否安装好
	 * 
	 * @return true 好 false 没有安装好
	 */

	public static boolean getIsSuccess() {
		// Environment.getExternalStorageState();的到sdcard的安装状态
		String state = Environment.getExternalStorageState();

		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 该方法获得sdcard的根目录
	 * 
	 * @return null 没有得到根目录 path:根目录
	 */
	public static String getPath() {
		if (getIsSuccess()) {
			File file = Environment.getExternalStorageDirectory(); // 根目录文件
			String path = file.getAbsolutePath(); // 获得根目录的绝对路径
			new File(path + "/" + "image.");
			return path;
		}
		return null;
	}

	public static void putData(String fileName, String type, byte[] b) {
		String path = SdcardUtil.getPath();
		if (path != null) {
			if (type != null) {
				path = path + "/" + fileName + "." + type;

			} else {
				path = path + "/" + fileName;

			}
			File file = new File(path);

			try {
				OutputStream out = new FileOutputStream(file);
				try {
					out.write(b, 0, b.length);
					out.flush();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (out != null) {
							out.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
