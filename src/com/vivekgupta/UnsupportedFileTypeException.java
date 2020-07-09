package com.vivekgupta;

/**
 * Custom Exception handler for Unsupported file type
 */
public class UnsupportedFileTypeException extends Exception {
    public UnsupportedFileTypeException() {
        super("This file type isn't supported");
    }
}
