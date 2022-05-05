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
        basket = basket.replaceAll("[^a-zA-Z]", "");
        Map<String, Integer> optimizeFoodBasket = doOptimization(basket);
        optimizeFoodBasket.entrySet()
                .forEach(typeProductInBasket -> cost += costOfOneTypeProductInBasket(typeProductInBasket, allProducts));
        return cost;
    }

    private Map<String, Integer> doOptimization(String basket) {
        Map<String, Integer> tempMap = new HashMap<>();
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

    private double costOfOneTypeProductInBasket(Map.Entry<String, Integer> oneTypeProduct, Map<String, Product> allProducts) {
        Product tempProduct = allProducts.get(oneTypeProduct.getKey());
        double costWithoutPromotion;
        double costWithPromotion;
        if (tempProduct.hasPromotion()) {
            costWithPromotion = (double) (oneTypeProduct.getValue() / tempProduct.getPromotion()) * tempProduct.getPromotionPrice();
            costWithoutPromotion = (oneTypeProduct.getValue() % tempProduct.getPromotion()) * tempProduct.getPrice();
        } else {
            costWithPromotion = 0;
            costWithoutPromotion = oneTypeProduct.getValue() * tempProduct.getPrice();
        }
        return costWithPromotion + costWithoutPromotion;
    }
}
