package resources;

public enum APIResources {
	APIAddTest("/maps/api/place/add/json"),
	APIGetTest("/maps/api/place/get/json"),
	APIDeleteTest("/maps/api/place/delete/json");
	private String resources;
	
	APIResources(String resources)
	{
		this.resources=resources;
	}
	
	public String getResources()
	{
		System.out.println("testing Gitup");
		return resources;
	}
}
