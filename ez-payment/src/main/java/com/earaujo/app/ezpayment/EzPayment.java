package com.earaujo.app.ezpayment;

import android.util.Base64;

import com.earaujo.app.ezpayment.rest.GetCardHashKeyResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Eduardo on 29/01/2018.
 */

public class EzPayment {

    public interface OnGetCardHashResult<T> {
        void onResult(T result);
    }

    /**
     *
     * @param cardNumber
     *              Costumer card number ex.: "4111111111111111"
     * @param cardHolderName
     *              Costumer holder name ex.: "João Silva"
     * @param cardExpirationDate
     *              Card Expiration Data. If January of 2020 "0120"
     * @param cardCvv
     *              Card CVV
     * @param callback
     *              Callback result interface
     */
    public static void getCardHash(final String cardNumber, final String cardHolderName, final String cardExpirationDate, final String cardCvv, String encryptionKey, final OnGetCardHashResult<String> callback) {

        final String dataFormat = "card_number={$cardNumber}&card_holder_name={$cardHolderName}&card_expiration_date={$cardExpirationDate}&card_cvv={$cardCvv}";

        RestUtil.getUserProfile(encryptionKey, new RestUtil.RestUtilCallback<GetCardHashKeyResponse>() {
            @Override
            public void onSuccess(GetCardHashKeyResponse result) {
                String id = result.id;
                String publicKey = result.publicKey;

                String cardHolderNameEncoded;
                try {
                    cardHolderNameEncoded = URLEncoder.encode("apples oranges", "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    if (callback!=null) callback.onResult(null);
                    return;
                }

                String dataToEncrypt = dataFormat
                        .replace("{$cardNumber}", cardNumber)
                        .replace("{$cardHolderName}",cardHolderNameEncoded)
                        .replace("{$cardExpirationDate}", cardExpirationDate)
                        .replace("{$cardCvv}", cardCvv);

                String encryptedStringBase64 = Base64.encodeToString(CryptUtils.encrypt(dataToEncrypt, publicKey), Base64.DEFAULT);
                encryptedStringBase64 = encryptedStringBase64.replace("\n","");

                String card_hash = id + "_" + encryptedStringBase64;

                if (callback!=null) callback.onResult(card_hash);
            }

            @Override
            public void onError() {
                if (callback!=null) callback.onResult(null);
            }
        });
    }
}
