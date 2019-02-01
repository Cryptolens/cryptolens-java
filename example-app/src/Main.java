import io.cryptolens.methods.*;
import io.cryptolens.models.*;

public class Main {

    public static void main(String[] args) {

        String RSAPubKey = "<RSAKeyValue><Modulus>sGbvxwdlDbqFXOMlVUnAF5ew0t0WpPW7rFpI5jHQOFkht/326dvh7t74RYeMpjy357NljouhpTLA3a6idnn4j6c3jmPWBkjZndGsPL4Bqm+fwE48nKpGPjkj4q/yzT4tHXBTyvaBjA8bVoCTnu+LiC4XEaLZRThGzIn5KQXKCigg6tQRy0GXE13XYFVz/x1mjFbT9/7dS8p85n8BuwlY5JvuBIQkKhuCNFfrUxBWyu87CFnXWjIupCD2VO/GbxaCvzrRjLZjAngLCMtZbYBALksqGPgTUN7ZM24XbPWyLtKPaXF2i4XRR9u6eTj5BfnLbKAU5PIVfjIS+vNYYogteQ==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        String auth = "WyIyNjA5IiwiaWE5b0VFT3Q2eDlNR2FvbHBHK2VOYUZ4bzNjT3h5UkNrMCtiYnhPRSJd";

        LicenseKey license = Key.Activate(auth, RSAPubKey, new ActivateModel(3349, "ICVLD-VVSZR-ZTICT-YKGXL", Helpers.GetMachineCode()));

        if (license == null || !Helpers.IsOnRightMachine(license)) {
            System.out.println("The license does not work.");
        } else {
            System.out.println("The license is valid!");
            System.out.println("It will expire: " + license.Expires);


            // Saving and loading a license (for offline use)
            String licenseString = license.SaveAsString();
            LicenseKey newLicense = LicenseKey.LoadFromString(RSAPubKey, licenseString, 1);

            System.out.println(newLicense.Expires);
        }

    }

    public static void main2() {
        String RSAPubKey = "<RSAKeyValue><Modulus>sGbvxwdlDbqFXOMlVUnAF5ew0t0WpPW7rFpI5jHQOFkht/326dvh7t74RYeMpjy357NljouhpTLA3a6idnn4j6c3jmPWBkjZndGsPL4Bqm+fwE48nKpGPjkj4q/yzT4tHXBTyvaBjA8bVoCTnu+LiC4XEaLZRThGzIn5KQXKCigg6tQRy0GXE13XYFVz/x1mjFbT9/7dS8p85n8BuwlY5JvuBIQkKhuCNFfrUxBWyu87CFnXWjIupCD2VO/GbxaCvzrRjLZjAngLCMtZbYBALksqGPgTUN7ZM24XbPWyLtKPaXF2i4XRR9u6eTj5BfnLbKAU5PIVfjIS+vNYYogteQ==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        String auth = "WyIyNjA5IiwiaWE5b0VFT3Q2eDlNR2FvbHBHK2VOYUZ4bzNjT3h5UkNrMCtiYnhPRSJd";

        LicenseKey license = Key.Activate(auth, RSAPubKey, new ActivateModel(3349, "MTMPW-VZERP-JZVNZ-SCPZM", Helpers.GetMachineCode(),300, 1 ));

        if (license == null || !Helpers.IsOnRightMachine(license, true, true)) {
            System.out.println("The license does not work.");
        } else {
            System.out.println("The license is valid!");
            System.out.println("It will expire: " + license.Expires);
        }
    }
}
