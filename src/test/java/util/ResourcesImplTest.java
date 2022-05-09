package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesImplTest {

    @Test
    void getFileResources_TestReturnNotNull() {
        ResourcesImpl resourcesImpl = new ResourcesImpl("Stock.txt");
        Assertions.assertNotNull(resourcesImpl.getFileResources());
    }

    @Test
    void getFileResources_TestFail() {
        ResourcesImpl resourcesImpl = new ResourcesImpl("Stock234.txt");
        Assertions.assertThrows(FileNotFoundException.class, resourcesImpl::getFileResources);

        resourcesImpl = new ResourcesImpl("Stock");
        Assertions.assertThrows(FileNotFoundException.class, resourcesImpl::getFileResources);
    }

}