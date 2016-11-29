package com.example;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("integration-test")
public class BusRoutesIT {

    @LocalServerPort
    private int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void isDirect() {
        when().get("/api/direct?dep_sid=1&arr_sid=2").then().statusCode(200).body("direct_bus_route", is(true));
    }

    @Test
    public void isNotDirect() {
        when().get("/api/direct?dep_sid=4&arr_sid=5").then().statusCode(200).body("direct_bus_route", is(false));
    }
}
