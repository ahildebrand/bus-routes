package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DirectRouteDto {

    private final int dep_sid;
    private final int arr_sid;
    private final boolean direct_bus_route;


}
