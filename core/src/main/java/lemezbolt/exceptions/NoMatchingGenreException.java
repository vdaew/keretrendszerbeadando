package lemezbolt.exceptions;

public class NoMatchingGenreException extends Exception{
    public NoMatchingGenreException() {
    }

    public NoMatchingGenreException(String message) {
        super(message);
    }
}
