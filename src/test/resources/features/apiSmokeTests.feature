Feature: API smoke tests
  As a software tester, I want to run smoke CRUD tests so that I can quickly check the basic functionality

  Background:
    Given RestAssured is configured to the basic correct configuration
    And username and token are correct

  Scenario: Getting all boards
    When I want to get all boards
    Then I should get the correct response about all requested boards

  Scenario: Creating a board
    When I want to create a board according my testing standards
    Then I should get the correct response about the created board

  Scenario: Getting the previously created board
    When I want to get the board
    Then I should get the correct response about the requested board

  Scenario: Deleting the previously gotten board
    When I want to delete the board
    Then I should get the correct response about the deleted board

#todo fill it soon, at least "update" something



