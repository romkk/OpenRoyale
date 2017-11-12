import java.io.InputStream;

public class PacketServerHello extends Packet {
	public byte[] sessionKey;
	
	public PacketServerHello(InputStream in, Crypto crypto) throws Exception {
		super((short) 20100, in);
		sessionKey = getBytes();
		crypto.setSessionKey(sessionKey);
	}
}
