Feature: Several Modules
  3. As a user, I should be able to  see several modules once I login.

  Background:
    Given User is on the login page

   @regression
  Scenario: Students should have access to 2 modules
    When User login as a Student
    Then User should see following modules
      | Books           |
      | Borrowing Books |


  @regression
  Scenario: Librarians  should have access to 3 modules
    When User login as a librarian
    Then User should see following modules
      | Dashboard       |
      | Users           |
      | Books |
