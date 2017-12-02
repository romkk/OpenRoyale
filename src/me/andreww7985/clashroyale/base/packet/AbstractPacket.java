package me.andreww7985.clashroyale.base.packet;

import java.io.DataInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.ServerCrypto;
import me.andreww7985.clashroyale.base.packet.field.PacketField;

public abstract class AbstractPacket {
	private final short id;
	public int pointer = 0;
	private ArrayList<Byte> data = new ArrayList<Byte>();

	public AbstractPacket(final short id, final DataInputStream in) {
		this.id = id;
		try {
			int length = (in.read() & 0xFF) << 16 | (in.read() & 0xFF) << 8 | (in.read() & 0xFF);
			byte[] temp = new byte[length];
			in.readShort();
			in.readFully(temp);
			for (byte b : temp)
				data.add(b);
		} catch (Exception e) {
			System.err.println("Failed to read packet " + id);
			e.printStackTrace();
		}
	}

	public AbstractPacket(final short id, final DataInputStream in, ClientCrypto clientCrypto) {
		this(id, in);
		setData(clientCrypto.decryptPacket(getData()));
	}
	
	public AbstractPacket(final short id, final DataInputStream in, ServerCrypto serverCrypto) {
		this(id, in);
		setData(serverCrypto.decryptPacket(getData()));
	}

	public AbstractPacket(final short id) {
		this.id = id;
	}

	public short getID() {
		return id;
	}

	public int getLength() {
		return data.size();
	}

	public int getFullLength() {
		return getLength() + 7;
	}

	public byte[] getData() {
		byte[] result = new byte[data.size()];
		for (int i = 0; i < data.size(); i++) {
			result[i] = data.get(i).byteValue();
		}
		return result;
	}

	public void setData(byte[] value) {
		data.clear();
		for (byte b : value)
			data.add(b);
		pointer = 0;
	}

	public byte[] getFullData() {
		return ByteBuffer.allocate(data.size() + 7).putShort(id).putInt(data.size() << 8).put((byte) 0).put(getData())
				.array();
	}

	protected byte getByte() {
		return data.get(pointer++);
	}

	protected void putByte(final byte value) {
		data.add(pointer++, value);
	}

	protected boolean getBoolean() {
		return data.get(pointer++) == 1;
	}

	protected void putBoolean(final boolean value) {
		data.add(pointer++, value ? (byte) 0 : (byte) 1);
	}

	protected int getInt() {
		return (data.get(pointer++) & 0xFF) << 24 | (data.get(pointer++) & 0xFF) << 16
				| (data.get(pointer++) & 0xFF) << 8 | (data.get(pointer++) & 0xFF);
	}

	protected void putInt(final int value) {
		data.add(pointer++, (byte) (value >> 24));
		data.add(pointer++, (byte) (value >> 16));
		data.add(pointer++, (byte) (value >> 8));
		data.add(pointer++, (byte) (value));
	}

	protected long getLong() {
		return (data.get(pointer++) & 0xFF) << 4 | (data.get(pointer++) & 0xFF) << 56
				| (data.get(pointer++) & 0xFF) << 48 | (data.get(pointer++) & 0xFF << 32)
				| (data.get(pointer++) & 0xFF) << 24 | (data.get(pointer++) & 0xFF) << 16
				| (data.get(pointer++) & 0xFF) << 8 | (data.get(pointer++) & 0xFF);
	}

	protected void putLong(final long value) {
		data.add(pointer++, (byte) (value >> 64));
		data.add(pointer++, (byte) (value >> 56));
		data.add(pointer++, (byte) (value >> 48));
		data.add(pointer++, (byte) (value >> 32));
		data.add(pointer++, (byte) (value >> 24));
		data.add(pointer++, (byte) (value >> 16));
		data.add(pointer++, (byte) (value >> 8));
		data.add(pointer++, (byte) (value));
	}

	protected int getVarInt() {
		int c = 0, value = 0, seventh, msb, b;
		do {
			b = getByte();
			if (c == 0) {
				seventh = (b & 0x40) >> 6;
				msb = (b & 0x80) >> 7;
				b = b << 1;
				b = b & ~(0x181);
				b = b | (msb << 7) | (seventh);
			}
			value |= (b & 0x7f) << (7 * c);
			++c;
		} while ((b & 0x80) != 0);
		value = ((value >>> 1) ^ -(value & 1)) | 0;

		return value;
	}

	protected void putVarInt(int value) {
		boolean rotate = true;
		int lsb, msb;
		byte b;
		if (value == 0) {
			putByte((byte) 0);
		} else {
			value = (value << 1) ^ (value >> 31);
			while (value > 0) {
				b = (byte) (value & 0x7f);
				if (value >= 0x80)
					b |= 0x80;
				if (rotate) {
					rotate = false;
					lsb = b & 0x1;
					msb = (b & 0x80) >> 7;
					b >>= 1;
					b &= ~0xC0;
					b |= (msb << 7) | (lsb << 6);
				}
				putByte(b);
				value >>= 7;
			}
		}
	}

	protected String getString() {
		byte[] temp = getBytes();
		return temp == null ? null : new String(temp, StandardCharsets.UTF_8);
	}

	protected void putString(final String value) {
		if (value == null) {
			putInt(0xFFFFFFFF);
		} else {
			byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
			putBytes(bytes);
		}
	}

	protected byte[] getBytes() {
		int length = getInt();
		if (length == 0xFFFFFFFF)
			return null;
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++)
			result[i] = getByte();
		return result;
	}

	protected void putBytes(byte[] value) {
		putInt(value.length);
		for (byte b : value)
			putByte(b);
	}

	public PacketField[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}
}
