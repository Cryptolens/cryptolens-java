# Cryptolens Licensing for Java

This repository contains examples of how to verify license keys in Java applications.

An example of how to call the helper methods can be found [here](https://github.com/Cryptolens/cryptolens-java/blob/master/src/main/java/io/cryptolens/App.java) (which we explain in more details under *Example*)
and all the helper methods are located [here](https://github.com/Cryptolens/cryptolens-java/tree/master/src/main/java/io/cryptolens).

This library is still being developed. In meantime, please contact us at support@cryptolens.io should you have any questions.

## Example
The following example is similar to what is covered in the [key verification tutorial](https://help.cryptolens.io/examples/key-verification). The only difference at this point is that the Java version does not have built in support for machine code computation.

```java

public class App 
{
    public static void main(String[] argv) {
        Cryptolens cryptolens = Cryptolens.getDefault();
        cryptolens.setExponentBase64("AQAB");
        cryptolens.setModulusBase64("khbyu3/vAEBHi339fTuo2nUaQgSTBj0jvpt5xnLTTF35FLkGI+5Z3wiKfnvQiCLf+5s4r8JB/Uic/i6/iNjPMILlFeE0N6XZ+2pkgwRkfMOcx6eoewypTPUoPpzuAINJxJRpHym3V6ZJZ1UfYvzRcQBD/lBeAYrvhpCwukQMkGushKsOS6U+d+2C9ZNeP+U+uwuv/xu8YBCBAgGb8YdNojcGzM4SbCtwvJ0fuOfmCWZvUoiumfE4x7rAhp1pa9OEbUe0a5HL+1v7+JLBgkNZ7Z2biiHaM6za7GjHCXU8rojatEQER+MpgDuQV3ZPx8RKRdiJgPnz9ApBHFYDHLDzDw==");

        Cryptolens.ActivateResponse cryptolensResponse =
            cryptolens.activate( "WyI0NjUiLCJBWTBGTlQwZm9WV0FyVnZzMEV1Mm9LOHJmRDZ1SjF0Vk52WTU0VzB2Il0="
                            , 3646
                            , "MPDWY-PQAOW-FKSCH-SGAAU"
                            , "289jf2afs3"
                            );

        if (!cryptolensResponse.successful()) {
            System.out.println("Failed to activate!");
            Cryptolens.ActivateServerError er = cryptolensResponse.getServerError();
            Exception ex = cryptolensResponse.getException();

            if (er != null) {
                System.out.println("Server error: " + er);
            }

            if (ex != null) {
                ex.printStackTrace(System.out);
            }

            return;
        }

        LicenseKey licenseKey = cryptolensResponse.getLicenseKey();

        System.out.println("Activation was successful!");
        System.out.println(licenseKey.getKey());
        System.out.println(licenseKey.getF1());
    }
}
```
