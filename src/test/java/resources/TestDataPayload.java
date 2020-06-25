package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataPayload {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace a=new AddPlace();
		a.setAccuracy(50);
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress(address);
		a.setLanguage(language);
		a.setWebsite("http://google.com");
		List<String>type=new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		a.setTypes(type);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		return a;
	}
	
	public String deletePayload(String place_id)
	{
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}"; 
	}

}
