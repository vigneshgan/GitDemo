Feature: Validating Place API's 

Scenario: Verify if Place being Successfully added using AddplaceAPI
Given Add Place Payload
When When User calls "AddPlaceAPI" with Post http request
Then the API call is success with status code 200
And "Status" in response body should be "OK"
And "Scope" in response body should be "APP"