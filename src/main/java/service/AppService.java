package service;

import model.Product;

import java.util.HashMap;
import java.util.Map;

public class AppService {
    private final BaseStockService actualStockBaseService;
    private double cost = 0.0;

    public AppService(BaseStockService actualStockBaseService){
        this.actualStockBaseService = actualStockBaseService;
    }

    public void printFoodBasketCost (String basket){
        System.out.println("Cost of \""
                + basket
                + "\" = "
                + calculateTotalCost(basket));
    }

    public double calculateTotalCost(String basket) {
        cost = 0.0;
        if (basket.length() == 0) return 0.0;

        Map<String, Integer> optimizeFoodBasket = doOptimization(basket);
        Map<String, Product> actualStockBase = actualStockBaseService.getBasePrices();
        optimizeFoodBasket.forEach((key, value) -> cost += costOfOneTypeProductInBasket(key, value, actualStockBase));
        return cost;
    }

    public Map<String, Integer> doOptimization(String basket) {
        Map<String, Integer> tempMap = new HashMap<>();
        basket = basket.replaceAll("[^a-zA-Z]", "");
        String[] arrBasket = basket.split("");
        for (String element : arrBasket) {
            if (tempMap.containsKey(element)) {
                tempMap.put(element, tempMap.get(element) + 1);
                continue;
            }
            tempMap.put(element, 1);
        }
        return tempMap;
    }

    public double costOfOneTypeProductInBasket(String oneTypeProductName, int oneTypeProductCount, Map<String, Product> actualStockBase) {
        Product tempProduct = actualStockBase.get(oneTypeProductName);

        if (tempProduct == null) {
            return 0.0;
        }

        double costWithoutPromotion;
        double costWithPromotion;
        if (tempProduct.hasPromotion()) {
            costWithPromotion = (double) (oneTypeProductCount / tempProduct.getPromotion()) * tempProduct.getPromotionPrice();
            costWithoutPromotion = (oneTypeProductCount % tempProduct.getPromotion()) * tempProduct.getPrice();
        } else {
            costWithPromotion = 0;
            costWithoutPromotion = oneTypeProductCount * tempProduct.getPrice();
        }
        return costWithPromotion + costWithoutPromotion;
    }
}
