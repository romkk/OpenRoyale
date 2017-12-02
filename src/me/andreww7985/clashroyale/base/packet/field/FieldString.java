package me.andreww7985.clashroyale.base.packet.field;

public class FieldString extends PacketField {
	private final String value;
	
	public FieldString(String name, String value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
