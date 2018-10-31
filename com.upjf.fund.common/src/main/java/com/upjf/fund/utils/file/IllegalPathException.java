package com.upjf.fund.utils.file;

/**
 * 代表非法的路径。
 * 
 */
public class IllegalPathException extends IllegalArgumentException {
    private static final long serialVersionUID = -3229134664162661189L;

    public IllegalPathException() {
        super();
    }

    public IllegalPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPathException(String s) {
        super(s);
    }

    public IllegalPathException(Throwable cause) {
        super(cause);
    }
}
