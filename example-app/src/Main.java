import io.cryptolens.Cryptolens;
import io.cryptolens.Helpers;
import io.cryptolens.LicenseKey;

public class Main {

    public static void main(String[] args) {
        String RSAPubKey = "<RSAKeyValue><Modulus>sGbvxwdlDbqFXOMlVUnAF5ew0t0WpPW7rFpI5jHQOFkht/326dvh7t74RYeMpjy357NljouhpTLA3a6idnn4j6c3jmPWBkjZndGsPL4Bqm+fwE48nKpGPjkj4q/yzT4tHXBTyvaBjA8bVoCTnu+LiC4XEaLZRThGzIn5KQXKCigg6tQRy0GXE13XYFVz/x1mjFbT9/7dS8p85n8BuwlY5JvuBIQkKhuCNFfrUxBWyu87CFnXWjIupCD2VO/GbxaCvzrRjLZjAngLCMtZbYBALksqGPgTUN7ZM24XbPWyLtKPaXF2i4XRR9u6eTj5BfnLbKAU5PIVfjIS+vNYYogteQ==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";

        Cryptolens cryptolens = Cryptolens.getDefault();
        cryptolens.setRSAPublicKey(RSAPubKey);

        Cryptolens.ActivateResponse response =
                cryptolens.activate( "WyIyNTk1IiwidVVrQm94OGlYS3pHZlhTc0x6Rm9mN1piektrT0FSd0REaFZ0ZXZJMSJd"
                        , 3349
                        , "ICVLD-VVSZR-ZTICT-YKGXL"
                        , Helpers.GetMachineCode()
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
