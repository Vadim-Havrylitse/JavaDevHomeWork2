package service;

import model.Product;
import util.Resources;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BaseStockService {

    private final Map<String, Product> productList = new HashMap<>();
    private final Resources resource;

    public BaseStockService(Resources resource){
        this.resource = resource;
    }

    public Map<String, Product> getBasePrices (){
        productList.clear();
        try{
            Scanner scanner = new Scanner(resource.getFileResources());
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
        String[] splitLine = scannerLine
                .replaceAll(",+", ".")
                .split("\\\\");
        return deserializeProduct(splitLine);
    }

    public Product deserializeProduct (String[] args){
        assert args != null;
        return (args[2]+args[3]).matches("[0-9]*[.][0-9]") ?
                new Product(args[0],Double.parseDouble(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3])) :
                new Product(args[0],Double.parseDouble(args[1]));
    }


}
