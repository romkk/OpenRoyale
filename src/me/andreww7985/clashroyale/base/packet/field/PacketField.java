package me.andreww7985.clashroyale.base.packet.field;

public abstract class PacketField {
	private final String name;
	
	public PacketField(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String toString();
}
