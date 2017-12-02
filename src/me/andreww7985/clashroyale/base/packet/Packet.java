package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.ServerCrypto;
import me.andreww7985.clashroyale.base.packet.field.PacketField;

public class Packet extends AbstractPacket {
	public Packet(short id) {
		super(id);
	}

	public Packet(short id, DataInputStream in) {
		super(id, in);
	}

	public Packet(short id, DataInputStream in, ClientCrypto cc) {
		super(id, in, cc);
	}

	public Packet(short id, DataInputStream in, ServerCrypto sc) {
		super(id, in, sc);
	}

	public PacketField[] getFields() {
		return new PacketField[] {};
	}

}
