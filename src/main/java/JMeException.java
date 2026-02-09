public class JMeException extends Exception{

    public JMeException() {
        super();
    }

    public static class OutOfBounds extends JMeException {}

    public static class Duplicates extends JMeException {}

    public static class InvalidFormat extends JMeException {}

}
