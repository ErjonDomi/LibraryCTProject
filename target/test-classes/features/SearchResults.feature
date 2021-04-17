Feature: Search Result
  As a user, I should be able to login as librarian.

  Background:
    Given User is on the login page
    When User login as a librarian
    And User click on "Users" link

  @smoke @regression
  Scenario: Table columns names
    Then table should have following column names:
      | Actions   |
      | User ID   |
      | Full Name |
      | Email     |
      | Group     |
      | Status    |