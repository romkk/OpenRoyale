package me.andreww7985.clashroyale.base.packet;
import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.Crypto;

public class PacketLogin extends Packet {

	public PacketLogin(long accountID, String passToken, int majorVersion, int minorVersion, int buildVersion,
			String resourceHash, String UDID, String openUDID, String macAddress, String device, String advertisingGuid,
			String osVersion, boolean isAndroid, String unknown1, String androidID, String preferredDeviceLanguage,
			byte unknown2, byte preferredLanguage, String facebookAttributionId, boolean advertisingEnabled,
			String appleIFV, int appStore, String kunlunSSO, String kunlunUID, String unknown3, String unknown4,
			byte unknown5, Crypto crypto) throws Exception {
		super((short) 10101);
		putLong(accountID);
		putString(passToken);
		putRrsInt32(majorVersion);
		putRrsInt32(minorVersion);
		putRrsInt32(buildVersion);
		putString(resourceHash);
		putString(UDID);
		putString(openUDID);
		putString(macAddress);
		putString(device);
		putString(advertisingGuid);
		putString(osVersion);
		putBoolean(isAndroid);
		putString(unknown1);
		putString(androidID);
		putString(preferredDeviceLanguage);
		putByte(unknown2);
		putByte(preferredLanguage);
		putString(facebookAttributionId);
		putBoolean(advertisingEnabled);
		putString(appleIFV);
		putInt(appStore);
		putString(kunlunSSO);
		putString(kunlunUID);
		putString(unknown3);
		putString(unknown4);
		putByte(unknown5);
		setData(((ClientCrypto) crypto).encryptLoginPacket(getData()));
	}
}
