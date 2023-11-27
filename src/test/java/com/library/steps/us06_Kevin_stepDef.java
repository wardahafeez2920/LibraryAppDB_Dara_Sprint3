package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class us06_Kevin_stepDef {
    BookPage bookPage = new BookPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("the user navigates to {string} page-KV")
    public void the_user_navigates_to_page_kv(String booksTab) {

        dashBoardPage.navigateModule(booksTab);

    }
    @When("the librarian click to add book-KV")
    public void the_librarian_click_to_add_book_kv() {
        BrowserUtil.waitFor(3);
        bookPage.addBook.click();

    }
    @When("the librarian enter book name {string}-KV")
    public void the_librarian_enter_book_name_kv(String bookName) {

    bookPage.bookName.sendKeys(bookName);

    }
    @When("the librarian enter ISBN {string}-KV")
    public void the_librarian_enter_isbn_kv(String book_ISBN) {

        bookPage.isbn.sendKeys(book_ISBN);

    }
    @When("the librarian enter year {string}-KV")
    public void the_librarian_enter_year_kv(String book_year) {

        bookPage.year.sendKeys(book_year);

    }
    @When("the librarian enter author {string}-KV")
    public void the_librarian_enter_author_kv(String author) {

        bookPage.author.sendKeys(author);

    }
    @When("the librarian choose the book category {string}-KV")
    public void the_librarian_choose_the_book_category_kv(String category) {

        Select select = new Select(bookPage.categoryDropdown);

        select.selectByVisibleText(category);

    }
    @When("the librarian click to save changes-KV")
    public void the_librarian_click_to_save_changes_kv() {

        bookPage.saveChanges.click();

    }
    @Then("verify {string} message is displayed-KV")
    public void verify_message_is_displayed_kv(String message_displayed) {

        bookPage.toastMessage.isDisplayed();
        Assert.assertEquals(message_displayed, bookPage.toastMessage.getText());

    }
    @Then("verify {string} information must match with DB-KV")
    public void verify_information_must_match_with_db_kv(String string) {



    }

}
