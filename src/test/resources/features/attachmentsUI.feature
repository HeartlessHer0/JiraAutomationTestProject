@UI
Feature: Attachments to Issue

  Background:
    Given Atlassian login page is open
    And User enter correct email and password
    And User open the Test Board


  Scenario: User add Image to Issue
    When User create issue
    And User add Test Image to Issue
    Then User see added Image in Issue

  Scenario: User delete added Image from Issue
    When User open test Issue
    And User delete Test Image from Issue
    Then User see Issue without Test Image

    Scenario: User add comment to Issue
      When User open test Issue
      And User add comment
      Then User see added comment in Issue

  Scenario: User delete added comment from Issue
    When User open test Issue
    And User delete Test Comment
    Then User see Issue without Test Comment

