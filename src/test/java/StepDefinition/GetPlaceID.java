package StepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class GetPlaceID {
	@Before("@DeletePlace")
	public void getPlavceID() throws IOException
	{
		if(AddPlaceStepDefinition.place_id==null)
		{
		AddPlaceStepDefinition add=new AddPlaceStepDefinition();
		add.associated_add_place_payload("Dhanabal","English","29, grggf layofut, cohen 09");
		add.providing_http_request("APIAddTest","POST");
		add.verify_place_Id_created_maps_to_using("Dhanabal","APIGetTest");
		}
	}
}
