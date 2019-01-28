# Cryptolens Licensing for Java

This repository contains examples of how to verify license keys in Java applications.

This library is still being developed. In meantime, please contact us at support@cryptolens.io should you have any questions.

## Example
The following example is similar to what is covered in the [key verification tutorial](https://help.cryptolens.io/examples/key-verification). The only difference at this point is that the Java version does not have built in support for machine code computation.

Assuming you have referenced the `cryptolens.jar` file, the code below should generate successful result. A working project with the code below can be found in the [example-app](https://github.com/Cryptolens/cryptolens-java/tree/master/example-app) folder.

```java
import io.cryptolens.Cryptolens;
import io.cryptolens.LicenseKey;

public class Main {

    public static void main(String[] args) {
        String RSAPubKey = "<RSAKeyValue><Modulus>khbyu3/vAEBHi339fTuo2nUaQgSTBj0jvpt5xnLTTF35FLkGI+5Z3wiKfnvQiCLf+5s4r8JB/Uic/i6/iNjPMILlFeE0N6XZ+2pkgwRkfMOcx6eoewypTPUoPpzuAINJxJRpHym3V6ZJZ1UfYvzRcQBD/lBeAYrvhpCwukQMkGushKsOS6U+d+2C9ZNeP+U+uwuv/xu8YBCBAgGb8YdNojcGzM4SbCtwvJ0fuOfmCWZvUoiumfE4x7rAhp1pa9OEbUe0a5HL+1v7+JLBgkNZ7Z2biiHaM6za7GjHCXU8rojatEQER+MpgDuQV3ZPx8RKRdiJgPnz9ApBHFYDHLDzDw==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";

        Cryptolens cryptolens = Cryptolens.getDefault();
        cryptolens.setRSAPubKey(RSAPubKey);

        Cryptolens.ActivateResponse response =
                cryptolens.activate( "WyI0NjUiLCJBWTBGTlQwZm9WV0FyVnZzMEV1Mm9LOHJmRDZ1SjF0Vk52WTU0VzB2Il0="
                        , 3646
                        , "MPDWY-PQAOW-FKSCH-SGAAU"
                        , "289jf2afs3"
                );

        if (!response.successful()) {
            System.out.println("Failed to activate!");
            Cryptolens.ActivateServerError er = response.getServerError();
            Exception ex = response.getException();

            if (er != null) {
                System.out.println("Server error: " + er);
            }

            if (ex != null) {
                ex.printStackTrace(System.out);
            }

            return;
        }

        LicenseKey licenseKey = response.getLicenseKey();

        System.out.println("Activation was successful!");
        System.out.println(licenseKey.getKey());
        System.out.println(licenseKey.getF1());
    }
}
```

In order to adjust this code to your products, several parameters needs to be changed, which we outline below:

* `RSAPubKey` - the RSA Public key, which can be found on [this](https://app.cryptolens.io/docs/api/v3/QuickStart#api-keys) page.
* `token` - the access token (can be found [here](https://app.cryptolens.io/docs/api/v3/QuickStart#api-keys), in *API Keys* section).
* `product_id` - the id of the product can be found on the product page (in the example above, it's 3646).
* `key` - the license key to be verified (above it's MPDWY-PQAOW-FKSCH-SGAAU).
* `machine_code` - the unique id of the device (we are working on adding a method similar to `Helpers.GetMachineCode()`). In the code above, we hard coded it to 289jf2afs3.


