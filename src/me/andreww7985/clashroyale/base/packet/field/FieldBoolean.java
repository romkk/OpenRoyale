package me.andreww7985.clashroyale.base.packet.field;

public class FieldBoolean extends PacketField {
	private final boolean value;
	
	public FieldBoolean(String name, boolean value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value ? "true" : "false";
	}

}
