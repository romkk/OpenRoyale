package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

public class PacketLoginFailed extends Packet {
	public int errorCode, secondsUntilMaintenanceEnd;
	public String resourceFingerprintData, redirectDomain, contentURL, updateURL, reason;

	public PacketLoginFailed(int errorCode, String resourceFingerprintData, String redirectDomain, String contentURL,
			String updateURL, String reason, int secondsUntilMaintenanceEnd) {
		super((short) 20103);
		putVarInt(errorCode);
		putString(resourceFingerprintData);
		putString(contentURL);
		putString(updateURL);
		putString(reason);
		putVarInt(secondsUntilMaintenanceEnd);
	}

	public PacketLoginFailed(DataInputStream in) {
		super((short) 20103, in);
		errorCode = getVarInt();
		resourceFingerprintData = getString();
		redirectDomain = getString();
		contentURL = getString();
		updateURL = getString();
		getByte();
		getByte();
		reason = getString();
		secondsUntilMaintenanceEnd = getVarInt();
	}
}
