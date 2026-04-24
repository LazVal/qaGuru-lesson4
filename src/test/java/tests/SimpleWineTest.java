package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import tests.testdata.TestData;

public class SimpleWineTest extends BaseTest{
    MainPage mainPage = new MainPage();
    TestData testData = new TestData();
    @ValueSource(strings = {
            "Крепкие напитки",
            "Вода"
    })
    @ParameterizedTest(name = "Поиск алкогольного напитка {0} на сайте")
    void successSearchDifferentAlcohol(String typeOfDrink){
        mainPage.openPage()
        .proofOfLegalAgeClick()
        .acceptSityClick()
        .searchBarInput(typeOfDrink)
        .checkCatalogDescriptionTitle(typeOfDrink)
        .searchCatalogItem();
    }
}
