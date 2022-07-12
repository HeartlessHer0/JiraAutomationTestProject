Feature: API smoke tests
  As a software tester, I want to run API smoke CRUD tests so that I can quickly check the basic functionality

  Background:
    Given RestAssured is configured to the basic correct configuration
    * username and token are correct

  Scenario: Creating a project
    When I want to create a project according my testing standards
    Then I should get the correct response about the created project
    * I will be able to remember ID and SELF fields from the response

  Scenario: Getting the previously created project
    When I want to get the previously created project
    Then I should get the correct response about the requested project

  Scenario: Getting all projects
    When I want to get all projects
    Then I should get the correct response about all projects
    * my previously created project should be among them

  Scenario: Updating the previously created project
    When I want to update the previously created project
    Then I should get the correct response about the requested project

  Scenario: Deleting the previously updated project
    When I want to delete the previously created project
    Then I should get the correct response about the deleted project
