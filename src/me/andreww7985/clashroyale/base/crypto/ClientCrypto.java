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

	public byte[] encryptPacketLogin(byte[] message) {
		Nonce nonce = new Nonce(clientKey, serverKey);
		state++;
		return Utils.concatBytes(clientKey,
				encrypt(Utils.concatBytes(sessionKey, encryptNonce.getBytes(), message), nonce));
	}

	public byte[] encryptPacket(byte[] message) {
		return encrypt(message, null);
	}

	public byte[] decryptPacketLoginOK(byte[] message, ServerCrypto serverCrypto) {
		Nonce nonce = new Nonce(clientKey, encryptNonce.getBytes(), serverKey);
		byte[] decrypted = decrypt(message, nonce);
		decryptNonce = new Nonce(Arrays.copyOfRange(decrypted, 0, 24));
		if (serverCrypto != null)
			serverCrypto.setEncryptNonce(new Nonce(Arrays.copyOfRange(decrypted, 0, 24)));
		sharedKey = Arrays.copyOfRange(decrypted, 24, 56);
		state++;
		return Arrays.copyOfRange(decrypted, 56, decrypted.length);
	}

	public byte[] decryptPacket(byte[] message) {
		return decrypt(message, null);
	}
}
