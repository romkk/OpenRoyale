package me.andreww7985.clashroyale.base.packet.field;

import me.andreww7985.clashroyale.base.utils.Utils;

public class FieldBytes extends PacketField {
	private final byte[] value;

	public FieldBytes(String name, byte[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public String toString() {
		return Utils.bytesToHex(value);
	}

}
