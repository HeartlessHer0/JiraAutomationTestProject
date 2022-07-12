@UI
Feature: Issue CRUD

  Background:
    Given Atlassian login page is open
    And User enter correct email and password
    And User open the Test Board

  Scenario: User create issue in Test Board
    When User create issue with some parameters
    Then User see created Issue on the Test Board Page

  Scenario: User read created Issue
    When User open created Issue
    Then User see Window with created Issue

  Scenario: User update created Issue
    When User open created Issue
    And User update Issue parameters
    Then User see Window with updated Issue

  Scenario: User delete created Issue from the Test Board
    When User delete created and updated Issue
    Then User see clean board
