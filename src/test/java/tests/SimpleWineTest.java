package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import tests.testdata.TestData;

public class SimpleWineTest extends BaseTest {
    MainPage mainPage = new MainPage();
    TestData testData = new TestData();

    @ValueSource(strings = {
            "Крепкие напитки",
            "Вода"
    })
    @ParameterizedTest(name = "Поиск алкогольного напитка {0} на сайте")
    void SearchDifferentDrinksShouldNotBeEmpty(String typeOfDrink) {
        mainPage.openPage()
                .proofOfLegalAgeClick()
                .acceptSityClick()
                .searchBarInput(typeOfDrink)
                .checkCatalogDescriptionTitle(typeOfDrink)
                .searchCatalogItem();
    }

    @CsvSource(value = {
            "Крепкие напитки|Крепкие напитки",
            "Вода | Вода и соки"
    }, delimiter = '|')
    @ParameterizedTest(name = "Поиск алкогольного напитка {0} на сайте и нахождение его в категории товара {1}")
    void SearchDifferentDrinksShouldNotBeEmptyAndHaveCategory(String typeOfDrink, String expectedCategory) {
        mainPage.openPage()
                .proofOfLegalAgeClick()
                .acceptSityClick()
                .searchBarInput(typeOfDrink)
                .checkCatalogDescriptionTitle(typeOfDrink)
                .searchCatalogItem()
                .checkCategoryItem(expectedCategory);

    }

    @CsvFileSource(resources = "/testData/SearchDifferentCountyDrinks.csv")
    @ParameterizedTest(name = "Поиск алкогольного напитка {0} на сайте и нахождение его в категории товара {1}")
    void SearchDifferentCountyDrinks(String differentCounty, String fullCountryName) {
        mainPage.openPage()
                .proofOfLegalAgeClick()
                .acceptSityClick()
                .clickButton()
                .chooseCountyItem(differentCounty)
                .checkCountyFullName(fullCountryName);

    }
}
