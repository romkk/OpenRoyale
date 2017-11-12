
public class Utils {
	static final char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] b) {
		char[] hexChars = new char[b.length * 2 + b.length];
		for (int j = 0; j < b.length; j++) {
			int v = b[j] & 0xFF;
			hexChars[j * 3] = hexArray[v >>> 4];
			hexChars[j * 3 + 1] = hexArray[v & 0x0F];
			hexChars[j * 3 + 2] = ' ';
		}
		return new String(hexChars);
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
}
