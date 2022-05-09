package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class ResourcesImpl implements Resources{
    private final String fileName;

    public ResourcesImpl(String fileNameFromResources){
        this.fileName = fileNameFromResources;
    }

    @Override
    public File getFileResources() {
        URL resource = getClass().getClassLoader().getResource(fileName);
        assert resource != null : new FileNotFoundException("Your stock has not been loaded!");
        return new File(resource.getPath());
    }
}
