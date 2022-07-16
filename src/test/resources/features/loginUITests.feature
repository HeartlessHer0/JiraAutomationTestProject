@UI
Feature: Login

  Background:
    Given Atlassian login page is open


  Scenario: Login Test with correct Email and Password
    When User enter correct email and password
    Then User come to the Atlassian Start Page

  Scenario: Login Test with correct Email and incorrect Password
    When User enter correct email and incorrect password
    Then User see error text area

  Scenario: Login Test with correct Email and without Password
    When User enter correct email and password is null
    Then User see password error message

  Scenario: Login Test with unregistered email
    When User enter unregistered email
    Then User see Registration button

  Scenario: Login Test without email
    When User dont enter email
    Then User see Email error message
