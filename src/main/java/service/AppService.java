package service;

import lombok.NoArgsConstructor;
import model.Product;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class AppService {
    private final Map<String, Product> allProducts = BaseStockService.getInstance().getBasePrices("Stock.txt");
    private double cost = 0.0;

    public void printFoodBasketCost (String basket){
        System.out.println("Cost of \""
                + basket
                + "\" = "
                + calculateFoodBasketCost(basket));
    }



    public double calculateFoodBasketCost(String basket) {
        cost = 0.0;
        Map<String, Integer> optimizeFoodBasket = doOptimization(basket);
        optimizeFoodBasket.forEach((key, value) -> cost += costOfOneTypeProductInBasket(key, value, allProducts));
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

    public double costOfOneTypeProductInBasket(String oneTypeProductName, int oneTypeProductCount, Map<String, Product> allProducts) {
        Product tempProduct = allProducts.get(oneTypeProductName);
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
