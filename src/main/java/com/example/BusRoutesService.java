package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Service
public class BusRoutesService {

    private final Environment env;
    private final ResourceLoader resourceLoader;
    private final Map<Integer, List<Integer>> busRoutes;

    @Autowired
    public BusRoutesService(Environment env, ResourceLoader resourceLoader) {
        this.env = env;
        this.resourceLoader = resourceLoader;
        busRoutes = new HashMap<>();
    }

    public boolean isDirect(@RequestParam int dep_sid, @RequestParam int arr_sid) {

        boolean directRoute = busRoutes.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(dep_sid))
                .anyMatch(entry -> entry.getValue().contains(arr_sid));

        return directRoute;
    }

    @PostConstruct
    public void loadBusRoutes() throws IOException {

        File file = null;

        String[] nonOptionArgs = env.getProperty("nonOptionArgs", String[].class);

        if(nonOptionArgs != null) {
            file = new File(nonOptionArgs[0]);
        }

        if (file == null || !file.exists()) {
            file = resourceLoader.getResource("classpath:routes.txt").getFile();
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] splitted = line.split("\\s+");

            // no valid bus route (must have routeId and at least two stations)
            if (splitted.length < 3) {
                continue;
            }

            Integer routeId = parseInt(splitted[0]);

            List<Integer> stations = new ArrayList<>();
            for (int i = 1; i < splitted.length; i++) {
                stations.add(parseInt(splitted[i]));
            }

            busRoutes.put(routeId, stations);
        }
    }
}
