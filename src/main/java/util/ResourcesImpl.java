package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class ResourcesImpl implements Resources{
    @Override
    public File getFileResources(String fileName) {
        URL resource = ResourcesImpl.class.getClassLoader().getResource(fileName);
        assert resource != null : new FileNotFoundException("Your stock has not been loaded!");
        return new File(resource.getPath());
    }
}
