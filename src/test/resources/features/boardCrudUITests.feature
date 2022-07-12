@UI
Feature: Board CRUD

  Background:
    Given Atlassian login page is open
    And User enter correct email and password

  Scenario: User create a new Board
    When User go to the Jira Software page and create a new board for CRUD test
    Then User see created Board with entered parameters

  Scenario: User open the created Board
    When User open the created Board from the Projects page
    Then User see the created Board on Board page

  Scenario: User update the created Board
    When User open the created Board from the Projects page
    And User update board parameters
    Then User see updated Board with entered parameters

  Scenario: User delete the created Board
    When User delete the created Board from the Projects page
    And User delete the Board from the Trash page
    Then User see delete notification



