package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.BooksPage;
import com.cybertek.library.pages.HomePage;
import com.cybertek.library.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class DataTable_StepDefinitions {

    HomePage homePage = new HomePage();
    BooksPage booksPage = new BooksPage();

    @When("User click on {string} link")
    public void user_click_on_link(String string) {
        homePage.getUsersLink.click();
    }

    @Then("show records default value should be {int}")
    public void show_records_default_value_should_be(int expectedDefault) {
        Assert.assertEquals(expectedDefault, homePage.defaultValue());
    }
    @Then("show records should have following options:")
    public void show_records_should_have_following_options(List<String> expectedList) {
        Assert.assertEquals(homePage.actualList(), expectedList);
    }

    @Then("table should have following column names:")
    public void table_should_have_following_column_names(List<String> expectedList) {
        Assert.assertEquals(homePage.columnNamesList(), expectedList);
    }

    @Then("the user should see the table headers")
    public void theUserShouldSeeTheTableHeaders(List<String> expectedHeaderList) {
        Assert.assertEquals(booksPage.actualHeaderList(), expectedHeaderList);
    }

    @When("the user click the book categories dropdown")
    public void the_user_click_the_book_categories_dropdown() {
       booksPage.bookCategoriesDropDown.click();
    }

    @Then("User should be able to see all categories")
    public void user_should_be_able_to_see_all_categories(List<String> expectedBookCategories) {
        BrowserUtils.sleep(3);
        Assert.assertEquals(booksPage.bookCategories(), expectedBookCategories);
    }

}
