@UI
Feature: Navigation

  Background:
    Given Atlassian login page is open
    And User enter correct email and password


  Scenario: User open Jira All Projects Page from Start Page
    When User click Jira Software button
    Then User see Jira All Projects Page

  Scenario: User open Jira Work Page from Jira All Projects Page
    When User go to Jira All Projects Page and click Jira Software Main Button
    Then User see Jira Work Page

  Scenario: User open Board Page from Jira All Projects Page
    When User go to Jira All Projects Page and click some Board Button
    Then User see Board Page

  Scenario: User open Logout Page from Start Page
    When User click Quit Button
    Then User see Logout page

  Scenario: User open Project Setting Page from Jira All Projects Page
    When User click Project Settings Button
    Then User see Project Settings Page
