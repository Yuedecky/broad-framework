package com.broad.web.framework.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <pre>
 * 正则表达式
 * </pre>
 *
 * @author yuwenjie
 * @version $Id: PatternUtil.java, v 0.1 2015年5月28日 下午6:00:14 yuwenjie Exp $
 */
public class PatternUtil {

	private static Map<String, String> map = new HashMap<String, String>() {
		/**
		 * <pre>
		 * 
		 * </pre>
		 */
		private static final long serialVersionUID = -433258398793880977L;

		{

			put("一", "一");
			put("二", "二");
			put("三", "三");
			put("四", "四");
			put("五", "五");
			put("六", "六");
			put("七", "七");
			put("八", "八");
			put("九", "九");
			put("十", "十");

		}
	};

	/**
	 * 
	 * <pre>
	 * 校验优惠券兑换码 是否合法
	 * </pre>
	 *
	 * @param exChangeCode
	 * @return
	 */
	public static Pattern exchangePattern = Pattern.compile("[a-zA-Z0-9]{16}");

	public static boolean checkExchange(String exChangeCode) {
		if (StringUtils.isBlank(exChangeCode))
			return false;
		Matcher matcher = exchangePattern.matcher(exChangeCode);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验图形验证码 4位
	 * </pre>
	 *
	 * @param securityCode
	 * @return
	 */
	public static Pattern securityCodePattern = Pattern.compile("[a-zA-Z0-9]{4}");

	public static boolean checkSecurityCode(String securityCode) {
		if (StringUtils.isBlank(securityCode))
			return false;
		Matcher matcher = securityCodePattern.matcher(securityCode);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验图形验证码 4位
	 * </pre>
	 *
	 * @param securityCode
	 * @return
	 */
	public static Pattern number6Pattern = Pattern.compile("[0-9]{6}");

	public static boolean checkNumber6(String number) {
		if (StringUtils.isBlank(number))
			return false;
		Matcher matcher = number6Pattern.matcher(number);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验手机号码
	 * </pre>
	 *
	 * @param phone
	 * @return
	 */
	public static Pattern phonePattern = Pattern.compile("^((13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$");

	public static boolean checkPhone(String phone) {
		if (StringUtils.isBlank(phone) || phone.length() != 11)
			return false;
		Matcher matcher = phonePattern.matcher(phone);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验email
	 * </pre>
	 *
	 * @param email
	 * @return
	 */
	public static Pattern emailPattern = Pattern.compile(".+@(\\w+.)+[a-zA-Z0-9]{2,3}");

	public static boolean checkEmail(String email) {
		if (StringUtils.isBlank(email))
			return false;
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验密码,必须包含数字 字母 以及特殊字符 位置不限 10-20位
	 * </pre>
	 *
	 * @param password
	 * @return
	 */
	public static Pattern passwordPattern = Pattern.compile(
			"(?=.*[a-zA-Z])(?=.*[0-9])(?=.*?[-!\\(\\)\\~@#$%\\^\\&\\*_\\+\\-\\=])[a-zA-Z0-9-!\\(\\)\\~@#$%\\^\\&\\*_\\+\\-\\=]{10,20}");

	public static boolean checkPassWord(String password) {
		if (StringUtils.isBlank(password))
			return false;
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.matches();
	}

	public static boolean checkJumeiAddressSevenChinaNumber(String address) {

		char[] chars = address.toCharArray();
		Integer count = 0;
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (map.containsValue(c + "")) {
				count++;
			} else {
				count = 0;
			}

			if (count >= 7) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 校验是否为.meitun.com的域名下的链接
	 * </pre>
	 *
	 * @param url
	 * @return
	 */
	public static boolean checkHttp(String url) {
		// if(StringUtils.isBlank(url)) return false;
		// Pattern pattern =
		// Pattern.compile("(http|https)://(.+){1}\\.meitun\\.com/(.+)*");
		// Matcher matcher = pattern.matcher(url);
		// return matcher.matches();
		if (StringUtils.isBlank(url))
			return false;

		url = url.substring(url.indexOf("://") + 3);

		if (url.indexOf("/") != -1)
			url = url.substring(0, url.indexOf("/"));

		return url.contains(".meitun.com");
	}

	/**
	 * 
	 * <pre>
	 * 校验是否为内网IP
	 * </pre>
	 *
	 * @param ip
	 * @return
	 */
	public static final Pattern innerIpPattern = Pattern.compile(
			"(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})");

	public static boolean isInnerIp(String ip) {
		Matcher matcher = innerIpPattern.matcher(ip);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验是否为合法IP
	 * </pre>
	 *
	 * @param ip
	 * @return
	 */
	public static final Pattern isIpPattern = Pattern
			.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

	public static boolean isIp(String ip) {
		Matcher matcher = isIpPattern.matcher(ip);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验是否为纯数字
	 * </pre>
	 *
	 * @param number
	 * @return
	 */
	public static final Pattern numberPattern = Pattern.compile("^[-]?\\d+$");

	public static boolean isNumber(String number) {
		Matcher matcher = numberPattern.matcher(number);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验聚美方式的用户名
	 * </pre>
	 *
	 * @param userName
	 * @return
	 */
	public static final Pattern jumeiUserNamePattern = Pattern.compile("^[\u4E00-\u9FA5A-Za-z,，．.。·\\s\u3000]{2,30}$");

	public static boolean checkJumeiUserName(String userName) {
		Matcher matcher = jumeiUserNamePattern.matcher(userName);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验用户收货地址（聚美）
	 * </pre>
	 *
	 * @param address
	 * @return
	 */
	public static final Pattern jumeiAddressPattern = Pattern
			.compile("^(?=.*?[\u4E00-\u9FA5])[a-zA-Z0-9\u4E00-\u9FA5－\\-＃#（\\(）\\)－——：:，,]{6,100}$");

	public static boolean checkJumeiAddress(String address) {
		Matcher matcher = jumeiAddressPattern.matcher(address);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 较远用户收货地址中是否有连续7个数字（聚美）
	 * </pre>
	 *
	 * @param address
	 * @return
	 */
	public static final Pattern jumeiAddressSevenNumberPattern = Pattern.compile("^.*\\d{7}.*$");

	public static boolean checkJumeiAddressSevenNumber(String address) {
		Matcher matcher = jumeiAddressSevenNumberPattern.matcher(address);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 替换聚美方式的用户名
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static String replaceForJumei(String str) {
		if (StringUtils.isBlank(str))
			return "";
		return str.replaceAll("[,，．.。·]", "·").replaceAll("[\\s|\u3000]", "");
	}

	/**
	 * 
	 * <pre>
	 * 替换收货地址中的中文特殊字符（聚美）
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static String replaceAddressForJumei(String str) {
		if (StringUtils.isBlank(str))
			return "";
		return str.replaceAll("[——－]", "-").replaceAll("＃", "#").replaceAll("（", "(").replaceAll("（", "(")
				.replaceAll("）", ")").replaceAll("：", ":").replaceAll("，", ",");
	}

	/**
	 * 
	 * <pre>
	 * 删除制表符，空格等
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static final Pattern strnPattern = Pattern.compile("\\s*|t|r|n");

	public static String removeSTRN(String str) {
		if (StringUtils.isBlank(str))
			return null;
		Matcher m = strnPattern.matcher(str);
		return m.replaceAll("");
	}

	/**
	 * 
	 * <pre>
	 * 校验是否为json格式
	 * </pre>
	 *
	 * @param json
	 * @return
	 */
	public static Boolean checkJson(String json) {
		if (StringUtils.isBlank(json))
			return false;
		try {
			JSONObject.parse(json);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <pre>
	 * 校验是否为纯中文
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static final Pattern chinesePattern = Pattern.compile("[\\u4e00-\\u9fa5]*");

	public static Boolean checkIsChinese(String str) {
		if (StringUtils.isBlank(str))
			return false;
		Matcher matcher = chinesePattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 
	 * <pre>
	 * 校验是否包含中文
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static final Pattern containChinesePattern = Pattern.compile("[\u4e00-\u9fa5]");

	public static Boolean checkContainChinese(String str) {
		if (StringUtils.isBlank(str))
			return false;
		Matcher matcher = containChinesePattern.matcher(str);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 删除制表符，空格等
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static final Pattern tCodePattern = Pattern.compile("(tcode)=([^&]+)", Pattern.CASE_INSENSITIVE);

	public static String getTcode(String str) {
		if (StringUtils.isBlank(str))
			return null;
		Matcher matcher = tCodePattern.matcher(str);
		while (matcher.find()) {
			String s = matcher.group();
			return s.split("=")[1];
		}
		return null;
	}

	/**
	 * 
	 * <pre>
	 * 校验是否包含中文
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static final Pattern YYYY_MM_DD_HH_MM_SS = Pattern
			.compile("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$");
	public static final Pattern YYYY_MM_DD = Pattern.compile("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}$");

	public static String getTimeFomart(String str) {
		if (StringUtils.isBlank(str))
			return null;
		str = str.trim();
		if (checkTimeFormat(YYYY_MM_DD_HH_MM_SS, str)) {
			return DateUtils.PATTERN_YMDHMS;
		}
		if (checkTimeFormat(YYYY_MM_DD, str)) {
			return DateUtils.PATTERN_YMD;
		}
		return null;
	}

	/**
	 * @param pattern
	 * @param str
	 * @return
	 */
	private static Boolean checkTimeFormat(Pattern pattern, String str) {
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 校验证件号
	 * @param cerNumber
	 * @return
	 */
	public static Boolean checkIdentity(String cerNumber) {
		if (cerNumber.length() == 15 || cerNumber.length() == 18) {
			if (!cardCodeVerifySimple(cerNumber)) {
				return false;
			} 
			if (cerNumber.length() == 18 && !cardCodeVerify(cerNumber)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	// 第一代身份证正则表达式(15位)
	public static final Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
	// 第二代身份证正则表达式(18位)
	public static final Pattern isIDCard2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$");
	private static boolean cardCodeVerifySimple(String cerNumber) {
		Matcher matcher1 = isIDCard1.matcher(cerNumber);
		if (matcher1.find()) {
			return true;
		}
		
		Matcher matcher2 = isIDCard2.matcher(cerNumber);
		if (matcher2.find()) {
			return true;
		}
		return false;
	}
	
	private static boolean cardCodeVerify(String cerNumber) {
		int i = 0;
		String r = "error";
		String lastnumber = "";

		i += Integer.parseInt(cerNumber.substring(0, 1)) * 7;
		i += Integer.parseInt(cerNumber.substring(1, 2)) * 9;
		i += Integer.parseInt(cerNumber.substring(2, 3)) * 10;
		i += Integer.parseInt(cerNumber.substring(3, 4)) * 5;
		i += Integer.parseInt(cerNumber.substring(4, 5)) * 8;
		i += Integer.parseInt(cerNumber.substring(5, 6)) * 4;
		i += Integer.parseInt(cerNumber.substring(6, 7)) * 2;
		i += Integer.parseInt(cerNumber.substring(7, 8)) * 1;
		i += Integer.parseInt(cerNumber.substring(8, 9)) * 6;
		i += Integer.parseInt(cerNumber.substring(9, 10)) * 3;
		i += Integer.parseInt(cerNumber.substring(10, 11)) * 7;
		i += Integer.parseInt(cerNumber.substring(11, 12)) * 9;
		i += Integer.parseInt(cerNumber.substring(12, 13)) * 10;
		i += Integer.parseInt(cerNumber.substring(13, 14)) * 5;
		i += Integer.parseInt(cerNumber.substring(14, 15)) * 8;
		i += Integer.parseInt(cerNumber.substring(15, 16)) * 4;
		i += Integer.parseInt(cerNumber.substring(16, 17)) * 2;
		i = i % 11;
		lastnumber = cerNumber.substring(17, 18);
		if (i == 0) {
			r = "1";
		}
		if (i == 1) {
			r = "0";
		}
		if (i == 2) {
			r = "x";
		}
		if (i == 3) {
			r = "9";
		}
		if (i == 4) {
			r = "8";
		}
		if (i == 5) {
			r = "7";
		}
		if (i == 6) {
			r = "6";
		}
		if (i == 7) {
			r = "5";
		}
		if (i == 8) {
			r = "4";
		}
		if (i == 9) {
			r = "3";
		}
		if (i == 10) {
			r = "2";
		}
		if (r.equals(lastnumber.toLowerCase())) {
			return true;
		}
		return false;
	}

	/**
	 * 校验银行卡卡号
	 * 校验过程： 
	 * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
	 * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
	 * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
	 * @param bankCard
	 * @return
	 */
	public static boolean checkBankCard(String bankCard) {
		if (bankCard.length() < 15 || bankCard.length() > 19) {
			return false;
		}
		char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return bankCard.charAt(bankCard.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * @param nonCheckCodeBankCard
	 * @return
	 */
	public static char getBankCardCheckCode(String nonCheckCodeBankCard) {
		if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
				|| !nonCheckCodeBankCard.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeBankCard.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	public static final Pattern numericParttern = Pattern.compile("^[-]?\\d*.?\\d*$");
	public static boolean isNumeric(String str) {  
		return numericParttern.matcher(str).matches();  
	}
	
	public static final Pattern imagePattern = Pattern.compile(".+\\.(gif|jpg|jpeg|bmp|png)$");
	public static boolean isImage(String str) {  
		return imagePattern.matcher(str).matches();  
	}
}
