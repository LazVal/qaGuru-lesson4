package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

public class SimpleWineTest extends BaseTest {
    MainPage mainPage = new MainPage();

    @ValueSource(strings = {
            "Крепкие напитки",
            "Вода"
    })
    @ParameterizedTest(name = "Поиск алкогольного напитка {0} на сайте")
    void SearchDifferentDrinksShouldNotBeEmptyTest(String typeOfDrink) {
        mainPage.openPage()
                .proofOfLegalAgeClick()
                .acceptCityClick()
                .searchBarInput(typeOfDrink)
                .checkCatalogDescriptionTitle(typeOfDrink)
                .searchCatalogItem();
    }

    @CsvSource(value = {
            "Крепкие напитки|Крепкие напитки",
            "Вода | Вода и соки"
    }, delimiter = '|')
    @ParameterizedTest(name = "Поиск алкогольного напитка {0} на сайте и нахождение его в категории товара {1}")
    void SearchDifferentDrinksShouldNotBeEmptyAndHaveCategoryTest(String typeOfDrink, String expectedCategory) {
        mainPage.openPage()
                .proofOfLegalAgeClick()
                .acceptCityClick()
                .searchBarInput(typeOfDrink)
                .checkCatalogDescriptionTitle(typeOfDrink)
                .searchCatalogItem()
                .checkCategoryItem(expectedCategory);

    }

    @CsvFileSource(resources = "/testData/SearchDifferentCountyDrinks.csv")
    @ParameterizedTest(name = "Поиск {0} вина на сайте и просмотр карточки {1}")
    void SearchDifferentCountyDrinksTest(String differentCounty, String fullCountryName) {
        mainPage.openPage()
                .proofOfLegalAgeClick()
                .acceptCityClick()
                .clickButton()
                .chooseCountyItem(differentCounty)
                .checkCountyFullName(fullCountryName);

    }
}
