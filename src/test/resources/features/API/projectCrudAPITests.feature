@API
Feature: Project CRUD API tests
  As a software tester, I want to run project CRUD API tests
  so that I can quickly check the basic functionality of projects

  Background:
    Given RestAssured is configured to the basic correct configuration
    * username and token are correct


  Scenario: Creating a project
    When I send a request to create a project according my testing standards
    Then I get a correct response about the created project
    * I remember project ID and SELF fields from the response

  Scenario: Getting the previously created project
    When I send a request to get the previously created project
    Then I get a correct response about the requested project

  Scenario: Getting all projects
    When I send a request to get all projects
    Then I get a correct response about all projects
    * my previously created project is among them

  Scenario: Updating the previously created project
    When I send a request to update the previously created project
    Then I get a correct response about the requested project

  Scenario: Deleting the previously created project
    When I send a request to delete the previously created project
    Then I get a correct response about the deleted project
