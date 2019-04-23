package ga.awsapp.qrscanner.create.schemes;

public class Telephone extends QRCodeScheme {
    private static final String TEL = "TEL";

    private String number;

    public Telephone() {
    }

    public Telephone(String number) {
        this.setNumber(number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(TEL).append(DEFAULT_KEY_VALUE_SEPARATOR);
        if (number != null)
            sb.append(number);

        return sb.toString();
    }

    public String getNumber() {
        return number;
    }

    public Telephone setNumber(String number) {
        this.number = number;
        return this;
    }
}

