package com.server.exception;

/**
 * The type Encryption exception.
 *
 */
public class EncryptionException extends Exception {
    /**
     * Instantiates a new Encryption exception.
     */
    public EncryptionException() {
        super();
    }

    /**
     * Instantiates a new Encryption exception.
     *
     * @param message the message
     */
    public EncryptionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Encryption exception.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public EncryptionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Encryption exception.
     *
     * @param throwable the throwable
     */
    public EncryptionException(Throwable throwable) {
        super(throwable);
    }
}
