package me.andreww7985.clashroyale.base.crypto;
import java.util.Arrays;

import com.neilalexander.jnacl.crypto.curve25519xsalsa20poly1305;

import me.andreww7985.clashroyale.base.utils.Utils;

public class ClientCrypto extends Crypto {
	public ClientCrypto(byte[] serverKey) {
		super(serverKey);
		curve25519xsalsa20poly1305.crypto_box_keypair(clientKey, privateKey);
		beforeNm(serverKey);
		setEncryptNonce(new Nonce());
	}

	public byte[] encryptLoginPacket(byte[] message) {
		Nonce nonce = new Nonce(clientKey, serverKey);
		return Utils.concatBytes(clientKey,
				encrypt(Utils.concatBytes(sessionKey, encryptNonce.getBytes(), message), nonce));
	}

	public byte[] encryptPacket(byte[] message) {
		return encrypt(message, null);
	}

	public byte[] decryptLoginOkPacket(byte[] message) {
		Nonce n = new Nonce(clientKey, encryptNonce.getBytes(), serverKey);
		byte[] decrypted = decrypt(message, n);
		decryptNonce = new Nonce(Arrays.copyOfRange(decrypted, 0, 24));
		sharedKey = Arrays.copyOfRange(decrypted, 24, 56);
		return Arrays.copyOfRange(decrypted, 56, decrypted.length);
	}

	public byte[] decryptPacket(byte[] message) {
		return decrypt(message, null);
	}
}
