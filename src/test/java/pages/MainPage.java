package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.JsSnippets;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement proofOfLegalAge = $x("//button[normalize-space()='Подтверждаю']");
    private final SelenideElement acceptSity = $x("//button[normalize-space()='Да, все верно']");
    private final SelenideElement searchBar = $("[data-autotest-target-id='header-search-input']");
    private final SelenideElement catalogDescriptionTitle = $("[data-autotest-target-id='search-main-found-text']");
    private final ElementsCollection catalogItem = $$(".catalog-grid__item");
    private final SelenideElement categoryItem = $("[data-autotest-target-id='search-main-category-2'] > p");
    private final SelenideElement buttonClick = $("[data-autotest-target-id='header-ecom-catalog-btn']");

    public MainPage openPage() {
        open("/");
        JsSnippets.deleteAdvertisement();
        return this;
    }

    public MainPage proofOfLegalAgeClick() {
        proofOfLegalAge.click();
        return this;
    }

    public MainPage acceptSityClick() {
        acceptSity.click();
        return this;
    }

    public MainPage searchBarInput(String value) {
        searchBar.setValue(value).pressEnter();
        return this;
    }
    public MainPage searchCatalogItem() {
        catalogItem.shouldBe(sizeGreaterThan(0));
        return this;
    }
    public MainPage checkCatalogDescriptionTitle(String value) {
        catalogDescriptionTitle.shouldHave(text(value));
        return this;
    }
    public MainPage checkCategoryItem(String value) {
        categoryItem.shouldHave(text(value));
        return this;
    }
    public MainPage clickButton() {
        buttonClick.click();
        return this;
    }

    public MainPage chooseCountyItem(String value) {
        $x("//div[text()='" + value + "']").click();
        return this;
    }
    public MainPage checkCountyFullName(String value) {
        $x("//h1[text()='" + value + "']").click();
        return this;
    }
}
