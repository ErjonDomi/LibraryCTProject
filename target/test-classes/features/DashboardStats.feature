Feature: Dashboard stats
@Dash
  Scenario: Checking dashboard stats with api and database

    Given when user access api and check dashboard stats
    Then User verify that the book count is same in database
  And User verify that the borrowed books count  is same in database
  And User verify that the users  count  is same in database
