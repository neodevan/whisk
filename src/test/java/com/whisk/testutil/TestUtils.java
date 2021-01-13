package com.whisk.testutil;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import org.testng.Assert;
import java.util.UUID;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by neodevan
 */
public class TestUtils {

	// Verify the http response status returned. Check Status Code is 200?
	public void checkStatusIs200(Response res) {
		Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
	}
	
	public void verifyCalculation(String actual, String Expected) {
		Assert.assertEquals(actual, Expected, "Actual: "+actual+" is Not Matching Expected: "+Expected);
	}



	public static String getUniqueId() {
		return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 5), System.currentTimeMillis() / 1000);
	}

	public static String generategmailId() {
		return String.format("%s@%s", getUniqueId(), "gmail.com");
	}
	

	public String getResposeBodyAsString(Response res, String path) {
		ResponseBody body = res.getBody();
		JsonPath jp = new JsonPath(res.getBody().asString());
		return jp.get(path);
	}


}
