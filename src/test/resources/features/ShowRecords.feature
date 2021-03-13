Feature: Data Table Values

Background:
  Given User is on the login page
  When User login as a librarian

@dataTable
Scenario: verify default values in Users page
    And User click on "Users" link
    Then show records default value should be 10
    And show records should have following options:
          | 5   |
          | 10  |
          | 15  |
          | 50  |
          | 100 |
          | 200 |
          | 500 |