package test;

import static org.junit.Assert.*;

import java.util.HashMap;
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
		
		if(output.containsKey("error") && output.get("error") != "")
		{
			System.out.println(output.get("error"));
			fail(output.get("error"));
		}
	}

}
