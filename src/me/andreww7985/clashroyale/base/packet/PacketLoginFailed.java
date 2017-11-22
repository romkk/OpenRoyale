package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

public class PacketLoginFailed extends Packet {
	public int errorCode, secondsUntilMaintenanceEnd;
	public String resourceFingerprintData, redirectDomain, contentURL, updateURL, reason;

	public PacketLoginFailed(int errorCode, String resourceFingerprintData, String redirectDomain, String contentURL,
			String updateURL, String reason, int secondsUntilMaintenanceEnd) {
		super((short) 20103);
		putRrsInt32(errorCode);
		putString(resourceFingerprintData);
		putString(contentURL);
		putString(updateURL);
		putString(reason);
		putRrsInt32(secondsUntilMaintenanceEnd);
	}

	public PacketLoginFailed(DataInputStream in) {
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
