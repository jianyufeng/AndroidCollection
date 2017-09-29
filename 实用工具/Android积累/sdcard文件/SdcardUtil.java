package com.qf.androidday_13_sdcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.os.Environment;

public class SdcardUtil {
	/**
	 * �ж�sdcard�Ƿ�װ��
	 * 
	 * @return true �� false û�а�װ��
	 */

	public static boolean getIsSuccess() {
		// Environment.getExternalStorageState();�ĵ�sdcard�İ�װ״̬
		String state = Environment.getExternalStorageState();

		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * �÷������sdcard�ĸ�Ŀ¼
	 * 
	 * @return null û�еõ���Ŀ¼ path:��Ŀ¼
	 */
	public static String getPath() {
		if (getIsSuccess()) {
			File file = Environment.getExternalStorageDirectory(); // ��Ŀ¼�ļ�
			String path = file.getAbsolutePath(); // ��ø�Ŀ¼�ľ���·��
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
