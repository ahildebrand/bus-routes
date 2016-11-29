package com.example.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public interface BusRoutesLoader {

    File getSource() throws IOException;

    default Map<Integer, List<Integer>> loadData() throws IOException {

        Map<Integer, List<Integer>> busRoutes = new HashMap<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(getSource()));
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
        return busRoutes;
    }

}
