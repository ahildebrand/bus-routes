package com.example.loader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Profile("integration-test")
public class ClasspathBusRoutesLoader implements BusRoutesLoader {

    private final ResourceLoader resourceLoader;

    @Autowired
    public ClasspathBusRoutesLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public File getSource() throws IOException {
        return resourceLoader.getResource("classpath:routes.txt").getFile();
    }
}
