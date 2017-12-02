import java.util.Arrays;
import java.util.Random;

import com.neilalexander.jnacl.crypto.curve25519xsalsa20poly1305;

import me.andreww7985.clashroyale.base.crypto.ClientCrypto;
import me.andreww7985.clashroyale.base.crypto.ServerCrypto;
import me.andreww7985.clashroyale.base.utils.Utils;

public class CryptoTest {
	public static void main(String[] args) {
		byte[] serverKey = new byte[32], privateKey = new byte[32];
		/* Generate new random server key and private key */
		curve25519xsalsa20poly1305.crypto_box_keypair(serverKey, privateKey);

		ClientCrypto clientCrypto = new ClientCrypto(serverKey);
		ServerCrypto serverCrypto = new ServerCrypto(privateKey, serverKey);

		byte[] sessionKey = new byte[24];
		new Random().nextBytes(sessionKey);
		clientCrypto.setSessionKey(sessionKey);

		byte[] original = Utils.concatBytes(serverKey, privateKey);
		byte[] encrypted = clientCrypto.encryptPacketLogin(original);
		byte[] decrypted = serverCrypto.decryptPacketLogin(encrypted, clientCrypto);

		if (Arrays.equals(original, decrypted)) {
			System.out.println("PacketLogin encrypt&decrypt - sucessfull!");
		} else {
			System.out.println("PacketLogin encrypt&decrypt - error! Arrays:");
			System.out.println(Utils.bytesToHex(original));
			System.out.println(Utils.bytesToHex(decrypted));
		}
	}
}
