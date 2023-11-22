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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class us07_Elveg_stepDef {

    boolean alreadyBorrowed;
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
        try {
            bookPage.borrowBook(book).click();
        } catch (ElementClickInterceptedException e){
            alreadyBorrowed = true;
        }

    }
    @Then("verify that book is shown in {string} page-EL")
    public void verify_that_book_is_shown_in_page_el(String module) {
        bookPage.navigateModule(module);
        List<String> allBorrowedBooksNameStrings = new ArrayList<>();
        for (WebElement borrowedBook : borrowedBooksPage.allBorrowedBooksName) {
            allBorrowedBooksNameStrings.add(borrowedBook.getText());
        }
        Assert.assertTrue("Book is not in the borrowed book list",allBorrowedBooksNameStrings.contains(book));

    }

    @Then("verify logged student has same book in database-EL")
    public void verify_logged_student_has_same_book_in_database_el() {
        DB_Util.runQuery("select full_name,b.name,bb.borrowed_date from users u inner join book_borrow bb on u.id = bb.user_id inner join books b on bb.book_id = b.id where full_name='"+bookPage.accountHolderName.getText()+"' and name='"+book+"' order by 3 desc");
        Assert.assertEquals("Incorrect name",DB_Util.getCellValue(1, "full_name"), bookPage.accountHolderName.getText());
        Assert.assertEquals("Incorrect book",DB_Util.getCellValue(1, "name"), book);
        if(!alreadyBorrowed){
            Assert.assertTrue(getDateFromString(DB_Util.getCellValue(1, "borrowed_date")).isAfter(LocalDateTime.now().minusSeconds(10)));
        }
    }

    public static LocalDateTime getDateFromString(String inputDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(inputDate, format);
    }

}
