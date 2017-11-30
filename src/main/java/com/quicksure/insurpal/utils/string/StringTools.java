package com.quicksure.insurpal.utils.string;

/**
 * @author Liu Dongbo
 * 
 */
public class StringTools {
	/**
	 * 将手机号码中的中间四位转换成*
	 * @param src
	 * @return
	 */
	public static String phoneChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		StringBuffer tempStr = new StringBuffer();
		int srcLength = src.length();
		for (int i = 0; i < srcLength; i++) {
			if (i > 2 && i < 7) {
				tempStr.append("*");
			} else {
				tempStr.append(src.charAt(i));
			}
		}
		return tempStr.toString();
	}

	/**
	 * 将银行卡号限前4后3中间用****填充
	 * @param src
	 * @return
	 */
	public static String bankNoChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return src.substring(0, 4) + "****" + src.substring(src.length() - 3, src.length());
	}

	/**
	 * 将真实姓名限前**后1个名字
	 * @param src
	 * @return
	 */
	public static String realNameChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return "**" + src.charAt(src.length() - 1);
	}

	/**
	 * 将收款人按长度前面用**显示
	 * @param src
	 * @return
	 */
	public static String PayeeNameChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		StringBuffer mark = new StringBuffer("");
		if (src.trim().length() > 7) {
			for (int i = 0; i < src.trim().length() - 4; i++) {
				mark.append("*");
			}
			return src.substring(0, 4) + mark;
		}
		if (src.trim().length() > 3) {
			for (int i = 0; i < src.trim().length() - 2; i++) {
				mark.append("*");
			}
			return src.substring(0, 2) + mark;
		}
		if (src.trim().length() > 1) {
			for (int i = 0; i < src.trim().length() - 1; i++) {
				mark.append("*");
			}
			return src.substring(0, 1) + mark;
		}
		return src.substring(0, 1) + "*";
	}

	/**
	 * 将身份证号限前4后4中间用****填充
	 * @param src
	 * @return
	 */
	public static String idCardChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return src.substring(0, 4) + "****" + src.substring(src.length() - 4, src.length());
	}

	/**
	 * 将email限前4后4中间用****填充
	 * 
	 * 
	 * @param src
	 * @return
	 */
	public static String emailChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return src.substring(0, 4) + "****" + src.substring(src.length() - 4, src.length());
	}
}
