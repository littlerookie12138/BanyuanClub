package club.banyuan;

public class QuitException extends CalculatorException{
    public QuitException() {
        super();
    }

    public QuitException(String message) {
        super(message);
    }

    public QuitException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuitException(Throwable cause) {
        super(cause);
    }

    protected QuitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
