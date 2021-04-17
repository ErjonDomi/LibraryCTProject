Feature: Borrowing Books
  As a user, I should able to see my borrowing books.

  Background:
    Given User is on the login page
    When User login as a Student

  @smoke @regression
  Scenario: borrowing books table columns names
    When the user click Borrowing Books module
    Then the user should see the following column names:
      | Action              |
      | Book Name           |
      | Borrowed Date       |
      | Planned Return Date |
      | Return Date         |
      | Is Returned ?       |
