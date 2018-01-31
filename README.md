Ez Payment

Thank you for reaching here.

This is Android Library with an static method for you to generate Card Hash to https://pagar.me/ service.

Installation

On your project's `build.gradle`:

```gradle
allprojects {
    repositories {
        maven {
            url 'https://dl.bintray.com/esanchos/ez-payment/'
        }
        ...
    }
}
```

On you app module `build.gradle`:

```gradle
implementation 'com.esanchos.ezpayment:ez-payment:0.0.4'
```

Include the Internet permission in you `manifest.xml`.

```android
<uses-permission android:name="android.permission.INTERNET"/>
```

Using the lib

Just call getCardHash method as below:
```android
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
```

```java
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
```