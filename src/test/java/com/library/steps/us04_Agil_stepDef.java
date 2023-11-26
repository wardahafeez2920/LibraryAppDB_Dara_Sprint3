package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.util.Asserts;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;


import java.util.Map;

public class us04_Agil_stepDef {
    BookPage bookPage = new BookPage();

    String searchBook;
    @Given("the user navigates to {string} page-AB")
    public void the_user_navigates_to_page_ab(String moduleName) {
     bookPage.navigateModule(moduleName);
    }

    @When("the user searches for {string} book-AB")
    public void the_user_searches_for_book(String bookName) {
        bookPage.search.sendKeys(bookName + Keys.ENTER);
        searchBook = bookName;
    }

    @When("the user clicks edit book button-AB")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(searchBook).click();
        BrowserUtil.waitFor(8);
    }
    @Then("book information must match the Database-AB")
    public void book_information_must_match_the_database() {
        String query = "SELECT DISTINCT isbn,BO.name as bookname,author, BC.name as category, year, U.full_name as BorrowedBy\n" +
                "from books BO  JOIN book_categories BC\n" +
                "                  on BO.book_category_id = BC.id\n" +
                "             join book_borrow BB\n" +
                "                  on BO.id = BB.book_id\n" +
                "             join users U\n" +
                "                  on BB.user_id = U.id\n" +
                "where BO.name = 'Clean Code';";
        DB_Util.runQuery(query);
        Map<String, String> DB_Book_info = DB_Util.getRowMapForJoinedTables(1);
        System.out.println("DB_Book_info = " + DB_Book_info);


        String expectedBookName = bookPage.bookName.getAttribute("value");
        String actualBookName = DB_Book_info.get("bookname");
        Assert.assertEquals(expectedBookName,actualBookName);


        String expectedISBN = bookPage.isbn.getAttribute("value");
        String actualISBN = DB_Book_info.get("isbn");


        String expectedAuthor = bookPage.author.getAttribute("value");
        String actuaAuthor = DB_Book_info.get("author");
        Assert.assertEquals(expectedAuthor,actuaAuthor);

        Select select = new Select(bookPage.categoryDropdown);
        String expectedCategory = select.getFirstSelectedOption().getText();
        String actualCategory = DB_Book_info.get("category");


        String expectedYear = bookPage.year.getAttribute("value");
        String actualYear = DB_Book_info.get("year");
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedCategory,actualCategory);
    }



}
