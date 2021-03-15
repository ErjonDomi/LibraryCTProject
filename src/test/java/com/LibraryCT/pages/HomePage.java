package com.LibraryCT.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {


    @FindBy(xpath = "//h2[.='2844']")
    public WebElement userNumber;

    @FindBy(xpath = "//a[@class='nav-link']")
    public List<WebElement> modules;

    @FindBy(xpath = "//a[@href='#users']")
    public WebElement getUsersLink;

    @FindBy(xpath = "//select[@name='tbl_users_length']")
    public WebElement showRecordsDropDown;

    @FindBy(xpath = "//table//th")
    public List<WebElement> columnNames;

    @FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
    public WebElement accountHolderName;

    public int howManyUsers(){
        return Integer.parseInt(String.valueOf(userNumber.getText()));
    }

    public int defaultValue(){
        Select showRecords = new Select(showRecordsDropDown);
        return Integer.parseInt(String.valueOf(showRecords.getFirstSelectedOption().getText()));
    }

    public List<String> actualList(){
        Select showRecords = new Select(showRecordsDropDown);
        List<WebElement> options = showRecords.getOptions();
        List<String> actualList = new ArrayList<>();
        for (WebElement webElement : options) {
            actualList.add(webElement.getText());
        }
        return actualList;
    }

    public List<String> columnNamesList(){
        List<String> list = new ArrayList<>();
        for (WebElement each : columnNames) {
            list.add(each.getText());
        }
        return list;
    }

    public List<String> modules(){
        List<String> modulesList = new ArrayList<>();
        for (WebElement module : modules) {
            modulesList.add(module.getText());
        }
        return modulesList;
    }



}
