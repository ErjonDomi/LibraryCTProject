Feature: Table with default info
  As a students, I should be able to see tables with default info

  Background:
    Given User is on the login page
    When User login as a Student

  @regression
  Scenario: Table columns names
    Then the user should see the table headers
      | Actions     |
      | ISBN        |
      | Name        |
      | Author      |
      | Category    |
      | Year        |
      | Borrowed By |
