package com.bwbrid.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	/**
	 * inputStreamを文字列に変換
	 * 
	 * @param inputStream
	 * @return 変換後の文字列
	 */
	public static String StreamToString(InputStream inputStream) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int temp = -1;
		String result = null;

		try {
			while ((temp = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, temp);
			}
			result = baos.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
