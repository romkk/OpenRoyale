package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

import me.andreww7985.clashroyale.base.packet.field.PacketField;
import me.andreww7985.clashroyale.base.packet.field.FieldNumber;
import me.andreww7985.clashroyale.base.packet.field.FieldString;

public class PacketClientHello extends AbstractPacket {
	public int protocol, keyVersion, majorVersion, minorVersion, buildVersion, deviceType, appStore;
	public String resourceHash;

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
		resourceHash = getString();
		deviceType = getInt();
		appStore = getInt();
	}

	@Override
	public PacketField[] getFields() {
		return new PacketField[] { new FieldNumber("Protocol", FieldNumber.Type.INT, protocol),
				new FieldNumber("Key version", FieldNumber.Type.INT, keyVersion),
				new FieldNumber("Major version", FieldNumber.Type.INT, majorVersion),
				new FieldNumber("Minor version", FieldNumber.Type.INT, minorVersion),
				new FieldNumber("Build version", FieldNumber.Type.INT, buildVersion),
				new FieldString("Resource hash", resourceHash),
				new FieldNumber("Device type", FieldNumber.Type.INT, deviceType),
				new FieldNumber("App store", FieldNumber.Type.INT, appStore) };
	}
}
