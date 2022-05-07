import model.Product;
import service.AppService;
import service.BaseStockService;
import util.ResourcesImpl;

import java.util.Map;

public class Main {

    public static AppService app = new AppService(new BaseStockService(new ResourcesImpl("Stock.txt")));

    public static void main(String[] args) {
        app.printFoodBasketCost("ABCD");
        app.printFoodBasketCost("ABCDABA");
        app.printFoodBasketCost("ABCDA34BA");
        app.printFoodBasketCost("");

        Map<String, Product> f = Map.of(
                "F", new Product("F", 1.25, 3, 3.0),
                "E", new Product("E", 4.25));
        System.out.println(f.get("G"));
    }
}
