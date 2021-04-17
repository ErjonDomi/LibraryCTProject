Feature: user status feature

  Background:
    Given User is on the login page
    When User login as a librarian
    When User click on "Users" link


  @smoke @regression
  Scenario: verify user status
    And the user click Status dropdown
    Then the user should see the following options:
      | ACTIVE   |
      | INACTIVE |
