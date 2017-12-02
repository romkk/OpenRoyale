package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.ServerCrypto;
import me.andreww7985.clashroyale.base.packet.field.PacketField;
import me.andreww7985.clashroyale.base.packet.field.FieldNumber;
import me.andreww7985.clashroyale.base.packet.field.FieldString;

public class PacketLoginOK extends AbstractPacket {
	public byte unknown4;
	public long userID, homeID;
	public int serverMajorVersion, serverBuild, contentVersion, sessionCount, playTimeSeconds, daysSinceStartedPlaying,
			unknown1;
	public String userToken, gameCenterID, facebookID, environment, facebookAppID, serverTime, accountCreatedDate,
			googleServiceId, unknown2, unknown3, region, contentURL, eventAssetsURL;

	public PacketLoginOK(DataInputStream in, ClientCrypto clientCrypto, ServerCrypto serverCrypto) {
		super((short) 20104, in);
		setData(clientCrypto.decryptPacketLoginOK(getData(), serverCrypto));
		userID = getLong();
		homeID = getLong();
		userToken = getString();
		gameCenterID = getString();
		facebookID = getString();
		serverMajorVersion = getVarInt();
		serverBuild = getVarInt();
		serverBuild = getVarInt();
		contentVersion = getVarInt();
		environment = getString();
		sessionCount = getVarInt();
		playTimeSeconds = getVarInt();
		daysSinceStartedPlaying = getVarInt();
		facebookAppID = getString();
		serverTime = getString();
		accountCreatedDate = getString();
		unknown1 = getVarInt();
		googleServiceId = getString();
		unknown2 = getString();
		unknown3 = getString();
		region = getString();
		contentURL = getString();
		eventAssetsURL = getString();
		unknown4 = getByte();
	}

	@Override
	public PacketField[] getFields() {
		return new PacketField[] { new FieldNumber("User ID", FieldNumber.Type.LONG, userID),
				new FieldNumber("Home ID", FieldNumber.Type.LONG, homeID), new FieldString("User token", userToken),
				new FieldString("Gamecenter ID", gameCenterID), new FieldString("Facebook ID", facebookID),
				new FieldNumber("Server major version", FieldNumber.Type.VARINT, serverMajorVersion),
				new FieldNumber("Server build", FieldNumber.Type.VARINT, serverBuild),
				new FieldNumber("Server build (again?)", FieldNumber.Type.VARINT, serverBuild),
				new FieldNumber("Content version", FieldNumber.Type.VARINT, contentVersion),
				new FieldString("Environment", environment),
				new FieldNumber("Session count", FieldNumber.Type.VARINT, sessionCount),
				new FieldNumber("Play time seconds", FieldNumber.Type.VARINT, playTimeSeconds),
				new FieldNumber("Days since started playing", FieldNumber.Type.VARINT, daysSinceStartedPlaying),
				new FieldString("Facebook app ID", facebookAppID), new FieldString("Server time", serverTime),
				new FieldString("Account created date", accountCreatedDate),
				new FieldNumber("Unknown 1", FieldNumber.Type.VARINT, unknown1),
				new FieldString("Google service ID", googleServiceId), new FieldString("Unknown 2", unknown2),
				new FieldString("Unknown 3", unknown3), new FieldString("Region", region),
				new FieldString("Content URL", contentURL), new FieldString("Event assetsURL", eventAssetsURL),
				new FieldNumber("Unknown 4", FieldNumber.Type.BYTE, unknown4) };
	}
}
