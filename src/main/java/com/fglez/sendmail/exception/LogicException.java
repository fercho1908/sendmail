package com.fglez.sendmail.exception;

/**
 * Created by IntelliJ IDEA
 * User: Fernando Gonzalez<fgonzalez@syesoftware.com>
 * Date: 7/21/16.
 * Time: 1:52 PM.
 */
public class LogicException extends Exception {

    public LogicException(String message, Exception e) {
        super(message, e);
    }

    public LogicException(String message) {
        super(message);
    }
}