package org.nevdashchenko.mikrotik.exception;

/**
 * Exception class for length operation errors.
 * @author Maksym Nevdashchenko (maksym.nevdashchenko@gmail.com)
 * @version 1.0.0 30/04/2018
 * @since 1.0.0
 */
public class LengthException extends Exception {
    public LengthException() {
        super();
    }

    public LengthException(String message) {
        super(message);
    }
}
