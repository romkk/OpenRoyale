import java.io.InputStream;

public class PacketLoginOk extends Packet {
	public byte unknown4;
	public long userId, homeId;
	public int serverMajorVersion, serverBuild, contentVersion, sessionCount, playTimeSeconds, daysSinceStartedPlaying,
			unknown1;
	public String userToken, gameCenterId, facebookId, environment, facebookAppId, serverTime, accountCreatedDate,
			googleServiceId, unknown2, unknown3, region, contentURL, eventAssetsURL;

	public PacketLoginOk(InputStream in, Crypto crypto) throws Exception {
		super((short) 20104, in);
		setData(((ClientCrypto) crypto).decryptLoginOkPacket(getData()));
		userId = getLong();
		homeId = getLong();
		userToken = getString();
		gameCenterId = getString();
		facebookId = getString();
		serverMajorVersion = getRrsInt32();
		serverBuild = getRrsInt32();
		serverBuild = getRrsInt32();
		contentVersion = getRrsInt32();
		environment = getString();
		sessionCount = getRrsInt32();
		playTimeSeconds = getRrsInt32();
		daysSinceStartedPlaying = getRrsInt32();
		facebookAppId = getString();
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
