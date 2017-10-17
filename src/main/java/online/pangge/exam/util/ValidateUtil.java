package online.pangge.exam.util;

public class ValidateUtil {

	/**
	 * 验证是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}

	/**
	 * 验证是否为Email格式
	 */
	public static boolean isEmail(String email) {
		if (hasLength(email)) {
			return email
					.matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b");
		}
		return false;
	}

	/**
	 * 验证字符串长度
	 * 
	 * @param str
	 * @param low
	 * @param hi
	 * @return
	 */
	public static boolean range(String str, int low, int hi) {
		if (hasLength(str)) {
			if (str.length() >= low && str.length() <= hi) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证是否为数字
	 * 
	 * @param validator
	 * @param value
	 * @return
	 */
	public static boolean isDiginit(String str) {
		if (hasLength(str)) {
			return str.matches("[0-9]+");
		}
		return false;
	}

}
