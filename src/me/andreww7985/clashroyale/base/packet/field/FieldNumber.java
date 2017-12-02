package me.andreww7985.clashroyale.base.packet.field;

public class FieldNumber extends PacketField {
	private final long value;
	private final Type type;

	public enum Type {
		VARINT, BYTE, INT, LONG
	}

	public FieldNumber(String name, Type type, long value) {
		super(name);
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}

	public Type getType() {
		return type;
	}

}
