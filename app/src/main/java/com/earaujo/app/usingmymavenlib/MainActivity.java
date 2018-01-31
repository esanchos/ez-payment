package com.earaujo.app.usingmymavenlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.earaujo.app.ezpayment.EzPayment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EzPayment ezPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String cardNumber = "4111111111111111";
        String cardHolderName = "abc";
        String cardExpirationDate = "1225";
        String cardCvv = "123";

        ezPayment.getCardHash(
                cardNumber,
                cardHolderName,
                cardExpirationDate,
                cardCvv,
                "ak_test_UQ2kAlOEB1vXtVqz2zoOaumvagNU1g",
                new EzPayment.OnGetCardHashResult<String>() {
            @Override
            public void onResult(String result) {
                if (result!=null) {
                    Log.d(TAG, "getCardHash: " + result);
                }
            }
        });
    }
}
