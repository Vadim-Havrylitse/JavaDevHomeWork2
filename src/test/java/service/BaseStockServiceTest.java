package service;

import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseStockServiceTest {
    private BaseStockService baseStockService;
    private Product testProduct1;
    private Product testProduct2;

    @BeforeEach
    void setUp() {
        baseStockService = new BaseStockService();
        testProduct1 = new Product("F", 2.0);
        testProduct2 = new Product("F",2.0, 2,1.0);
    }

    @Test
    void getBasePrices_TestReturnNotNull() {
        Assertions.assertNotNull(baseStockService.getBasePrices("Stock.txt"));
    }

    @Test
    void checkProduct_TestReturnCorrectValue() {
        Assertions.assertEquals(testProduct1, baseStockService.checkProduct("F\\2,0\\t\\t"));
        Assertions.assertEquals(testProduct1, baseStockService.checkProduct("F\\2.0\\0\\-"));
        Assertions.assertEquals(testProduct1, baseStockService.checkProduct("F\\2,0\\fs\\sd"));
        Assertions.assertEquals(testProduct2, baseStockService.checkProduct("F\\2.0\\2\\1,0"));
    }

    @Test
    void deserializeProduct_TestReturnCorrectValue() {
        Assertions.assertEquals(testProduct1, baseStockService.deserializeProduct(new String[]{"F","2.0","t","t"}));
        Assertions.assertEquals(testProduct1, baseStockService.deserializeProduct(new String[]{"F","2.0","0","-"}));
        Assertions.assertEquals(testProduct1, baseStockService.deserializeProduct(new String[]{"F","2.0","fs","sd"}));
        Assertions.assertEquals(testProduct2, baseStockService.deserializeProduct(new String[]{"F","2.0","2","1.0"}));
    }
}