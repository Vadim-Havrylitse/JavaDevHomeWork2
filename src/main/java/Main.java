import service.AppService;
import service.BaseStockService;
import util.ResourcesImpl;

public class Main {

    public static AppService app = new AppService(new BaseStockService(new ResourcesImpl("Stock.txt")));

    public static void main(String[] args) {
        app.printFoodBasketCost("ABCD");
        app.printFoodBasketCost("ABCDABA");
        app.printFoodBasketCost("ABCDA34BA");
        app.printFoodBasketCost("");
        app.printFoodBasketCost(null);


    }
}
