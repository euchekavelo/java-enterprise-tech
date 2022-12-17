package com.skillbox;

public class StackException extends Exception {

    public StackException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
