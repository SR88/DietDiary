package com.shneddy.dietdiary.exception;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class NoValueInputException extends Exception {

    /**
     * Utilized to create popup messages when the user generates an exception
     * @param message that will be passed to pop-up
     */
    public NoValueInputException(String message){
        super(message);
    }

}
