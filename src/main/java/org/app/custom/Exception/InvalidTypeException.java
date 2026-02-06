package org.app.custom.Exception;

public class InvalidTypeException extends Exception {
    public InvalidTypeException() {
        super("The activity type must be MEAL or EXERCISES!");
    }

    public static boolean checkIfValidType(String type) throws InvalidTypeException {
        if(type.equals("MEAL") || type.equals("EXERCISES")) {
            return true;
        } else  {
            throw new InvalidTypeException();
        }
    }

}
