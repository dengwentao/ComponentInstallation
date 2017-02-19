package com.company.command;

/**
 * Created by dengw on 2/17/2017.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
