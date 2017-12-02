package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.packet.field.PacketField;
import me.andreww7985.clashroyale.base.packet.field.FieldBytes;

public class PacketServerHello extends AbstractPacket {
	public byte[] sessionKey;

	public PacketServerHello(DataInputStream in, ClientCrypto clientCrypto) {
		super((short) 20100, in);
		sessionKey = getBytes();
		clientCrypto.setSessionKey(sessionKey);
	}

	@Override
	public PacketField[] getFields() {
		return new PacketField[] { new FieldBytes("Session key", sessionKey) };
	}
}
