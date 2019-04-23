package ga.awsapp.qrscanner.create.schemes;

public class WiFi extends QRCodeScheme {
    private static final String WIFI = "WIFI";
    private static final String SSID = "S";
    private static final String TYPE = "T";
    private static final String PASSWORD = "P";

    private String ssid;
    private String type;
    private String password;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(WIFI).append(DEFAULT_KEY_VALUE_SEPARATOR);
        if (ssid != null)
            sb.append(SSID).append(DEFAULT_KEY_VALUE_SEPARATOR).append(ssid).append(DEFAULT_LINE_END);
        if (type != null)
            sb.append(TYPE).append(DEFAULT_KEY_VALUE_SEPARATOR).append(type).append(DEFAULT_LINE_END);
        if (password != null)
            sb.append(PASSWORD).append(DEFAULT_KEY_VALUE_SEPARATOR).append(password).append(DEFAULT_LINE_END);

        sb.append(DEFAULT_LINE_END);

        return sb.toString();
    }

    public String getPassword() {
        return password;
    }

    public String getSsid() {
        return ssid;
    }

    public String getType() {
        return type;
    }

    public WiFi setPassword(String password) {
        this.password = password;
        return this;
    }

    public WiFi setSsid(String ssid) {
        this.ssid = ssid;
        return this;
    }

    public WiFi setType(String type) {
        this.type = type;
        return this;
    }
}
