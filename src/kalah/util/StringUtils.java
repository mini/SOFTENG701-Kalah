package kalah.util;

public class StringUtils {
	public static String repeat(String src, int times) {
		return new String(new char[times]).replace("\0", src);
	}
	
	public static String padNum(int num, int spaces) {
		return String.format("%" + spaces + "d", num);
	}
}
