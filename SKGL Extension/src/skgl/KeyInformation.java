package skgl;

import java.util.Date;

/**
 * A class that stores information about a key. Note, if the Valid=false, no more information (creation date, etc) will be stored in the Key Information object.
 * @author Artem Los
 */
public final class KeyInformation {
	
	private Date creationDate;
	private Date expirationDate;
	private int setTime;
	private int timeLeft;
	private boolean[] features;
	private String newKey;
	private String  notes;
	private String signature;
	
	/**
	 * Initialise the Key Information with data.
	 * @param creationDate The date when the key was generated.
	 * @param expirationDate The specified expiration date.
	 * @param setTime The number of days a key should be valid.
	 * @param timeLeft The number of days before the key expires.
	 * @param features The 8 different features that are stored in the key.
	 * @param newKey In some cases, KeyActivation will return the new key that will be stored in this variable. If there are no changes to the key, the current key will be stored here.
	 * @param notes The notes field of a given key. Make sure to enable access to notes field on http://serialkeymanager.com/Account/Manage.
	 * @param signature When secure option is set to true, this variable will contain the signature of the information that the server returns. It is a signature of almost all variables stored in this variable except for NewKey.
	 */
	public KeyInformation(Date creationDate, Date expirationDate, int setTime, int timeLeft, boolean[] features, String newKey, String notes, String signature)
	{
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.setTime = setTime;
		this.timeLeft = timeLeft;
		this.features= features;
		this.newKey = newKey;
		this.notes = notes;
		this.signature = signature;
	}
	
	/**
	 * The date when the key was generated.
	 * @return A Date with CreationDate.
	 */
	public Date CreationDate()
	{
		return creationDate;
	}
	
	/**
	 * The specified expiration date.
	 * @return A Date with ExpirationDate.
	 */
	public Date ExpirationDate()
	{
		return expirationDate;
	}
	
	/**
	 * The number of days a key should be valid.
	 * @return An int with SetTime.
	 */
	public int SetTime()
	{
		return setTime;
	}
	
	/**
	 * The number of days before the key expires.
	 * @return An int with the number of days left.
	 */
	public int TimeLeft()
	{
		return timeLeft;
	}
	
	/**
	 * The 8 different features that are stored in the key.
	 * @return A boolean array with features.
	 */
	public boolean[] Features()
	{
		return features;
	}
	
	/**
	 * In some cases, KeyActivation will return the new key that will be stored in this variable. If there are no changes to the key, the current key will be stored here.
	 * @return A string that contains a new key, if applicable.
	 */
	public String NewKey()
	{
		return newKey;
	}
	
	/**
	 * The notes field of a given key. Make sure to enable access to notes field on http://serialkeymanager.com/Account/Manage.
	 * @return A string that contains the content of the Notes field.
	 */
	public String Notes()
	{
		return notes;
	}
	
	/**
	 * When secure option is set to true, this variable will contain the signature of the information that the server returns. It is a signature of almost all variables stored in this variable except for NewKey.
	 * @return A string that contains the signature.
	 */
	public String Signature()
	{
		return signature;
	}
}
