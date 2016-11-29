package com.example;

public class DirectRouteDto {

    private final int dep_sid;
    private final int arr_sid;
    private final boolean direct_bus_route;

    public DirectRouteDto(int dep_sid, int arr_sid, boolean direct_bus_route) {
        this.dep_sid = dep_sid;
        this.arr_sid = arr_sid;
        this.direct_bus_route = direct_bus_route;
    }

    public int getDep_sid() {
        return dep_sid;
    }

    public int getArr_sid() {
        return arr_sid;
    }

    public boolean isDirect_bus_route() {
        return direct_bus_route;
    }


}
