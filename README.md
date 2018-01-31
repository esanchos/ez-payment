# Ez Payment

Thank you for reaching here.

This is Android Library with an static method for you to generate Card Hash to https://pagar.me/ service.

## Installation

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

Include the Internet permission in your `manifest.xml`.

```java
<uses-permission android:name="android.permission.INTERNET"/>
```

## Using the lib

Call getCardHash method as below:

```java
String cardNumber = "4111111111111111";
String cardHolderName = "João Matheus";
String cardExpirationDate = "1225";  //means december of 2015
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
```