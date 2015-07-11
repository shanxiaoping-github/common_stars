package sxp.android.framework.util;

import java.io.File;
import java.util.UUID;

import android.os.Environment;

/**
 * 文件操作
 * 
 * @author Administrator
 *
 */
public class FileUtil {
	/**
	 * 获得sd卡路径
	 * 
	 * @return
	 */
	public static String getSDPath() {

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			File sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
			return sdDir.toString();
		} else {

			return "";
		}

	}

	/**
	 * 创建路径
	 * 
	 * @param path
	 *            路径地址
	 * @return 是否创建成功
	 */
	public static Boolean creatPath(String path) {

		File file = new File(path);
		if (file.exists()) {

			return false;
		}
		if (path.endsWith(File.separator)) {
			file.mkdirs();
			return true;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录

			if (!file.getParentFile().mkdirs()) {

				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {

				return true;
			} else {

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

	}

	/**
	 * 获得唯一标示
	 * 
	 * @return
	 */
	public static String getMyUUID() {

		UUID uuid = UUID.randomUUID();
		String uniqueId = uuid.toString();
		String resultStr = uniqueId.replaceAll("-", "");
		return resultStr;
	}

	/**
	 * 判断是否是图片
	 * 
	 * @param imagePath
	 * @return
	 */
	private static final String[] imageSuffix = new String[] {

	".bmp", ".BMP", ".jpeg", ".JPEG", ".jpg", ".JPG", ".gif", ".GIF", ".png",
			".PNG" };

	public static boolean isImage(String imagePath) {
		for (String suffix : imageSuffix) {
			if (imagePath.endsWith(suffix)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 获得一个文件的大小
	 * 
	 * @param file
	 * @return
	 */
	public static long fileSize(File file) {
		if (file.exists() && file.isFile()) {
			return file.length();
		}

		return 0;

	}

	/**
	 * 返回文件大小
	 * 
	 * @param fileName
	 * @return
	 */
	public static long fileSize(String fileName) {
		File file = new File(fileName);
		return fileSize(file);
	}

}
