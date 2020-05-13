package kalah.util;

public class StringUtils {
	public static String repeat(String src, int times) {
		return new String(new char[times]).replace("\0", src);
	}
	
	public static String padNum(int num, int spaces) {
		return String.format("%" + spaces + "d", num);
	}
	
	// Sourced from https://stackoverflow.com/questions/8154366/how-to-center-a-string-using-string-format
	/**
	 *	Centers a string within the size limit. Biased towards the right side for odd lengths. 
	 */
	public static String center(String s, int size, String pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
