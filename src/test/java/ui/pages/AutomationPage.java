package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AutomationPage {
    // H1 в шапке страницы
    private final SelenideElement header = $("div.jumbotron h1");

    public AutomationPage open() {
        Selenide.open("https://restful-booker.herokuapp.com/");
        return this;
    }

    public AutomationPage assertWelcome() {
        // Ждём, пока появится и станет видимым
        header.shouldBe(visible)
              .shouldHave(text("Welcome to Restful-Booker"));
        return this;
    }
}
