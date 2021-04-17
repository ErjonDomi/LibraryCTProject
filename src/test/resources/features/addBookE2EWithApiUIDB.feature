Feature: adding book
@booksE2E
  Scenario: adding book with api endpoint
    Given  user creates a book with api endpoint "Aamir"
    When  user login as librarian
    Then user should verify that the book is created
    And user should verify that the book is created in database as well