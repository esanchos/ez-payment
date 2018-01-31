package com.earaujo.app.ezpayment.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eduardo on 17/01/2018.
 */

public interface BackendInterface {

    @GET("transactions/card_hash_key")
    Call<GetCardHashKeyResponse> getCardHashKey(
            @Query("encryption_key") String encryptionKey
    );
}
