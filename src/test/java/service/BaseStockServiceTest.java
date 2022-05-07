package service;

import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import util.Resources;

import java.io.File;
import java.util.Map;
import java.util.Objects;

class BaseStockServiceTest {
    private BaseStockService baseStockService;
    private Product testProduct1;
    private Product testProduct2;

    @BeforeEach
    void setUp() {
        Resources resourcesMock = Mockito.mock(Resources.class);
        String path = Objects.requireNonNull(BaseStockServiceTest.class.getClassLoader().getResource("StockTest.txt")).getPath();
        Mockito.when(resourcesMock.getFileResources()).thenReturn(new File(path));
        baseStockService = new BaseStockService(resourcesMock);
        testProduct1 = new Product("F", 2.0);
        testProduct2 = new Product("F",2.0, 2,1.0);
    }

    @Test
    void getBasePrices_TestReturnCorrectValue() {
        Map<String, Product> correctValue = Map.of(
                "F", new Product("F", 1.25, 3, 3.0),
                "E", new Product("E", 4.25));
        Assertions.assertEquals(correctValue, baseStockService.getBasePrices());
    }

    @Test
    void getBasePrices_TestReturnNotNull() {
        Assertions.assertNotNull(baseStockService.getBasePrices());
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