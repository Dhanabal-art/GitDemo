Feature: Validation the Add Place API

@AddPlace @Regression
Scenario Outline: Add place into the Add Place API

	Given Associated add place payload "<name>" "<language>" "<address>"
	When providing "APIAddTest" "POST" http request
	Then the responce with status code 200 is received
	And the reasonse "status" is received as "OK"
	And the reasonse "scope" is received as "APP"
	And verify place_Id created maps to "<name>" using "APIGetTest"
	
Examples:
	|name			|language |address					     |
	|Frontline house|French-IN|29, grggf layofut, cohen 09   |
#	|Backline House |Tamil    |45,fgtsbbfb Kl St, greadff 00 |

@DeletePlace @Regression	
Scenario: Verify the Delete Palce ID
	Given Deletepayload
	When providing "APIDeleteTest" "POST" http request
	Then the responce with status code 200 is received
	And the reasonse "status" is received as "OK"