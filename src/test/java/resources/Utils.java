package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	static RequestSpecification req;
	static ResponseSpecification responses;
	public RequestSpecification addRequestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getBaseUrl("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	public ResponseSpecification addResponseSpecification()
	
	{
		responses=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responses;
	}
	
	public String getBaseUrl(String key) throws IOException
	{
		Properties prob=new Properties();
		FileInputStream in=new FileInputStream("C:\\Users\\DHANAPAL\\eclipse-workspace\\RestApiAutomation\\src\\test\\java\\resources\\globalvariables.properties");
		prob.load(in);
		return prob.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
