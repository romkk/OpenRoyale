import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.packet.AbstractPacket;
import me.andreww7985.clashroyale.base.packet.PacketClientHello;
import me.andreww7985.clashroyale.base.packet.PacketLogin;
import me.andreww7985.clashroyale.base.packet.PacketLoginFailed;
import me.andreww7985.clashroyale.base.packet.PacketLoginOK;
import me.andreww7985.clashroyale.base.packet.PacketServerHello;
import me.andreww7985.clashroyale.base.utils.Utils;

public class Test {
	static final String CONTENT_HASH = "fbed69579e5624b853ae28a8a51f1d40ef52de6b";
	static final byte[] SERVER_KEY = Utils
			.hexToBytes("980cf7bb7262b386fea61034aba7370613627919666b34e6ecf66307a381dd61");
	static final int PROTOCOL = 1, KEY_VERSION = 14, MAJOR_VERSION = 3, MINOR_VERSION = 0, BUILD_VERSION = 690;

	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket("game.clashroyaleapp.com", 9339);
		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
		System.out.println("Connected! " + Utils.bytesToHex(SERVER_KEY) + " " + SERVER_KEY.length);

		ClientCrypto clientCrypto = new ClientCrypto(SERVER_KEY);

		AbstractPacket p = new PacketClientHello(PROTOCOL, KEY_VERSION, MAJOR_VERSION, MINOR_VERSION, BUILD_VERSION,
				CONTENT_HASH, 2, 2);
		out.write(p.getFullData());
		int id = (in.read() & 0xFF) << 8 | (in.read() & 0xFF);
		System.out.println("Received packet ID: " + id);
		byte[] sessionKey;
		switch (id) {
		case 20100:
			p = new PacketServerHello(in, clientCrypto);
			sessionKey = ((PacketServerHello) p).sessionKey;
			System.out.println("Session key: " + Utils.bytesToHex(sessionKey));
			break;
		case 20103:
			p = new PacketLoginFailed(in);
			System.out.println("Error Code: " + ((PacketLoginFailed) p).errorCode);
			System.out.println(((PacketLoginFailed) p).resourceFingerprintData);
			clientSocket.close();
			return;
		}
		p = new PacketLogin(0, null, MAJOR_VERSION, MINOR_VERSION, BUILD_VERSION, CONTENT_HASH, "", "7177033122655077",
				null, "H791", "aa3e6cf0-0162-43d3-8719-f3d3b00356b9", "8.1.0", true, "", "7177033122655077", "en-US",
				(byte) 1, (byte) 0, "", true, "", 0, "", "", "", "", (byte) 0, clientCrypto);
		System.out.println("Sending packet Login");
		out.write(p.getFullData());
		out.flush();
		id = (in.read() & 0xFF) << 8 | (in.read() & 0xFF);
		System.out.println("Received packet ID: " + id);
		p = new PacketLoginOK(in, clientCrypto, null);
		id = (in.read() & 0xFF) << 8 | (in.read() & 0xFF);
		System.out.println("Received packet ID: " + id);
		clientSocket.close();
	}
}
