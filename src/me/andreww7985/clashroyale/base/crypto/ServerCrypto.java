package me.andreww7985.clashroyale.base.crypto;

import java.util.Arrays;
import com.neilalexander.jnacl.crypto.curve25519xsalsa20poly1305;

import me.andreww7985.clashroyale.base.utils.Utils;

public class ServerCrypto extends Crypto {
	public ServerCrypto(byte[] privateKey, byte[] serverKey) {
		super(serverKey);
		this.privateKey = privateKey;
	}

	public byte[] decryptLoginPacket(byte[] message, ClientCrypto c) {
		this.clientKey = Arrays.copyOf(message, 32);
		this.sharedKey = new byte[32];
		curve25519xsalsa20poly1305.crypto_box_beforenm(this.sharedKey, this.clientKey, this.privateKey);
		Nonce nonce = new Nonce(clientKey, serverKey);
		byte[] decrypted = decrypt(Arrays.copyOfRange(message, 32, message.length), nonce);
		this.sessionKey = Arrays.copyOfRange(decrypted, 0, 24);
		this.decryptNonce = new Nonce(Arrays.copyOfRange(decrypted, 24, 48));
		c.encryptNonce = this.decryptNonce; // TODO: bug
		return Arrays.copyOfRange(decrypted, 48, decrypted.length);
	}

	public byte[] decryptPacket(byte[] message) {
		return decrypt(message, null);
	}

	public byte[] encryptLoginOkPacket(byte[] message, ClientCrypto c) {
		Nonce nonce = new Nonce(clientKey, serverKey, decryptNonce.getBytes());
		this.sharedKey = c.sharedKey;
		return encrypt(Utils.concatBytes(encryptNonce.getBytes(), c.sharedKey, message), nonce);
	}

	public byte[] encryptPacket(byte[] message) {
		return encrypt(message, null);
	}
}
