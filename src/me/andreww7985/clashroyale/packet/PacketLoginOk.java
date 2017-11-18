package me.andreww7985.clashroyale.packet;
import java.io.DataInputStream;

import me.andreww7985.clashroyale.crypto.ClientCrypto;
import me.andreww7985.clashroyale.crypto.Crypto;

public class PacketLoginOk extends Packet {
	public byte unknown4;
	public long userID, homeID;
	public int serverMajorVersion, serverBuild, contentVersion, sessionCount, playTimeSeconds, daysSinceStartedPlaying,
			unknown1;
	public String userToken, gameCenterID, facebookID, environment, facebookAppID, serverTime, accountCreatedDate,
			googleServiceId, unknown2, unknown3, region, contentURL, eventAssetsURL;

	public PacketLoginOk(DataInputStream in, Crypto crypto) throws Exception {
		super((short) 20104, in);
		setData(((ClientCrypto) crypto).decryptLoginOkPacket(getData()));
		userID = getLong();
		homeID = getLong();
		userToken = getString();
		gameCenterID = getString();
		facebookID = getString();
		serverMajorVersion = getRrsInt32();
		serverBuild = getRrsInt32();
		serverBuild = getRrsInt32();
		contentVersion = getRrsInt32();
		environment = getString();
		sessionCount = getRrsInt32();
		playTimeSeconds = getRrsInt32();
		daysSinceStartedPlaying = getRrsInt32();
		facebookAppID = getString();
		serverTime = getString();
		accountCreatedDate = getString();
		unknown1 = getRrsInt32();
		googleServiceId = getString();
		unknown2 = getString();
		unknown3 = getString();
		region = getString();
		contentURL = getString();
		eventAssetsURL = getString();
		unknown4 = getByte();
	}
}
