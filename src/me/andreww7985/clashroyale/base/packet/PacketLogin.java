package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.ServerCrypto;
import me.andreww7985.clashroyale.base.packet.field.PacketField;
import me.andreww7985.clashroyale.base.packet.field.FieldBoolean;
import me.andreww7985.clashroyale.base.packet.field.FieldNumber;
import me.andreww7985.clashroyale.base.packet.field.FieldString;

public class PacketLogin extends AbstractPacket {
	public long accountID;
	public String passToken, resourceHash, UDID, openUDID, macAddress, device, advertisingGuid, osVersion, unknown1,
			androidID, preferredDeviceLanguage, facebookAttributionId, appleIFV, kunlunSSO, kunlunUID, unknown3,
			unknown4;
	public int majorVersion, minorVersion, buildVersion, appStore;
	public boolean isAndroid, advertisingEnabled;
	public byte unknown2, preferredLanguage, unknown5;

	public PacketLogin(long accountID, String passToken, int majorVersion, int minorVersion, int buildVersion,
			String resourceHash, String UDID, String openUDID, String macAddress, String device, String advertisingGuid,
			String osVersion, boolean isAndroid, String unknown1, String androidID, String preferredDeviceLanguage,
			byte unknown2, byte preferredLanguage, String facebookAttributionId, boolean advertisingEnabled,
			String appleIFV, int appStore, String kunlunSSO, String kunlunUID, String unknown3, String unknown4,
			byte unknown5, ClientCrypto clientCrypto) {
		super((short) 10101);
		putLong(accountID);
		putString(passToken);
		putVarInt(majorVersion);
		putVarInt(minorVersion);
		putVarInt(buildVersion);
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
		putVarInt(appStore);
		putString(kunlunSSO);
		putString(kunlunUID);
		putString(unknown3);
		putString(unknown4);
		putByte(unknown5);
		setData(clientCrypto.encryptPacketLogin(getData()));
	}

	public PacketLogin(DataInputStream in, ServerCrypto serverCrypto, ClientCrypto clientCrypto) {
		super((short) 10101, in);
		setData(serverCrypto.decryptPacketLogin(getData(), clientCrypto));
		accountID = getLong();
		passToken = getString();
		majorVersion = getVarInt();
		minorVersion = getVarInt();
		buildVersion = getVarInt();
		resourceHash = getString();
		UDID = getString();
		openUDID = getString();
		macAddress = getString();
		device = getString();
		advertisingGuid = getString();
		osVersion = getString();
		isAndroid = getBoolean();
		unknown1 = getString();
		androidID = getString();
		preferredDeviceLanguage = getString();
		unknown2 = getByte();
		preferredLanguage = getByte();
		facebookAttributionId = getString();
		advertisingEnabled = getBoolean();
		appleIFV = getString();
		appStore = getVarInt();
		kunlunSSO = getString();
		kunlunUID = getString();
		unknown3 = getString();
		unknown4 = getString();
		unknown5 = getByte();
	}

	@Override
	public PacketField[] getFields() {
		return new PacketField[] { new FieldNumber("Account ID", FieldNumber.Type.LONG, accountID),
				new FieldString("Pass token", passToken),
				new FieldNumber("Major version", FieldNumber.Type.VARINT, majorVersion),
				new FieldNumber("Minor version", FieldNumber.Type.VARINT, minorVersion),
				new FieldNumber("Build version", FieldNumber.Type.VARINT, buildVersion),
				new FieldString("Resource hash", resourceHash), new FieldString("UDID", UDID),
				new FieldString("OpenUDID", openUDID), new FieldString("MAC address", macAddress),
				new FieldString("Device", device), new FieldString("Adversiting GUID", advertisingGuid),
				new FieldString("OS version", osVersion), new FieldBoolean("Is Android", isAndroid),
				new FieldString("Unknown 1", unknown1), new FieldString("Android ID", androidID),
				new FieldString("Preferred device language", preferredDeviceLanguage),
				new FieldNumber("Unknown 2", FieldNumber.Type.BYTE, unknown2),
				new FieldNumber("Preferred language", FieldNumber.Type.BYTE, preferredLanguage),
				new FieldString("Facebook attribution ID", facebookAttributionId),
				new FieldBoolean("Advertising enabled", advertisingEnabled),
				new FieldString("Apple IFV", appleIFV),
				new FieldNumber("App store", FieldNumber.Type.VARINT, appStore),
				new FieldString("Kunlun SSO", kunlunSSO), new FieldString("Kunlun UID", kunlunUID),
				new FieldString("Unknown 3", unknown3), new FieldString("Unknown 4", unknown4),
				new FieldNumber("Unknown 5", FieldNumber.Type.BYTE, unknown5) };
	}
}
