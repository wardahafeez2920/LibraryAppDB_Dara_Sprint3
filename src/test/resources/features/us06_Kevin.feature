@kv @ui @db
Feature: Books module
  As a librarian, I should be able to add new book into library

  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page-KV
    When the librarian click to add book-KV
    And the librarian enter book name "<Book Name>"-KV
    When the librarian enter ISBN "<ISBN>"-KV
    And the librarian enter year "<Year>"-KV
    When the librarian enter author "<Author>"-KV
    And the librarian choose the book category "<Book Category>"-KV
    And the librarian click to save changes-KV
    Then verify "The book has been created." message is displayed-KV
    And verify "<Book Name>" information must match with DB-KV
    Examples:
      | Book Name   | ISBN  | Year  | Author  | Book Category |
      | kev+friends | 10107 | 2023  | Kevin v | Classic       |
      | McNugget    | 1117  | 77710 | owen c  | Drama         |