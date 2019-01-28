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
