package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/api")
public class BusRoutesApi {

    private BusRoutesService busRoutesService;

    @Autowired
    public BusRoutesApi(BusRoutesService busRoutesService) {
        this.busRoutesService = busRoutesService;
    }

    @RequestMapping(value = "/direct", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public DirectRouteDto direct(@RequestParam int dep_sid, @RequestParam int arr_sid) {
        return new DirectRouteDto(dep_sid, arr_sid, busRoutesService.isDirect(dep_sid, arr_sid));
    }

    @ExceptionHandler
    public ResponseEntity handleExceptions() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
