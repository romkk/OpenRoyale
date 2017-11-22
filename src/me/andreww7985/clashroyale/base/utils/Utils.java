package me.andreww7985.clashroyale.base.utils;

public class Utils {
	public static final char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static final char[] tagArray = "0289PYLQGRJCUV".toCharArray();

	public static String bytesToHex(byte[] b) {
		String result = "";
		for (int j = 0; j < b.length; j++) {
			int v = b[j] & 0xFF;
			result += "0x";
			result += hexArray[v >>> 4];
			result += hexArray[v & 0x0F];
			result += " ";
		}
		return result;
	}

	public static byte[] hexToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static byte[] concatBytes(byte[]... bytes) {
		int length = 0;
		for (byte[] b : bytes)
			length += b.length;
		byte[] result = new byte[length];
		int i = 0;
		for (byte[] b : bytes) {
			System.arraycopy(b, 0, result, i, b.length);
			i += b.length;
		}
		return result;
	}

	public static String idToTag(long id) {
		String result = "";
		while (id > 0) {
			result += tagArray[(int) (id % 14)];
			id /= 14;
		}
		return result;
	}
}
