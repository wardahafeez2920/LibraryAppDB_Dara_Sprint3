@ss
Feature: Books module
  As a librarian, I should be able to add new book into library

  @db @ui @ac1
  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page-SS
    When the librarian click to add book-SS
    And the librarian enter book name "<Book Name>"-SS
    When the librarian enter ISBN "<ISBN>"-SS
    And the librarian enter year "<Year>"-SS
    When the librarian enter author "<Author>"-SS
    And the librarian choose the book category "<Book Category>"-SS
    And the librarian adds "<Description>" of the book
    And the librarian click to save changes-SS
    Then verify "The book has been created" message is displayed-SS
    Examples:
      | Book Name                              | ISBN     | Year | Author            | Book Category           | Description                                                                                                                                            |
      | Naruto: The tests of the Ninja. vol. 1 | 10010023 | 2000 | Masashi Kishimoto | Comic and Graphic Novel | It tells the story of Naruto Uzumaki, a young ninja who seeks recognition from his peers and dreams of becoming the Hokage, the leader of his village. |
      | Percy Jackson: The Sea of Monsters     | 10020023 | 2006 | Rick Riordan      | Fantasy                 | The story is set in present day America and is about the 13 year old demigod Percy Jackson and his friends Annabeth and Tyson.                         |

  @db @ui @ac2
  Scenario Outline: Verify previously entered Book information must match with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page-SS
    When librarian opens book "<Book Name>" in "<Category>"-SS
    Then  database must match book "<Book Name>" information-SS
    Examples:
      | Book Name                              | Category                |
      | Naruto: The tests of the Ninja. vol. 1 | Comic and Graphic Novel |
      | Percy Jackson: The Sea of Monsters     | Fantasy                 |
