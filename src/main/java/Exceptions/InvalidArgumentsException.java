package Exceptions;

import App.Config;

public class InvalidArgumentsException extends BaseException {
    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException() {
        super(Config.USAGE);
    }
}
