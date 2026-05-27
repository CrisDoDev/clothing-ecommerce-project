package util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureUtil {

	public static boolean verifySignature(String orderHash, String signatureBase64, String publicKeyBase64) {
		try {

			byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey publicKey = kf.generatePublic(spec);

			byte[] sigBytes = Base64.getDecoder().decode(signatureBase64);

			Signature sigEngine = Signature.getInstance("SHA256withRSA");
			sigEngine.initVerify(publicKey);
			sigEngine.update(orderHash.getBytes(StandardCharsets.UTF_8));

			return sigEngine.verify(sigBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
