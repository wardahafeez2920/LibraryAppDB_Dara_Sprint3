package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class us06_Sabina_stepDef {

    DashBoardPage dashboardPage = new DashBoardPage();
    BookPage bookPage = new BookPage();


    @Given("the user navigates to {string} page-SS")
    public void the_user_navigates_to_page_ss(String moduleName) {
        // this method dynamically can navigate to provided name module
        dashboardPage.navigateModule(moduleName);
    }

    @When("the librarian click to add book-SS")
    public void the_librarian_click_to_add_book_ss() {
        BrowserUtil.waitForPageToLoad(10);
        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}-SS")
    public void the_librarian_enter_book_name_ss(String bookName) {
        bookPage.bookName.sendKeys(bookName);
    }

    @When("the librarian enter ISBN {string}-SS")
    public void the_librarian_enter_isbn_ss(String isbnNum) {
        bookPage.isbn.sendKeys(isbnNum);
    }

    @When("the librarian enter year {string}-SS")
    public void the_librarian_enter_year_ss(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}-SS")
    public void the_librarian_enter_author_ss(String author) {
        bookPage.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}-SS")
    public void the_librarian_choose_the_book_category_ss(String bookCategory) {
        Select bookCatSelect = new Select(bookPage.categoryDropdown);

        for (WebElement each : bookCatSelect.getOptions()) {
            if (each.getText().equals(bookCategory)) {
                each.click();
            }
        }
        System.out.println("bookCatSelect.getFirstSelectedOption() = " + bookCatSelect.getFirstSelectedOption().getText());
    }

    @When("the librarian adds {string} of the book")
    public void the_librarian_adds_of_the_book(String desc) {
        bookPage.description.sendKeys(desc);
    }

    @When("the librarian click to save changes-SS")
    public void the_librarian_click_to_save_changes_ss() {
        bookPage.saveChanges.click();
        // bookPage.closeBtn.click();
    }

    @Then("verify {string} message is displayed-SS")
    public void verify_message_is_displayed_ss(String msg) {
        BrowserUtil.waitForVisibility(bookPage.toastMessage, 20);
        System.out.println("bookPage.toastMessage.isDisplayed() = " + bookPage.toastMessage.isDisplayed());
    }



    /**
     * AC2 -> comparison of Database with UI input
     **/

    @When("librarian opens book {string} in {string}-SS")
    public void librarian_opens_book_in_ss(String bookName, String category) {

        bookPage.search.sendKeys(bookName);
        bookPage.editBookBtn(bookName).click();
    }

    @Then("database must match book {string} information-SS")
    public void database_must_match_book_information_ss(String bookName) {

        List<String> expectedBookInfo = BrowserUtil.getElementsTextWithValueAttribute(bookPage.bookInfo());

        DB_Util.runQuery("select name, isbn, year, author, description from books where name = '" + bookName + "'");

        List<String> actualBookInfo = DB_Util.getRowDataAsList(1);

        Assert.assertEquals(expectedBookInfo, actualBookInfo);


    }


}
