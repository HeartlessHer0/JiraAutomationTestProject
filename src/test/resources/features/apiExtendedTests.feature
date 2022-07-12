Feature: API extended tests
  As a software tester, I want to run API extended tests
  so that I can deeply check the functionality according to various test design techniques

  Background:
    Given RestAssured is configured to the basic correct configuration
    * username and token are correct
#    * I want to create a project according my testing standards    todo fill it

  Scenario Outline: Getting the project (negative scenarios)
    When I want to get the project with PROJECT_ID or KEY "<projectIdOrKey>"
    Then I should get the status code <statusCode>
    * I should get a response with the error message "<errorMessage>"
    Examples:
      | projectIdOrKey      | statusCode | errorMessage                                               |
      | TAD1                | 200        | <none>                                                     |
      | -1                  | 404        | Проект с ключом '-1' не может быть найден                  |
      | 0                   | 404        | Проект с ключом '0' не может быть найден                   |
      | 1                   | 404        | Проект с id '1' не может быть найден                       |
      | 2                   | 404        | Проект с id '2' не может быть найден                       |
      | 500000000000000000  | 404        | Проект с id '500000000000000000' не может быть найден      |
      | 999999999999999999  | 404        | Проект с id '999999999999999999' не может быть найден      |
      | 1000000000000000000 | 404        | Проект с ключом '1000000000000000000' не может быть найден |
      | 1000000000000000001 | 404        | Проект с ключом '1000000000000000001' не может быть найден |


#todo fill it etc
