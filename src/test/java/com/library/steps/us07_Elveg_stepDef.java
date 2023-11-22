package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class us07_Elveg_stepDef {

    String book;
    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();

    @Given("the user navigates to {string} page-EL")
    public void the_user_navigates_to_page_el(String module) {
        bookPage.navigateModule(module);
    }
    @Given("the user searches for {string} book-EL")
    public void the_user_searches_for_book_el(String book) {
        this.book = book;
        bookPage.search.sendKeys(book+ Keys.ENTER);
    }
    @When("the user clicks Borrow Book-EL")
    public void the_user_clicks_borrow_book_el() {
        if(bookPage.borrowBook(book).getAttribute("class").contains("disabled")){
            throw new ElementClickInterceptedException("Book is already borrowed");
        } else{
            bookPage.borrowBook(book).click();
        }
    }
    @Then("verify that book is shown in {string} page-EL")
    public void verify_that_book_is_shown_in_page_el(String module) {
        bookPage.navigateModule(module);
    }
    @Then("verify logged student has same book in database-EL")
    public void verify_logged_student_has_same_book_in_database_el() {
        DB_Util.runQuery("SELECT * FROM book_borrow BOOKBORROW JOIN books LIBRARY on LIBRARY.id = BOOKBORROW.book_id WHERE name = '"+book+"' ORDER BY BOOKBORROW.id DESC");
        List<String> allBorrowedBooksNameStrings = new ArrayList<>();
        for (WebElement webElement : borrowedBooksPage.allBorrowedBooksName) {
            allBorrowedBooksNameStrings.add(webElement.getText());
        }
        Assert.assertEquals(allBorrowedBooksNameStrings.contains(book),(DB_Util.getRowDataAsList(1).contains("0")));
    }
}
