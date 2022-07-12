@UI
Feature: Boundary Testing
  Background:
    Given Atlassian login page is open
    And User enter correct email and password
    And User open Board creating page

   #Boundary values for Board Name - min 2 symbols, max - 80 symbols
    Scenario: User wants to create a Board with One symbol in the Board Name field
      When User enter in Board Name Field One symbol
      Then User see Title To Short message

  Scenario: User wants to create a Board with Two symbol in the Board Name field
      When User enter in Board Name Field Two symbols
      Then User see generated Board Key

  Scenario: User wants to create a Board with Three symbol in the Board Name field
      When User enter in Board Name Field Tree symbols
      Then User see generated Board Key

  Scenario: User wants to create a Board with Seventy Nine symbols in the Board Name field
    When User enter in Board Name Field Seventy Nine symbols
    Then User see generated Board Key

  Scenario: User wants to create a Board with Eighty symbols in the Board Name field
      When User enter in Board Name Field Eighty symbols
      Then User see generated Board Key

  Scenario: User wants to create a Board with Eighty One symbols in the Board Name field
      When User enter in Board Name Eighty One symbols
      Then User see Title To Long message

  Scenario: User wants to create a Board without symbols in the Board Name field
      When User dont enter symbols in Board Name Field
      Then User see Board Need Name message