import model.Product;
import service.AppService;
import service.BaseStockService;

import java.util.Map;

public class Main {
    public static Map<String, Product> stock = new BaseStockService().getBasePrices("Stock.txt");



    public static void main(String[] args) {
        AppService app = new AppService();
        app.printFoodBasketCost("ABCD", stock);
        app.printFoodBasketCost("ABCDABA", stock);
        app.printFoodBasketCost("ABCDA34BA", stock);
    }
}
