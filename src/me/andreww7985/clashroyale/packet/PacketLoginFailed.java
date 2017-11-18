package me.andreww7985.clashroyale.packet;
import java.io.DataInputStream;

public class PacketLoginFailed extends Packet {
	public int errorCode, secondsUntilMaintenanceEnd;
	public String resourceFingerprintData, redirectDomain, contentURL, updateURL, reason;

	public PacketLoginFailed(DataInputStream in) throws Exception {
		super((short) 20103, in);
		errorCode = getRrsInt32();
		resourceFingerprintData = getString();
		redirectDomain = getString();
		contentURL = getString();
		updateURL = getString();
		getByte();
		getByte();
		reason = getString();
		secondsUntilMaintenanceEnd = getRrsInt32();
	}
}
