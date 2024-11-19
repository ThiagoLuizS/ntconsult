package br.com.desafio.ntconsult.exception;

public class GlobalException extends RuntimeException {
    public GlobalException(String msg) {
        super(msg);
    }

    public GlobalException(String msg, Throwable cause) {
        super(msg, cause);
    }
}