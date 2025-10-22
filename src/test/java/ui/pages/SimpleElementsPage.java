package ui.pages;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
public class SimpleElementsPage {
    public SimpleElementsPage assertLoaded() {
        $("h1, h2").shouldHave(matchText("(?i)Simple\\s+HTML\\s+Elements"));
        return this;
    }
}
