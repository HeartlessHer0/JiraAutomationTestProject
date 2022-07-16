@API
Feature: Issue CRUD API tests
  As a software tester, I want to run issue CRUD API tests
  so that I can quickly check the basic functionality of issues

  Background:
    Given RestAssured is configured to the basic correct configuration
    * username and token are correct

  Scenario: Creating an issue
    Given a test project for the following tests has been created
    When I send a request to create an issue according my testing standards
    Then I get a correct response about the created issue
    * I remember issue ID and SELF fields from the response

  Scenario: Getting the previously created issue
    When I send a request to get the previously created issue
    Then I get a correct response about the requested issue

  Scenario: Editing the previously created issue
    When I send a request to edit the previously created issue
    Then I get a correct response about the edited issue

  Scenario: Deleting the previously created issue
    When I send a request to delete the previously created issue
    Then I get a correct response about the deleted issue
