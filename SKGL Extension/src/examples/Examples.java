package examples;

import skgl.KeyInformation;
import skgl.ProductVariables;
import skgl.SKM;

public class Examples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeyActivation();
	}
	
	public static void KeyActivation () {
		String machineCode = "";
		String key = "MNIVR-MGQRL-QGUZK-BGJHQ";
		
		KeyInformation ki = SKM.KeyActivation(new ProductVariables("3","2", "751963"), key, machineCode);
		
		if (ki != null) {
			
			// key is valid
			
			System.out.println("Created: " + ki.CreationDate());
			System.out.println("Expires: " + ki.ExpirationDate());
			
		} else {
			// key is invalid
			System.out.println("Failed activation.");
		}
		
		
	}

}
