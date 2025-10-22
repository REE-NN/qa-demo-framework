package api.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

public class AllureSmokeTest {
    @Test
    void allureWritesResults() {
        Allure.step("Allure, ты здесь?");
    }
}
