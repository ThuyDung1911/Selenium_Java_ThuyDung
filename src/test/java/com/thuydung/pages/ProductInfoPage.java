package com.thuydung.pages;

import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoPage extends CommonPage {
    private By productName = By.xpath("//h1");
    public static By totalProductPrice = By.xpath("(//div[text()='Total Price:']/parent::div)/following-sibling::div//strong");
    private By productUnit = By.xpath("//span[@class='opacity-70']");
    private By productDescription = By.xpath("//div[@class = 'mw-100 overflow-auto text-left aiz-editor-data']/p");
    private By selectProductName = By.xpath("(//div[contains(@class,'product-name')])[1]");

    public ArrayList<String> productInfo(String product) {
        WebUI.setTextFromSplitString(DashboardPage.inputSearchProduct, product);
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        WebUI.clickElement(By.xpath("//div[normalize-space()='" + product + "']"));
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        String name = WebUI.getElementText(By.xpath("//h1[contains(.,'" + product + "')]"));
        String price = WebUI.getElementText(totalProductPrice);
        String unit = WebUI.getElementText(productUnit);
        String unitProduct = unit.substring(1);
        String description = WebUI.getElementText(productDescription);
        List<String> arrayList = new ArrayList<String>();
        arrayList.add(name);
        arrayList.add(price);
        arrayList.add(unitProduct);
        arrayList.add(description);
        System.out.println("Array" + arrayList);
        System.out.println("Array" + arrayList.get(0));
        return (ArrayList) arrayList;
    }
}
