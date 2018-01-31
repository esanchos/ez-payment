package com.earaujo.app.ezpayment.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eduardo on 31/01/2018.
 */

public class GetCardHashKeyResponse {

    @SerializedName("id")
    public String id;

    @SerializedName("public_key")
    public String publicKey;
}
