package com.earaujo.app.ezpayment;

import android.util.Base64;
import android.util.Log;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by Eduardo on 30/01/2018.
 */

public class CryptUtils {

    private static final String TAG = "CryptUtils";

    public static byte [] encrypt(String clearText, String extpublicKey) {

        PublicKey publicKey = null;

        // READ KEY FROM PEM
        try {
            publicKey = publicKeyFromPEM(extpublicKey);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error reading public key from PEM.");
            return null;
        }

        // Encode the original data with RSA private key
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.ENCRYPT_MODE, publicKey);
            encodedBytes = c.doFinal(clearText.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "RSA encryption error");
            return null;
        }

        return encodedBytes;
    }

    public static PublicKey publicKeyFromPEM(String key)
            throws Exception {

        String privKeyPEM = key
                .replace("-----BEGIN PUBLIC KEY-----\n", "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] encodedPrivateKey = Base64.decode(privKeyPEM, Base64.DEFAULT);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedPrivateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}
