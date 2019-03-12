package com.shneddy.dietdiary.utils;

public class FoodMessenger extends BaseMessenger {

    private String message = "No previously established Food Types!";

    @Override
    public String getMessage() {
        return message;
    }
}
