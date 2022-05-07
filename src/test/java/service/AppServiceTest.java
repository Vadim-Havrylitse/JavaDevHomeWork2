package service;

import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class AppServiceTest {
    private AppService appService;
    private Map<String, Product> allProduct;

    @BeforeEach
    void setUp() {
        appService = new AppService();
        allProduct = Map.of(
                "F", new Product("F",1.25,3,3),
                "E", new Product("E",4.25));
    }

    @Test
    void testCorrectValueCalculateFoodBasketCost() {
        Map<String, Double> correctValue = new HashMap<>();
        correctValue.put("F", 1.25);
        correctValue.put("E", 4.25);
        correctValue.put("FF", 2.5);
        correctValue.put("EE", 8.5);
        correctValue.put("FFF", 3.0);
        correctValue.put("FFFFEE", 12.75);
        correctValue.put("FFFFEE321", 12.75);
        correctValue.put("F ♦ F_F E*/E F-", 12.75);

        correctValue.forEach((key, value) ->
                Assertions.assertEquals(correctValue.get(key), appService.calculateFoodBasketCost(key, allProduct)));
    }

    @Test
    void testCorrectMethodWorkDoOptimization() {
        Map<String, Map<String, Integer>> correctValue = new HashMap<>();
        correctValue.put("ABCD",
                Map.of("A", 1,
                        "B", 1,
                        "C", 1,
                        "D", 1));
        correctValue.put("FF",
                Map.of("F",2));
        correctValue.put("AAACCCCCC",
                Map.of("A", 3,
                        "C", 6));

        correctValue.forEach((basket, answer) -> {
            Assertions.assertEquals(answer, appService.doOptimization(basket));
        });
    }

    @Test
    void testMethodOkCostOfOneTypeProductInBasket() {
        Assertions.assertEquals(1.25, appService.costOfOneTypeProductInBasket("F",1, allProduct));
        Assertions.assertEquals(3, appService.costOfOneTypeProductInBasket("F",3, allProduct));
        Assertions.assertEquals(4.25, appService.costOfOneTypeProductInBasket("F",4, allProduct));
        Assertions.assertEquals(8.5, appService.costOfOneTypeProductInBasket("E",2, allProduct));
    }
}