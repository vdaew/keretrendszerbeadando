package lemezbolt.exceptions;

public class NoMatchingReleaserException extends Exception {
    public NoMatchingReleaserException() {
    }

    public NoMatchingReleaserException(String message) {
        super(message);
    }
}
