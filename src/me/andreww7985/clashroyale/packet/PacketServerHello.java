package me.andreww7985.clashroyale.packet;
import java.io.DataInputStream;

import me.andreww7985.clashroyale.crypto.Crypto;

public class PacketServerHello extends Packet {
	public byte[] sessionKey;

	public PacketServerHello(DataInputStream in, Crypto crypto) throws Exception {
		super((short) 20100, in);
		sessionKey = getBytes();
		crypto.setSessionKey(sessionKey);
	}
}
