
package ga.awsapp.qrscanner.create.schemes;

public abstract class QRCodeScheme {

    @Override
    public abstract String toString();

    public static final String LINE_FEED = "\n";
    public static final String DEFAULT_PARAM_SEPARATOR = "\r?\n";
    public static final String DEFAULT_KEY_VALUE_SEPARATOR = ":";
    public static final String DEFAULT_LINE_END = ";";
}
