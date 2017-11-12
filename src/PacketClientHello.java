public class PacketClientHello extends Packet {
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
}
