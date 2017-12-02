package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;
import java.util.HashMap;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.ServerCrypto;

public class PacketFactory {
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
		packetNames.put(24446, "InboxGlobal");
		packetNames.put(21903, "SectorState");
		packetNames.put(25892, "Disconnected");
	}

	public static String getPacketName(AbstractPacket Packet) {
		return packetNames.containsKey((int) Packet.getID()) ? (String) packetNames.get((int) Packet.getID()) : "???";
	}

	public static AbstractPacket getPacketByID(short id, DataInputStream in, ClientCrypto cc, ServerCrypto sc) {
		switch (id) {
		case 10100:
			return new PacketClientHello(in);
		case 20100:
			return new PacketServerHello(in, cc);
		case 10101:
			return new PacketLogin(in, sc, cc);
		case 20104:
			return new PacketLoginOK(in, cc, sc);
		case 20103:
			return new PacketLoginFailed(in);
		default:
			if (cc.getState() == 2 || sc.getState() == 2) {
				if (id >= 20000) {
					return new Packet(id, in, cc);
				} else {
					return new Packet(id, in, sc);
				}
			} else {
				return new Packet(id, in);
			}
		}
	}

}
