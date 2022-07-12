@UI
Feature: UI Smoke Test

  Background:
    Given Atlassian login page is open
    And User enter correct email and password

    Scenario: User create a new Board
      When User go to the Jira Software page and create a new board  with some parameters
      Then User see created Board with entered parameters

  Scenario: User logout
    When User logout from account
    Then User see login page
