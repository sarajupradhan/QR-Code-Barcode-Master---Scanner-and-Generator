package ga.awsapp.qrscanner.create.schemes;

public class SMS extends QRCodeScheme {
    private static final String SMS = "SMS";

    private String number;
    private String message;

    public SMS() {
    }

    public SMS(String number, String message) {
        this.setMessage(message);
        this.setNumber(number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(SMS).append(DEFAULT_KEY_VALUE_SEPARATOR).append(number);
        if (message != null)
            sb.append(DEFAULT_KEY_VALUE_SEPARATOR).append(message);

        return sb.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getNumber() {
        return number;
    }

    public SMS setMessage(String message) {
        this.message = message;
        return this;
    }

    public SMS setNumber(String number) {
        this.number = number;
        return this;
    }
}
