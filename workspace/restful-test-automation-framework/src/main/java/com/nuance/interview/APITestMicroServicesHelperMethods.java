package com.nuance.interview;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

public class APITestMicroServicesHelperMethods {

    String testDataPayload = new String();  //**** JSON / XML / CSV
    String testMSName = new String();
    String actualResponse = new String();
    final String request = "https://punkapi.com/documentation/v2";

    public void initData(String pTestDataPayload) {

        testDataPayload = pTestDataPayload;
    }

    public Boolean readData() {

        Boolean isSuccess = true;

        try {
            // validate using parser to validate
        }
        catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public Boolean ValidateParseData() {

        Boolean isSuccess = true;

        try {
            // validate data using parser to validate
        }
        catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public boolean CallMicroService(String testDataPayload, String testName) {

        boolean isSuccess = true;

        try {
            // Call MicroService
        }
        catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }


    public Boolean ValidateParseResponse (){

        Boolean isSuccess = true;

        try {
            // validate response using parser to validate
        }
        catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public Boolean createTestReport() {

        Boolean isSuccess = true;

        try {
            // create Report file
        }
        catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }

    // make numIterations equal to something more that 3600 requests per hour
    public Boolean testPerformance(int numIterations, String testName) {

        Boolean isSuccess = true;

        try {
            while (numIterations >= 0) {
                CallMicroService(testDataPayload, testName);
                numIterations--;
            }
        }
        catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }
}
