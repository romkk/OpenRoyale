package me.andreww7985.clashroyale.base.packet;

import java.util.HashMap;

public class PacketMap {
	private static final HashMap<Integer, String> packetNames = new HashMap<Integer, String>();

	static {
		packetNames.put(10100, "ClientHello");
		packetNames.put(20103, "LoginFailed");
		packetNames.put(20100, "ServerHello");
		packetNames.put(10101, "Login");
		packetNames.put(20104, "LoginOK");
		packetNames.put(24101, "OwnHomeData");
		packetNames.put(24102, "OwnAvatarData");
		packetNames.put(10108, "KeepAlive");
		packetNames.put(20108, "KeepAliveOK");
		packetNames.put(10107, "ClientCapabilities");
		packetNames.put(20105, "FriendList");
		packetNames.put(14406, "AskForBattleReplayStream");
		packetNames.put(10113, "SetDeviceToken");
		packetNames.put(14102, "EndClientTurn");
		packetNames.put(14405, "AskForAvatarStream");
		packetNames.put(24411, "AvatarStream");
		packetNames.put(14101, "GoHome");
		packetNames.put(24111, "AvailableServerCommand");
		packetNames.put(24413, "BattleReportStream");
		packetNames.put(16103, "AskForClanTournamentsList");
		packetNames.put(24411, "AvatarStream");
	}

	public static String getPacketName(Packet packet) {
		return packetNames.containsKey((int) packet.getID()) ? (String) packetNames.get((int) packet.getID()) : "???";
	}

}
