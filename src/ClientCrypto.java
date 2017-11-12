import com.neilalexander.jnacl.crypto.curve25519xsalsa20poly1305;

public class ClientCrypto extends Crypto {
	public ClientCrypto(byte[] serverKey) {
		super(serverKey);
		curve25519xsalsa20poly1305.crypto_box_keypair(clientKey, privateKey);
		beforeNm(serverKey);
		setEncryptNonce(new Nonce());
	}

	public byte[] encryptLoginPacket(byte[] message) {
		Nonce n = new Nonce(clientKey, serverKey);
		byte[] toEncrypt = Utils.concatBytes(sessionKey, encryptNonce.getBytes(), message);
		System.out.println("Unecrypted Login without header:");
		System.out.println(Utils.bytesToHex(message));
		return Utils.concatBytes(clientKey, encrypt(toEncrypt, n));
	}

	public byte[] encryptPacket(byte[] message) {
		return encrypt(message, null);
	}
	
	public byte[] decryptLoginOkPacket(byte[] message) {
		Nonce n = new Nonce(clientKey, encryptNonce.getBytes(), serverKey);
		return decrypt(message, n);
	}
	
	public byte[] decryptPacket(byte[] message) {
		return decrypt(message, null);
	}
}
