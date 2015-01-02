package test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import skgl.*;

import org.junit.Test;

public class SKMTest {

	@Test
	public void GetParametersTest() {
		
		Map<String,String> input= new HashMap<String, String>();
		
		input.put("uid", "2");
        input.put("pid", "3");
        input.put("hsum", "751963");
        input.put("sid", "JLLKY-NEPGJ-BQCOR-EIJGY");
        input.put("sign","true");

		Map<String,String> output = SKM.GetParameters(input, "Validate");
		
		System.out.println(output);
		if(output.containsKey("error") && output.get("error") != "")
		{
			System.out.println(output.get("error"));
			fail(output.get("error"));
		}
	}
	
	@Test
	public void  GetKeyInformationFromParametersTest() throws NumberFormatException, ParseException {
		
		Map<String,String> input= new HashMap<String, String>();
		
		input.put("uid", "2");
        input.put("pid", "3");
        input.put("hsum", "751963");
        input.put("sid", "JLLKY-NEPGJ-BQCOR-EIJGY");
        input.put("sign","true");

		KeyInformation output = SKM.GetKeyInformationFromParameters(SKM.GetParameters(input, "Validate"));
		
		System.out.println(output.CreationDate());
		
	}

}
