package club.banyuan;

public class IllegalInputException extends CalculatorException{
    private String illegalInputType;
    public IllegalInputException() {
        super();
    }

    public String getIllegalInputType() {
        return illegalInputType;
    }

    public IllegalInputException(String message) {
        super(message);
        illegalInputType = message;
    }

    public IllegalInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
