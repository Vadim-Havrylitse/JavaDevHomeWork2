package service;


import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class BaseStockService {
    private static BaseStockService baseConnect;

    private final Map<String, Product> productList = new HashMap<>();

    private BaseStockService(){}

    public static BaseStockService getInstance (){
        if (baseConnect == null) {
            baseConnect = new BaseStockService();
        }
        return baseConnect;
    }

    public Map<String, Product> getBasePrices (String stockFileName){
        productList.clear();
        URL resource = BaseStockService.class.getClassLoader().getResource(stockFileName);
        assert resource != null : new FileNotFoundException("Your stock has not been loaded!");
        try{
            Scanner scanner = new Scanner(new File(resource.getPath()));
            while (scanner.hasNextLine()){
                Product temp = checkProduct(scanner.nextLine());
                productList.put(temp.getName(), temp);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product checkProduct (String scannerLine){
        String[] splitLine = scannerLine.replaceAll(",+", ".").split("\\\\");
        return deserializeProduct(splitLine);
    }

    public static Product deserializeProduct (String[] args){
        assert args != null;
        return args[2].equals("-") ?
                new Product(args[0],Double.parseDouble(args[1])) :
                new Product(args[0],Double.parseDouble(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]));
    }


}
