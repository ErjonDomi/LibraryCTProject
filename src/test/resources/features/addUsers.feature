Feature: Add users
  As a librarian, I should be able to add users from users page.

  Background:
    Given User is on the login page
    When User login as a librarian

  @addUser
  Scenario: Librarian should add users
    When User click on "Users" link
    Then Librarian add user
    Then User should be able to see new user name

    @close
    Scenario: Librarian should click close button
      When User click on "Users" link
      Then User should be able to close the add user pop-up window

    @edit
    Scenario: Librarians able to edit user info.
      When User click on "Users" link
      When User should be able to edit user information
      Then User should be able to see new user name







