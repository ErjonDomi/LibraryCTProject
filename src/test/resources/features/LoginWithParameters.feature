Feature: Login with parameters

  Background:
    Given User is on the login page

@firefox
  Scenario: Login as librarian 12
    When User enter username "librarian12@library"
    And User enter password 'AOYKYTMJ'
    And User click the sign in button
    Then Dashboard should be displayed
    And There should be 4317 users
    #number can be whatever you have there

@chrome
  Scenario: Login as librarian in the same line
    When I login using "librarian12@library" and "AOYKYTMJ"
    Then Dashboard should be displayed
    Then Dashboard should be displayed
