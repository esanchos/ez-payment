package com.earaujo.app.ezpayment;

import com.earaujo.app.ezpayment.rest.APIClient;
import com.earaujo.app.ezpayment.rest.BackendInterface;
import com.earaujo.app.ezpayment.rest.GetCardHashKeyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Eduardo on 30/01/2018.
 */

public class RestUtil {

    public interface RestUtilCallback<T> {
        void onSuccess(T result);
        void onError();
    }

    public static void getUserProfile(String encryptionKey, final RestUtilCallback<GetCardHashKeyResponse> callback) {
        Retrofit retrofit = APIClient.getClient();
        BackendInterface apiService = retrofit.create(BackendInterface.class);
        Call<GetCardHashKeyResponse> call = apiService.getCardHashKey(
                encryptionKey
        );
        call.enqueue(new Callback<GetCardHashKeyResponse>() {
            @Override
            public void onResponse(Call<GetCardHashKeyResponse> call, Response<GetCardHashKeyResponse> response) {
                GetCardHashKeyResponse result = response.body();

                if (response.code()==200) {
                    if (callback != null) callback.onSuccess(result);
                } else {
                    if (callback != null) callback.onError();
                }
            }

            @Override
            public void onFailure(Call<GetCardHashKeyResponse> call, Throwable t) {
                // Log error here since request failed
                if (callback != null) callback.onError();
            }
        });
    }
}
