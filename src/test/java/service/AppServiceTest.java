package service;

import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class AppServiceTest {
    private AppService appService;



    @BeforeEach
    void setUp() {
        appService = new AppService();

    }

    @Test
    void testCorrectCalculateFoodBasketCost() {
        Map<String, Double> correctValue = new HashMap<>();
        correctValue.put("A",1.25);
        correctValue.put("B",4.25);
        correctValue.put("C",1.00);
        correctValue.put("D",0.75);
        correctValue.put("ABCD", 7.25);
        correctValue.put("ABcD",6.25);
        correctValue.put("AAACCCCCC", 8.00);
        correctValue.put("AAAACCCCCC", 9.25);
        correctValue.put("ABCD321", 7.25);
        correctValue.put("A ♦ B_C D*/-", 7.25);

        correctValue.forEach((key, value) ->
                Assertions.assertEquals(correctValue.get(key), appService.calculateFoodBasketCost(key)));
    }


    @Test
    void testCorrectMethodWorkDoOptimization() {
        Map<String, Map<String, Integer>> correctValue = new HashMap<>();
        correctValue.put("ABCD",
                Map.of("A", 1,
                        "B", 1,
                        "C", 1,
                        "D", 1));
        correctValue.put("D",
                Map.of("D",1));
        correctValue.put("AAACCCCCC",
                Map.of("A", 3,
                        "C", 6));
        correctValue.forEach((basket, answer) -> {
            Assertions.assertEquals(answer, appService.doOptimization(basket));
        });
    }

    @Test
    void testMethodOkCostOfOneTypeProductInBasket() {
        Map<String, Product> allProduct = new HashMap<>();
        allProduct.put("F",new Product("F",1.25,3,3));
        allProduct.put("E",new Product("E",4.25));

        Assertions.assertEquals(1.25, appService.costOfOneTypeProductInBasket("F",1, allProduct));
        Assertions.assertEquals(3, appService.costOfOneTypeProductInBasket("F",3, allProduct));
        Assertions.assertEquals(4.25, appService.costOfOneTypeProductInBasket("F",4, allProduct));
        Assertions.assertEquals(8.5, appService.costOfOneTypeProductInBasket("E",2, allProduct));

    }


}