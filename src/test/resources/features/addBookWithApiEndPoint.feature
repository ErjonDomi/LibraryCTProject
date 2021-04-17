Feature: Adding book
@add
  Scenario: adding book with api endpoint

    Given new book is added using api endpoint
    Then  user should verify that the book is created in database