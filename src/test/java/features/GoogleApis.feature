Feature:verify Google API'S
  @Addplace @Regression
  Scenario Outline: verify if place is added using addplace api and
  able to get the place details with get place API

    Given Add payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then validate the response has the 200 status code
    And validate the response parameter that has "status" is "OK"
    And validate the response parameter that has "scope" is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name   | language | address       |  |
      | George | English  | US Washington |  |

  @DeletePlace @Regression
  Scenario:verify DeletePlace Api functionality
    Given Add DeletePlace payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then validate the response has the 200 status code
    And validate the response parameter that has "status" is "OK"


