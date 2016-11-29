package com.example.loader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Profile("default")
public class NoOptionArgsBusRoutesLoader implements BusRoutesLoader {

    private final Environment env;

    @Autowired
    public NoOptionArgsBusRoutesLoader(Environment env) {
        this.env = env;
    }


    @Override
    public File getSource() {
        return new File(env.getProperty("nonOptionArgs", String[].class)[0]);
    }
}
