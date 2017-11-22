package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

public class PacketClientHello extends Packet {
	public int protocol, keyVersion, majorVersion, minorVersion, buildVersion, deviceType, appStore;
	public byte[] resourceHash;
	
	public PacketClientHello(int protocol, int keyVersion, int majorVersion, int minorVersion, int buildVersion,
			String resourceHash, int deviceType, int appStore) {
		super((short) 10100);
		putInt(protocol);
		putInt(keyVersion);
		putInt(majorVersion);
		putInt(minorVersion);
		putInt(buildVersion);
		putString(resourceHash);
		putInt(deviceType);
		putInt(appStore);
	}
	
	public PacketClientHello(DataInputStream in) {
		super((short) 10100, in);
		protocol = getInt();
		keyVersion = getInt();
		majorVersion = getInt();
		minorVersion = getInt();
		buildVersion = getInt();
		resourceHash = getBytes();
		deviceType = getInt();
		appStore = getInt();
	}
}
