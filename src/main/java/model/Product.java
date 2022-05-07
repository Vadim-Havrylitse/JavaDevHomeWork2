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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (promotion != product.promotion) return false;
        if (Double.compare(product.promotionPrice, promotionPrice) != 0) return false;
        return name.equals(product.name);
    }

}
