@API
Feature: Project extended API tests
  As a software tester, I want to run project extended API tests
  so that I can deeply check the related functionality for a variety of behaviors

  Background:
    Given RestAssured is configured to the basic correct configuration
    * username and token are correct


  Scenario Outline: Getting a project (negative scenarios)
    When I send a request to get the project with PROJECT_ID or KEY "<projectIdOrKey>"
    Then I get the status code <statusCode>
    * I get a response with the error message "<errorMessage>"

    Examples:
      | projectIdOrKey      | statusCode | errorMessage                                              |
      | -1                  | 404        | No project could be found with key '-1'.                  |
      | 0                   | 404        | No project could be found with key '0'.                   |
      | 1                   | 404        | No project could be found with id '1'.                    |
      | 2                   | 404        | No project could be found with id '2'.                    |
      | 500000000000000000  | 404        | No project could be found with id '500000000000000000'.   |
      | 999999999999999999  | 404        | No project could be found with id '999999999999999999'.   |
      | 1000000000000000000 | 404        | No project could be found with key '1000000000000000000'. |
      | 1000000000000000001 | 404        | No project could be found with key '1000000000000000001'. |
