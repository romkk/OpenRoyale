import java.io.InputStream;

public class PacketLoginFailed extends Packet {
	public int errorCode, secondsUntilMaintenanceEnd, unknown1;
	public String resourceFingerprintData, redirectDomain, contentURL, updateURL, reason, unknown2;

	public PacketLoginFailed(InputStream in) throws Exception {
		super((short) 20103, in);
		errorCode = getRrsInt32();
		resourceFingerprintData = getString();
		redirectDomain = getString();
		contentURL = getString();
		updateURL = getString();
		reason = getString();
		secondsUntilMaintenanceEnd = getRrsInt32();
		unknown1 = getByte();
		unknown2 = getString();
	}
}
