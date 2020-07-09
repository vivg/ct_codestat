package com.vivekgupta;

/**
 * Custom Exception handler for Invalid file type exception
 */
public class InvalidFileTypeException extends Exception {
    public InvalidFileTypeException() {
        super("File is either hidden or has no extension");
    }
}
