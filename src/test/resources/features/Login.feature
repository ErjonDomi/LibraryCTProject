Feature: Login Functionality
  User should be able to login

  Background:
    Given User is on the login page

  @librarian
  Scenario: Login as a Librarian
      When User login as a librarian
      Then Dashboard should be displayed

  Scenario: Login as a Student
      When User login as a Student
      Then Dashboard should be displayed





