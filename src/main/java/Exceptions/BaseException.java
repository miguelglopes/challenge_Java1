package Exceptions;

import App.Config;

class BaseException extends Exception {
    BaseException(String message) {
        super(message + Config.TRY_HELP);
    }
}
