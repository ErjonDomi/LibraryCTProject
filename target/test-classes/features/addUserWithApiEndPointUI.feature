Feature: Adding users

  @endToEnd
  Scenario: Adding user with api endpoint
    Given user is on librarian page and user adds one user with api endpoint
    When user log in as with with his credentials
    Then user should verify his full name is displayed
    And user verifies that his/her name exist in the database
