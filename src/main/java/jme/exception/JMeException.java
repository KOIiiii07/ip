package jme.exception;

/**
 * Base exception class for JMe-specific errors.
 * Contains static inner classes for specific error types.
 */
public class JMeException extends Exception{

    public JMeException() {
        super();
    }

    /**
     * Thrown when an index is outside the valid range of the task list.
     */
    public static class OutOfBounds extends JMeException {}

    /**
     * Thrown when a task being added already exists in the list.
     */
    public static class Duplicates extends JMeException {}

    /**
     * Thrown when user input does not match the expected command format.
     */
    public static class InvalidFormat extends JMeException {}

}