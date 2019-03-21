package com.shneddy.dietdiary.utils;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class FoodMessenger extends BaseMessenger {

    private String message = "No previously established Food Types!";

    @Override
    public String getMessage() {
        return message;
    }
}
