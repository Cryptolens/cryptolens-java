package skgl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.json.*;

/**
 * This class contains methods to ease access to the <a href="https://serialkeymanager.com/">Serial Key Manager</a> Web API. <p>
 * Most of the methods will require variables "pid", "uid", "hsum".
 * These values can be found on <a href="https://serialkeymanager.com/Ext/Val">https://serialkeymanager.com/Ext/Val</a>.
 * @author Artem Los
 * @version 1.0
 */
public class SKM {

	/**
	 * This method will interpret the input from the dictionary that was returned through "GetParameters" method, if the action was either "activate" or "validate". <br>
	 * If there is an error, null will be returned.
	 * @param parameters The Map array returned in "GetParameters" method.
	 * @return A Key Information object. If parameters contains an error, null is returned.
	 */
	public static KeyInformation GetKeyInformationFromParameters(Map<String,String> parameters)
	{
		if(parameters.containsKey("error"))
		{
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		
		boolean[] features = new boolean[8];
		features[0] = Boolean.parseBoolean(parameters.get("f1"));
		features[1] = Boolean.parseBoolean(parameters.get("f2"));
		features[2] = Boolean.parseBoolean(parameters.get("f3"));
		features[3] = Boolean.parseBoolean(parameters.get("f4"));
		features[4] = Boolean.parseBoolean(parameters.get("f5"));
		features[5] = Boolean.parseBoolean(parameters.get("f6"));
		features[6] = Boolean.parseBoolean(parameters.get("f7"));
		features[7] = Boolean.parseBoolean(parameters.get("f8"));
		
		try
		{
			
			KeyInformation ki = new KeyInformation(df.parse(parameters.get("created")),
					df.parse(parameters.get("expires")),
					Integer.parseInt(parameters.get("settime")),
					Integer.parseInt(parameters.get("timeleft")), 
					features, parameters.getOrDefault("newkey", ""),
					parameters.getOrDefault("notes", ""), 
					parameters.getOrDefault("signature", ""));
		
			return ki;
		
		}
		catch(Exception e)
		{return null;}
				
	}
	
	/**
	 * This method will take in a set of parameters (input parameters) and send them to the given action. You can find them here: <a href="http://docs.serialkeymanager.com/web-api/">http://docs.serialkeymanager.com/web-api/</a>. <p>
	 * The action that is given by <i>typeOfAction</i> is a short string that is located in the end of <i>https://serialkeymanager.com/Ext/</i>. 
	 * For example, since <a href="http://docs.serialkeymanager.com/web-api/generate-key/">Key Generation</a> is normally accessed by <i>https://serialkeymanager.com/Ext/GenKey</i>, the <i>typeOfAction</i> parameter should be <i>GenKey</i>.   
	 * @param inputParameters A map that contains data such as "uid", "pid", etc.
	 * @param typeOfAction A string that tells what to do, i.e. "validate", "activate" etc.
	 * @return A map of the JSON elements returned for that particular request. If unsuccessful, null is returned.
	 */
	public static Map<String, String> GetParameters(Map<String,String> inputParameters, String typeOfAction )
	{
		// this should not thrown an error. if it does, return null.
		try
		{
			String query = GetQueryFromMap(inputParameters);
			
			URL url = new URL("https://serialkeymanager.com/Ext/" + typeOfAction);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-length", String.valueOf(query.length())); 
			
			con.setDoOutput(true); 
			con.setDoInput(true); 
			
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(query);
			output.close();
			
			DataInputStream input = new DataInputStream(con.getInputStream()); 
			
			JsonReader jsonReader = Json.createReader(con.getInputStream());	
			
			return jsonToMap(jsonReader.readObject());
		
		}
		catch (Exception e)
		{return null;}

	}
	
	private static String GetQueryFromMap(Map<String, String> inputParameters)
	{
		String output = "";
		
		for (String param : inputParameters.keySet()) {
			output += param + "=" + inputParameters.get(param) + "&"; 
		}
		return output;
	}
	
	
	// the methods below are from http://stackoverflow.com/questions/21720759/jsonobject-to-map
	// this code was modified.
	// orginally by @Vikas Gupta.
	
	private static Map jsonToMap(JsonObject json) throws JsonException, ParseException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JsonObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private static Map toMap(JsonObject object) throws JsonException, ParseException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr =  object.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JsonArray) {
                value = toList((JsonArray) value);
            }

            else if(value instanceof JsonObject) {
                value = toMap((JsonObject) value);
            }
            else if(value instanceof JsonString)
            {
            	value =   ((JsonString) value).getString();
            }
            else if(value instanceof JsonNumber)
            {
            	NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH); 
            	value = nf.parseObject(value.toString());
            }
            map.put(key, value);
        }
        return map;
    }

    private static List toList(JsonArray array) throws JsonException, ParseException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if(value instanceof JsonArray) {
                value = toList((JsonArray) value);
            }

            else if(value instanceof JsonObject) {
                value = toMap((JsonObject) value);
            }
            list.add(value);
        }
        return list;
    }
	
}
