import service.AppService;

public class Main {
    public static void main(String[] args) {
        AppService app = new AppService();
        System.out.println(app.calculateFoodBasketCost("ABCDABA"));
        app.printFoodBasketCost("ABCDABA");
        System.out.println(app.calculateFoodBasketCost("A BC  DAB A"));
        app.printFoodBasketCost("ABCDA34BA");
    }
}
