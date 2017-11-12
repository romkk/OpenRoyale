import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	static final String CONTENT_HASH = "b7b4b10c5bfed52f3c51ebc9bdec3930d7470b20";
	static final byte[] SERVER_KEY = Utils
			.hexToBytes("980cf7bb7262b386fea61034aba7370613627919666b34e6ecf66307a381dd61");
	static final int PROTOCOL = 1, KEY_VERSION = 14, MAJOR_VERSION = 3, MINOR_VERSION = 0, BUILD_VERSION = 690;

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("game.clashroyaleapp.com", 9339);
		OutputStream out = s.getOutputStream();
		InputStream in = s.getInputStream();
		System.out.println("Connected!");

		Crypto c = new ClientCrypto(SERVER_KEY);

		Packet p = new PacketClientHello(PROTOCOL, KEY_VERSION, MAJOR_VERSION, MINOR_VERSION, BUILD_VERSION,
				CONTENT_HASH, 2, 2);
		out.write(p.getFullData());
		System.out.println(Utils.bytesToHex(p.getFullData()));
		int id = (in.read() & 0xFF) << 8 | (in.read() & 0xFF);
		System.out.println("Received packet ID: " + id);
		byte[] sessionKey;
		switch (id) {
		case 20100:
			p = new PacketServerHello(in, c);
			System.out.println(Utils.bytesToHex(p.getFullData()));
			sessionKey = ((PacketServerHello) p).sessionKey;
			System.out.println("Session key: " + Utils.bytesToHex(sessionKey));
			break;
		case 20103:
			p = new PacketLoginFailed(in);
			System.out.println(Utils.bytesToHex(p.getFullData()));
			System.out.println("Error Code: " + ((PacketLoginFailed) p).errorCode);
			return;
		}
		System.out.println("Server key:");
		System.out.println(Utils.bytesToHex(SERVER_KEY));
		System.out.println("Encrypt nonce:");
		System.out.println(Utils.bytesToHex(c.getEncryptNonce().getBytes()));
		
		p = new PacketLogin(0, null, MAJOR_VERSION, MINOR_VERSION, BUILD_VERSION, CONTENT_HASH, null,
				"c0389670ea3b1978", null, "H791", "aa3e6cf0-0162-43d3-8719-f3d3b00356b7", "8.1.0", true, null,
				"5c0dd7774a877988", "en-US", (byte) 0, (byte) 0, null, false, null, 0, null, null, null, null, (byte) 0,
				c);
		System.out.println("Sending PacketLogin:");
		out.write(p.getFullData());
		System.out.println(Utils.bytesToHex(p.getFullData()));
		id = (in.read() & 0xFF) << 8 | (in.read() & 0xFF);
		System.out.println("Received packet ID: " + id);
	}
}
