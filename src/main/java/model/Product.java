package model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Product {
    private String name;
    private double price;
    private int promotion;
    private double promotionPrice;

    public Product(String name, double price) {
        this(name, price, 0, 0);
    }

    public Product(String name, double price, int promotion, double promotionPrice) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        this.promotionPrice = promotionPrice;
    }

    public boolean hasPromotion (){
        return this.promotion != 0;
    }


}
