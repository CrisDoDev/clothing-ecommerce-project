package rsa;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

// Tien ich RSA: tao cap khoa, ky so SHA256withRSA, xac thuc chu ky
public class RSA {

    public static final String SIGNATURE_ALG = "SHA256withRSA";

    // Sinh cap khoa RSA theo do dai bit (1024 / 2048 / 4096)
    public static KeyPair generateKeyPair(int keySize) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        return keyGen.generateKeyPair();
    }

    // Chuyen Public Key sang chuoi Base64 (de paste len web)
    public static String publicKeyToBase64(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Chuyen Private Key sang chuoi Base64 (luu file .pri)
    public static String privateKeyToBase64(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Dung lai Private Key tu chuoi Base64
    public static PrivateKey privateKeyFromBase64(String privateKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(cleanBase64(privateKeyBase64));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    // Dung lai Public Key tu chuoi Base64
    public static PublicKey publicKeyFromBase64(String publicKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(cleanBase64(publicKeyBase64));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    // Ky chuoi du lieu (vd: order_hash) bang Private Key Base64, tra ve chu ky Base64
    public static String sign(String data, String privateKeyBase64) throws Exception {
        PrivateKey privateKey = privateKeyFromBase64(privateKeyBase64);
        Signature signer = Signature.getInstance(SIGNATURE_ALG);
        signer.initSign(privateKey);
        signer.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] sig = signer.sign();
        return Base64.getEncoder().encodeToString(sig);
    }

    // Xac thuc chu ky voi Public Key (dung khi can kiem tra tai cho)
    public static boolean verify(String data, String signatureBase64, String publicKeyBase64) throws Exception {
        PublicKey publicKey = publicKeyFromBase64(publicKeyBase64);
        Signature verifier = Signature.getInstance(SIGNATURE_ALG);
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] sig = Base64.getDecoder().decode(cleanBase64(signatureBase64));
        return verifier.verify(sig);
    }

    // Bo het khoang trang, xuong dong de tranh loi khi paste tu nhieu nguon
    private static String cleanBase64(String s) {
        if (s == null) return "";
        return s.replaceAll("\\s+", "");
    }
}
