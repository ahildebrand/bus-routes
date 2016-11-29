package com.example;

import com.example.loader.BusRoutesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class BusRoutesService {

    private BusRoutesLoader busRoutesLoader;

    private Map<Integer, List<Integer>> busRoutes;

    @Autowired
    public BusRoutesService(BusRoutesLoader busRoutesLoader) {
        this.busRoutesLoader = busRoutesLoader;
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
        busRoutes = busRoutesLoader.loadData();
    }
}
