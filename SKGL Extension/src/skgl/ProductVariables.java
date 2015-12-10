package skgl;

/**
 * A class that stores product variables that are need to 
 * perform key validation/activation/generation through the API.
 * @author Artem Los
 *
 */
public class ProductVariables {

	private String PID;
	private String UID;
	private String HSUM;
	
	public ProductVariables(String PID, String UID, String HSUM) {
		this.PID = PID;
		this.UID = UID;
		this.HSUM = HSUM;
	}
	
	public String getPID() {
		return PID;
	}
	public void setPID(String PID) {
		this.PID = PID;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String UID) {
		this.UID = UID;
	}
	public String getHSUM() {
		return HSUM;
	}
	public void setHSUM(String HSUM) {
		this.HSUM = HSUM;
	}
	
}
