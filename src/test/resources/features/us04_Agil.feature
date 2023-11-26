@ag
Feature: As a data consumer, I want UI and DB book information are match.
@db @ui
  Scenario: Verify book information with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page-AB
    When the user searches for "Clean Code" book-AB
    And  the user clicks edit book button-AB
    Then book information must match the Database-AB