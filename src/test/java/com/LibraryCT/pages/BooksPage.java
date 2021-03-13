package com.cybertek.library.pages;

import com.cybertek.library.pages.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BooksPage extends BasePage {

    @FindBy(xpath = "//table[@id='tbl_books']//th")
    public List<WebElement> tableHeader;

    @FindBy(xpath = "//select[@id='book_categories']")
    public WebElement bookCategoriesDropDown;

    @FindBy(xpath = "//a[@href='#borrowing-books']")
    public WebElement borrowingBooksLink;

    @FindBy(xpath = "//table[@id='borrowed_list']//th")
    public List<WebElement> borrowingBooksHeaders;

    public List<String> actualHeaderList(){
        List<String> headerList = new ArrayList<>();
        for (WebElement webElement : tableHeader) {
            headerList.add(webElement.getText());
        }
        return headerList;
    }

    public List<String> bookCategories(){
        Select bookCategories = new Select(bookCategoriesDropDown);
        List<WebElement> listOfBooks = bookCategories.getOptions();
        List<String> list = new ArrayList<>();
        for (WebElement book : listOfBooks) {
            list.add(book.getText());
        }
        return list;
    }

    public List<String> borrowingBooksHeadersList(){
        List<String> list = new ArrayList<>();
        for (WebElement borrowingBooksHeader : borrowingBooksHeaders) {
            list.add(borrowingBooksHeader.getText());
        }
        return list;
    }
}
