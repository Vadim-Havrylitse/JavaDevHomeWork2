package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
    private Product testProduct1;
    private Product testProduct2;
    private Product testProduct11;
    private Product testProduct22;

    @BeforeEach
    void setUp() {
        testProduct1 = new Product("F", 2.0);
        testProduct2 = new Product("E",3.0, 5,1.0);
        testProduct11 = new Product("F", 2.0);
        testProduct22 = new Product("E",3.0, 5,1.0);
    }


    @Test
    void hasPromotion() {
        Assertions.assertEquals(true,testProduct2.hasPromotion());
        Assertions.assertEquals(false,testProduct1.hasPromotion());
    }

    @Test
    void testEquals_TestCorrectCompareObject() {
        Assertions.assertEquals(testProduct1,testProduct11);
        Assertions.assertEquals(testProduct2,testProduct22);
        Assertions.assertNotEquals(testProduct1,testProduct2);
    }
}